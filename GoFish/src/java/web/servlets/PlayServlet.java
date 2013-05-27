/*
 */
package web.servlets;

import engine.Engine;
import engine.Factory.PlayerItem;
import engine.cards.Card;
import engine.cards.Series;
import engine.players.Player;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.engine = (Engine) this.getServletContext().getAttribute(ATTR_ENGINE);

        handleLastClickedCard(request);


        super.processRequest(request, response);
    }

    @Override
    protected NavbarPrinter.NavbarItems getActiveItem() {
        return NavbarPrinter.NavbarItems.HOME;
    }

    @Override
    protected void printContent(PrintWriter out) {
//        out.print("<h1>");
//        out.print(this.cardLastClicked);
//        out.print("</h1>");


        printPlayersList(out);
        out.println("<hr>");
        printGraveyard(out);
        out.println("<hr>");
        printHand(out);
        out.println("<hr>");

        printThrowFourForm();
        printHiddenForm(out);
    }

    private void printPlayersList(PrintWriter out) {
        Player currentPlayer = engine.getCurrentPlayer();
        out.println("<h3>" + "Players:" + "</h1>");
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

        out.println("<h3>");
        out.println(engine.getCurrentPlayer().getName() + "'s Hand:");
        out.println("</h3>");

        out.println("<ul>");

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
            out.print("<h3>");
            out.print("Graveyard");
            out.print("</h3>");
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

    private void printHiddenForm(PrintWriter out) {
        out.println("<form action='play' method='post' name='clickform'>");
        out.println("</form>");
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

    private void printThrowFourForm() {
        
    }
}
