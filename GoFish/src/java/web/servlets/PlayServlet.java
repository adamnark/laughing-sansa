/*
 */
package web.servlets;

import engine.Engine;
import engine.Factory.PlayerItem;
import engine.cards.Card;
import engine.cards.Series;
import engine.players.Player;
import engine.players.exceptions.InvalidFourException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import web.playerinterface.WebFourPicker;
import web.servlets.printers.ErrorPrinter;
import web.servlets.printers.NavbarPrinter;
import web.servlets.printers.PlayerItemPrinter;

/**
 *
 * @author adam
 */
@WebServlet(name = "PlayServlet", urlPatterns = {"/play"})
public class PlayServlet extends GoFishServlet {

    private Engine engine;
    private static final String PARAM_CLICK_CARD = "card";
    private List<Card> clickedCards = new LinkedList<>();
    private List<String> errors = new LinkedList<>();
    private String actionFromRequest;
    private static final String PARAM_ACTION = "action";
    private static final String PARAM_ACTION_SKIP = "skip";
    private static final String PARAM_ACTION_THROW = "throw";
    private static final String PARAM_ACTION_REQUEST = "request";

    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.engine = (Engine) this.getServletContext().getAttribute(ATTR_ENGINE);
        actionFromRequest = request.getParameter(PARAM_ACTION);
        this.errors.clear();
        handleLastClickedCard(request);
        handleSkipTurn();
        handleThrowFour();
        super.processRequest(request, response);
    }

    @Override
    protected NavbarPrinter.NavbarItems getActiveItem() {
        return null;
    }

    @Override
    protected void printContent(PrintWriter out) {
        printTitle(out, "action=" + actionFromRequest);

        printPlayersList(out);
        out.println("<hr>");
        printPlayerForm(out);
        out.println("<hr>");
        printHand(out);
        out.println("<hr>");
        printGraveyard(out);
        out.println("<hr>");
        ErrorPrinter.printErrors(out, this.errors);
    }

    private void printPlayerForm(PrintWriter out) {
        out.print("<form name='playerform' method='post' action='play' class='form-inline'>");
        printThrowFourButton(out);
        printSkipTurnButton(out);
        out.print("</form>");
    }

    private void printThrowFourButton(PrintWriter out) {
        out.print("<input id='throw' type='button' class='btn' value='Throw red cards'>");
    }

    private void printSkipTurnButton(PrintWriter out) {
        out.print("<input id='skip' type='button' class='btn' value='Skip'>");
    }

    private void printPlayersList(PrintWriter out) {
        Player currentPlayer = engine.getCurrentPlayer();
        printTitle(out, "Players");
        out.println("<ul class='inline'>");
        for (Player player : engine.getPlayers()) {
            if (player.equals(currentPlayer)) {
                out.print("<li style='background:rgb(205, 255, 150);'>");
            } else {
                out.print("<li>");
            }

            out.print(PlayerItemPrinter.makeImgTag(new PlayerItem(player)));
            out.print(" ");
            out.print(player.getName());
            out.print(" : ");
            out.print(player.getScore());
            out.println();
        }

        out.println("</ul>");
    }

    private void printHand(PrintWriter out) {
        printTitle(out, engine.getCurrentPlayer().getName() + "'s Hand:");

        out.println("<ul class='inline'>");
        for (Card card : engine.getCurrentPlayer().getHand().getCards()) {
            String cssClass = "hand";
            if (isCardInClickedCards(card)) {
                cssClass += " clicked";
            }

            printCard(out, card, cssClass);
        }

        out.println("</ul>");
    }

    private boolean isCardInClickedCards(Card card) {
        for (Card card1 : clickedCards) {
            if (card1.getName().equals(card.getName())) {
                return true;
            }
        }
        return false;
    }

    private void printCard(PrintWriter out, Card card, String cssClass) {
        if (cssClass != null && !cssClass.isEmpty()) {
            out.print("<li class='" + cssClass + "' ");
            out.print("card='" + card.getName() + "'>");
        } else {
            out.println("<li>");
        }

        out.print(card.getName());
        out.print(" : {");
        for (Series series : card.getSeries()) {
            out.print(series.getName());
            out.print(" ");
        }

        out.print("}");
        out.println("</li>");
    }

    private void printGraveyard(PrintWriter out) {
        if (this.engine.getGameSettings().isForceShowOfSeries()) {
            printTitle(out, "Graveyard");
            if (this.engine.getLastCardsThrown() == null) {
                out.println("<p>");
                out.println("No cards were thrown yet.");
                out.println("</p>");
            } else {
                printLastCardsThrown(out);
            }
        } else {
            out.println("<p>");
            out.println("The Graveyard is disabled for this game... ");
            out.println("</p>");
        }

    }

    private void printLastCardsThrown(PrintWriter out) {
        out.print("<ul class='inline'>");
        for (Card card : engine.getLastCardsThrown()) {
            printCard(out, card, "graveyard");
        }
        out.print("</ul>");
    }

    @Override
    protected void printMoreScripts(PrintWriter out) {
        out.print("<script src='js/handclick.js'>");
        out.println("</script>");
    }

    private void handleLastClickedCard(HttpServletRequest request) {
        String cardName = (String) request.getParameter(PARAM_CLICK_CARD);
        if (cardName != null) {
            Card currentCard = engine.getCurrentPlayer().getCard(cardName);
            toggleCurrentCard(currentCard);
        }
    }

    private void toggleCurrentCard(Card currentCard) {
        int i = 0;
        for (Card card : clickedCards) {
            if (card.getName().equals(currentCard.getName())) {
                break;
            }
            i++;
        }

        if (i < clickedCards.size()) {
            clickedCards.remove(i);
        } else {
            clickedCards.add(currentCard);
        }
    }

    private void printTitle(PrintWriter out, String title) {
        out.println("<h3>");
        out.println(title);
        out.println("</h3>");
    }

    private void handleSkipTurn() {
        if (actionFromRequest != null
                && actionFromRequest.equals(PARAM_ACTION_SKIP)) {
            this.clickedCards.clear();
            this.engine.advanceTurn();
        }
    }

    private void handleThrowFour() {
        if (actionFromRequest != null
                && actionFromRequest.equals(PARAM_ACTION_THROW)) {
            // if current is human give the current player a four-thrower that would pick these cards.
            if (engine.getCurrentPlayer().isHuman()) {
                ((WebFourPicker) engine.getCurrentPlayer().getFourPicker()).setCardsToThrow(this.clickedCards);
            }
            try {
                this.engine.currentPlayerThrowFour();
            } catch (InvalidFourException ex) {
                this.errors.add(ex.getMessage());
            } finally {
                this.clickedCards.clear();
            }
        }
    }
}
