/*
 */
package web.servlets.game;

import engine.Engine;
import engine.cards.Card;
import engine.cards.Series;
import engine.players.Player;
import engine.request.Request;
import engine.request.RequestValidator;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import web.playerinterface.WebRequestMaker;
import web.servlets.containers.SessionPlayer;
import web.servlets.general.GoFishServlet;

/**
 *
 * @author adamnark
 */
@WebServlet(name = "RequestServlet", urlPatterns = {"/request"})
public class RequestServlet extends GoFishServlet {

    private static final String PARAM_Q = "q";
    private static final String PARAM_Q_REQUEST = "req";
    private static final String PARAM_OTHER_PLAYER = "op";
    private static final String PARAM_SERIES = "series[]";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final String q = request.getParameter(PARAM_Q);
        if (q != null && q.equals(PARAM_Q_REQUEST)) {
            respondToCardRequest(request, response);

        } else {
            super.doGet(request, response);
        }
    }

    private Request makeRequestObjectFromParameters(HttpServletRequest request) {
        Player otherPlayer = getOtherPlayerFromParameters(request);
        Card cardIWant = getCardIWantFromParameters(request);
        return new Request(otherPlayer, cardIWant);
    }

    @Override
    protected void printContent(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        out.println("<h3>");
        out.println("Let's make a request!");
        out.println("</h3>");
        out.println("<p>Pick a player:</p>");
        printAvailablePlayersRadios(out);
        out.println("<hr>");
        printAvailableSeriesCheckboxes(out);
        out.println("<button id=\"requestbtn\" class=\"btn btn-primary\">Request!</button>");
        out.println("</form>");
        out.println("<hr>");
        out.println("<div id='message'></div>");
    }

    private void printAvailableSeriesCheckboxes(PrintWriter out) {
        Engine engine = getEngineFromServletContext();
        out.println("<p>");
        out.println("Pick " + engine.getCurrentPlayer().getHand().getCards().get(0).getSeries().size() + " serieses:");
        out.println("</p>");
        for (Series series : engine.getAvailableSeries()) {
            out.println("<label class='checkbox'>");
            out.println("<span class='ser" + engine.getAvailableSeries().indexOf(series) + "'>");
            out.println("<input type='checkbox' name='series' value='" + series.getName() + "'>" + series.getName());
            out.println("</span>");
            out.println("</label>");
        }
    }

    private void printAvailablePlayersRadios(PrintWriter out) {
        List<Player> availablePlayers = getAvailablePlayersFromEngine();
        for (Player player : availablePlayers) {
            out.println("<label class='radio'>");
            out.println("<input type='radio' name='player' value='" + player.getName() + "'>" + player.getName());
            out.println("</label>");
        }
    }

    private List<Player> getAvailablePlayersFromEngine() {
        Engine engine = getEngineFromServletContext();
        List<Player> availablePlayers;
        availablePlayers = new LinkedList<>();
        for (Player player : engine.getPlayers()) {
            if (!player.equals(engine.getCurrentPlayer()) && player.isPlaying()) {
                availablePlayers.add(player);
            }
        }

        return availablePlayers;
    }

    private Player getOtherPlayerFromParameters(HttpServletRequest request) {
        String playerName = request.getParameter(PARAM_OTHER_PLAYER);
        Engine e = getEngineFromServletContext();
        return playerName != null ? e.getPlayerByName(playerName) : null;
    }

    private Card getCardIWantFromParameters(HttpServletRequest request) {
        String[] seriesNames = request.getParameterValues(PARAM_SERIES);
        if (seriesNames == null) {
            return null;
        }

        Card card = new Card();
        for (String seriesName : seriesNames) {
            card.addSeries(new Series(seriesName));
        }

        return card;
    }

    @Override
    protected void printMoreScripts(PrintWriter out) {
        out.println("<script src=\"js/request.js\"></script>");
    }

    private void respondToCardRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Request r = makeRequestObjectFromParameters(request);
        Engine engine = getEngineFromServletContext();
        boolean valid;
        String message = "";

        try {
            RequestValidator.tryValidateRequest(r, engine.getCurrentPlayer().getHand());
            valid = true;
            makeTheRequest(r, request, response);
        } catch (RequestValidator.OwnSeriesMissingException ex) {
            valid = false;
            message = "Pick at least one series that exists in your hand.";
        } catch (RequestValidator.InvalidCardException ex) {
            valid = false;
            message = "Pick exactly " + engine.getCurrentPlayer().getHand().getCards().get(0).getSeries().size() + " serieses.";
        } catch (RequestValidator.MissingPlayerException ex) {
            valid = false;
            message = "Don't forget to pick a player.";
        }

        CardRequestAnswer answer = new CardRequestAnswer(valid, message);
        respondJSONObject(answer, response);
    }

    private void makeTheRequest(Request r, HttpServletRequest request, HttpServletResponse response) {
        Engine engine = getEngineFromServletContext();
        SessionPlayer sp = getSessionPlayer(request.getSession(true));
        if (sp != null) {
            ((WebRequestMaker) engine.getCurrentPlayer().getRequestMaker()).setRequest(r);
            boolean cardWasTaken = engine.currentPlayerMakeRequest();
            boolean oneMoreTime = engine.getGameSettings().isAllowMutipleRequests();
            sp.setHasRequestedCard(!(oneMoreTime && cardWasTaken));
        }
    }

    private class CardRequestAnswer {

        boolean success;
        String message;

        public CardRequestAnswer(boolean success, String message) {
            this.success = success;
            this.message = message;
        }

        public CardRequestAnswer() {
        }

        public boolean isSuccess() {
            return success;
        }

        public void setSuccess(boolean success) {
            this.success = success;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
