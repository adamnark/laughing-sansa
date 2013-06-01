/*
 */
package web.servlets;

import engine.Engine;
import engine.Factory.PlayerItem;
import engine.cards.Card;
import engine.cards.Series;
import engine.players.Player;
import engine.players.exceptions.InvalidFourException;
import engine.request.Request;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import web.playerinterface.WebFourPicker;
import web.playerinterface.WebRequestMaker;
import web.servlets.printers.CardPrinter;
import web.servlets.printers.ErrorPrinter;
import web.servlets.printers.NavbarPrinter;
import web.servlets.printers.PlayerItemPrinter;

/**
 *
 * @author adam
 */
@WebServlet(name = "PlayServlet", urlPatterns = {"/play"})
public class PlayServlet extends GoFishServlet {

    public static final String ATTR_REQUEST = "request-attr";
    private Engine engine;
    private static final String PARAM_CLICK_CARD = "card";
    private List<Card> clickedCards = new LinkedList<>();
    private List<String> errors = new LinkedList<>();
    private List<String> messages = new LinkedList<>();
    private String action;
    private static final String PARAM_ACTION = "action";
    private static final String PARAM_ACTION_SKIP = "skip";
    private static final String PARAM_ACTION_THROW = "throw";
    public static final String PARAM_ACTION_REQUEST = "request";
    public static final String PARAM_ACTION_REQUEST_DONE = "requestform";
    public static final String PARAM_ACTION_AI_TURN = "aiturn";
    private CurrentPlayerState currentPlayerState;

    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.engine = (Engine) this.getServletContext().getAttribute(ATTR_ENGINE);
        if (this.engine != null) {
            action = request.getParameter(PARAM_ACTION);
            bootstrapGame();
            this.errors.clear();
            handleLastClickedCard(request);
            handleSkipTurn();
            handleAITurn(request, response);

            handleThrowFourAction();
            handleRequestCard(request, response);
            handleRequestAction();
            handleEngineMessages(request, response);

            super.processRequest(request, response);
        } else {
            handleGameOver(request, response);
        }
    }

    private void handleEngineMessages(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        while (engine != null && !engine.getEventQueue().isEmpty()) {
            Engine.Event event = engine.getEventQueue().pop();
            switch (event) {
                case GAME_OVER:
                    handleGameOver(request, response);
                    break;
                case FAILED_REQUEST:
                    handleFailedRequestMessage();
                    break;
                case SUCCESSFUL_REQUEST:
                    handleSuccessfulRequestMessage();
                    break;
                case FOUR_CARDS_NOT_THROWN:
                    handleFourCardsNotThrownMessage();
                    break;
                case FOUR_CARDS_THROWN:
                    handleFourCardsThrownMessage();
                    break;
                case PLAYER_OUT_OF_CARDS:
                    handlePlayerOutOfCards();
                    break;
                default:
                    throw new AssertionError("Unsupported message from engine: " + event.toString());
            }
        }
    }

    @Override
    protected NavbarPrinter.NavbarItems getActiveItem() {
        return null;
    }

    @Override
    protected void printContent(PrintWriter out) {
        printPlayersList(out);
        out.println("<hr>");
        printPlayerForm(out);
        ErrorPrinter.printErrors(out, this.errors);
        out.println("<hr>");

        out.println("<table class='table'>");
        out.println("<thead>");
        out.println("<tr>");
        out.println("<th>");
        printTitle(out, engine.getCurrentPlayer().getName() + "'s Hand:");
        out.println("</th>");
        out.println("<th>");
        printTitle(out, "Graveyard");
        out.println("</th>");
        out.println("<th>");
        printTitle(out, "Message log");
        out.println("</th>");
        out.println("</tr>");
        out.println("</thead>");
        out.println("<tbody>");
        out.println("<tr>");
        out.println("<td class='hand'>");
        printHand(out);
        out.println("</td>");
        out.println("<td class='graveyard'>");
        printGraveyard(out);
        out.println("</td>");
        out.println("<td>");
        printLastMessages(out, 5);
        out.println("</td>");
        out.println("</tr>");
        out.println("</tbody>");
        out.println("</table>");

    }

    private boolean isCardInClickedCards(Card card) {
        for (Card card1 : clickedCards) {
            if (card1.getName().equals(card.getName())) {
                return true;
            }
        }
        return false;
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

    private void handleSkipTurn() {
        if (action != null
                && action.equals(PARAM_ACTION_SKIP)) {
            clearStateAndAdvanceTurn();
        }
    }

    private void clearStateAndAdvanceTurn() {
        this.clickedCards.clear();
        this.currentPlayerState = new CurrentPlayerState();
        this.engine.advanceTurn();
    }

    private void handleThrowFourAction() {
        if (action != null
                && action.equals(PARAM_ACTION_THROW)) {

            // if current is human give the current player a four-thrower that would pick these cards.
            if (engine.getCurrentPlayer().isHuman()) {
                ((WebFourPicker) engine.getCurrentPlayer().getFourPicker()).setCardsToThrow(this.clickedCards);
            }
            try {
                this.engine.currentPlayerThrowFour();
                this.currentPlayerState.setHasThrownFour(true);
            } catch (InvalidFourException ex) {
                this.errors.add(ex.getMessage());
            } finally {
                this.clickedCards.clear();
            }
        }
    }

    private void handleFourCardsNotThrownMessage() {
        this.messages.add(getTimestamp() + engine.getCurrentPlayer().getName() + " hasn't thrown any cards.");
    }

    private void handleSuccessfulRequestMessage() {
        this.messages.add(getTimestamp() + engine.getCurrentPlayer().getName() + " has made a successful request!");
    }

    private void handleFailedRequestMessage() {
        this.messages.add(getTimestamp() + engine.getCurrentPlayer().getName() + " has made a bad request!");
    }

    private void handleFourCardsThrownMessage() {
        this.messages.add(getTimestamp() + engine.getCurrentPlayer().getName() + " has thrown four cards!");
    }

    private void handlePlayerOutOfCards() {
        this.messages.add(getTimestamp() + engine.getCurrentPlayer().getName() + " has run out of cards!");
        this.messages.add(getTimestamp() + engine.getCurrentPlayer().getName() + " is out of the game!");
    }

    private void bootstrapGame() {
        if (action != null && action.equals("start")) {
            engine.startGame();
            messages.clear();
            this.currentPlayerState = new CurrentPlayerState();
        }
        if (this.currentPlayerState == null) {
            this.currentPlayerState = new CurrentPlayerState();
        }
    }

    private void handleGameOver(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //this.engine = null;
        this.messages.clear();
        this.clickedCards.clear();
        this.errors.clear();
        request.getRequestDispatcher("gameover").forward(request, response);
    }

    private void handleRequestCard(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (action != null && action.equals(PARAM_ACTION_REQUEST)) {
            Request r = (Request) this.getServletContext().getAttribute(ATTR_REQUEST);
            if (r == null) {
                request.getRequestDispatcher(PARAM_ACTION_REQUEST).forward(request, response);
            }
        }
    }

    private void handleRequestAction() {
        if (action != null
                && action.equals(PARAM_ACTION_REQUEST_DONE)) {
            Request r = (Request) this.getServletContext().getAttribute(ATTR_REQUEST);
            if (r != null) {
                handleHumanCardRequest(r);
                this.getServletContext().removeAttribute(ATTR_REQUEST);
            }
        }
    }

    private void handleHumanCardRequest(Request r) {
        if (engine.getCurrentPlayer().isHuman()) {
            ((WebRequestMaker) engine.getCurrentPlayer().getRequestMaker()).setRequest(r);
        }

        makeACardRequestForCurrentPlayer();
    }

    private void handleAITurn(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (action != null && action.equals(PARAM_ACTION_AI_TURN)) {
            do {
                makeACardRequestForCurrentPlayer();
            } while (!this.currentPlayerState.hasRequestedCard() && !engine.isGameOver());
            try {
                this.engine.currentPlayerThrowFour();
            } catch (InvalidFourException ex) {
            }

            handleEngineMessages(request, response);
            this.currentPlayerState = new CurrentPlayerState();
            clearStateAndAdvanceTurn();
        }
    }

    private void makeACardRequestForCurrentPlayer() {
        boolean cardWasTaken = this.engine.currentPlayerMakeRequest();
        boolean oneMoreTime = this.engine.getGameSettings().isAllowMutipleRequests();
        this.currentPlayerState.setHasRequestedCard(!(oneMoreTime && cardWasTaken));
    }

    //<editor-fold defaultstate="collapsed" desc="printers">
    private void printPlayerForm(PrintWriter out) {
        out.print("<form name='playerform' method='post' action='play' class='form-inline'>");
        if (engine.getCurrentPlayer().isHuman()) {
            printSkipTurnButton(out);
            printThrowFourButton(out);
            printRequestCardButton(out);
        } else {
            printPlayComputerTurnButton(out);
        }
        out.print("</form>");
    }

    private void printThrowFourButton(PrintWriter out) {
        if (!currentPlayerState.hasThrownFour()) {
            out.print("<input id='throw' type='button' class='btn' value='Throw cards'>");
        }
    }

    private void printPlayComputerTurnButton(PrintWriter out) {
        out.print("<input id='aiturn' type='button' class='btn' value='Play Turn'>");
    }

    private void printRequestCardButton(PrintWriter out) {
        if (!currentPlayerState.hasRequestedCard()) {
            out.print("<input id='request' type='button' class='btn' value='Request a card'>");
        }
    }

    private void printSkipTurnButton(PrintWriter out) {
        out.print("<input id='skip' type='button' class='btn' value='Skip'>");
    }

    private void printPlayersList(PrintWriter out) {
        if (engine != null) {
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
    }

    private void printHand(PrintWriter out) {
        if (engine.getCurrentPlayer().isHuman()) {
            for (Card card : engine.getCurrentPlayer().getHand().getCards()) {
                String cssClass = "hand";
                if (isCardInClickedCards(card)) {
                    cssClass += " clicked";
                }

                CardPrinter.printCard(out, card, engine, cssClass);
            }
        }
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

            out.print("<span class='ser" + engine.getAvailableSeries().indexOf(series) + "'>");
            out.print(series.getName());
            out.print("</span>");
            out.print(" ");
        }

        out.print("}");
        out.println("</li>");
    }

    private void printGraveyard(PrintWriter out) {
        if (this.engine.getGameSettings().isForceShowOfSeries()) {
//            printTitle(out, "Graveyard");
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
//        out.print("<ul>");
        for (Card card : engine.getLastCardsThrown()) {
            CardPrinter.printCard(out, card, engine, "");
        }
//        out.print("</ul>");
    }

    @Override
    protected void printMoreScripts(PrintWriter out) {
        out.print("<script src='js/play.js'></script>");
        out.println("");
    }

    private void printTitle(PrintWriter out, String title) {
        out.println("<h3>");
        out.println(title);
        out.println("</h3>");
    }

    private void printLastMessages(PrintWriter out, int n) {
        if (!messages.isEmpty()) {
            java.util.Date date = new java.util.Date();

//            printTitle(out, "Message log");
            out.println("<div>");
            for (int i = messages.size() - 1; i >= messages.size() - n; i--) {
                try {
                    String message = messages.get(i);
                    out.println("<p>");
                    out.print(message);
                    out.println("</p>");
                } catch (IndexOutOfBoundsException ex) {
                }
            }
            out.println("</div>");
        }
    }

    private String getTimestamp() {
        return new java.sql.Timestamp(new java.util.Date().getTime()) + "> ";
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="inner class CurrentPlayerState">
    class CurrentPlayerState {

        private boolean hasThrownFour;
        private boolean hasRequestedCard;

        public CurrentPlayerState() {
            hasThrownFour = false;
            hasRequestedCard = false;
        }

        public boolean hasThrownFour() {
            return hasThrownFour;
        }

        public void setHasThrownFour(boolean hasThrownFour) {
            this.hasThrownFour = hasThrownFour;
        }

        public boolean hasRequestedCard() {
            return hasRequestedCard;
        }

        public void setHasRequestedCard(boolean hasRequestedCard) {
            this.hasRequestedCard = hasRequestedCard;
        }
    }
    //</editor-fold>
}
