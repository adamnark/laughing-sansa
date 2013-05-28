/*
 */
package web.servlets;

import engine.Engine;
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
import static web.servlets.GoFishServlet.ATTR_ENGINE;
import engine.request.Request;
import engine.request.RequestValidator;

/**
 *
 * @author adamnark
 */
@WebServlet(name = "RequestServlet", urlPatterns = {"/request"})
public class RequestServlet extends GoFishServlet {

    private Engine engine;
    //TODO -- add error reporting
    private List<String> errors = new LinkedList<>();
    private boolean wasRequestFormPosted;
    private static final String FORM_NAME = "requestform";
    public static final String ATTR_REQUEST = "request-attr";

    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.engine = (Engine) this.getServletContext().getAttribute(ATTR_ENGINE);
        String action = request.getParameter("action");
        this.wasRequestFormPosted = action != null && action.equals(FORM_NAME);
        super.processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (wasRequestFormPosted) {
            Request r = makeRequestObjectFromParameters(request);
            if (r != null && RequestValidator.validateRequest(r, engine.getCurrentPlayer().getHand())) {
                addRequestObjectToServletContext(r);
                forwardToPlayServlet(request);
            } else {
                super.doPost(request, response);
            }
        }

        super.doPost(request, response);
    }

    private Request makeRequestObjectFromParameters(HttpServletRequest request) {
        Player otherPlayer = getOtherPlayerFromParameters(request);
        Card cardIWant = getCardIWantFromParameters(request);
        return new Request(otherPlayer, cardIWant);
    }

    private void addRequestObjectToServletContext(Request r) {
        this.getServletContext().setAttribute(ATTR_REQUEST, r);
    }

    private void forwardToPlayServlet(HttpServletRequest request) {
        request.getRequestDispatcher("play");
    }

    @Override
    protected void printContent(PrintWriter out) {
        if (engine != null) {
            out.println("<h3>");
            out.println("Let's make a request!");
            out.println("</h3>");

            out.println("<form name='" + FORM_NAME + "' method='post' class='form' >");
            out.println("<input type='hidden' name='action' value='" + FORM_NAME + "'>");
            printAvailablePlayersRadios(out);
            out.println("<hr>");
            printAvailableSeriesCheckboxes(out);
            out.println("</form>");
        }
    }

    private void printAvailableSeriesCheckboxes(PrintWriter out) {
        out.println("<p>");
        out.println("Pick X serieses:");
        out.println("</p>");
        for (Series series : engine.getAvailableSeries()) {
            out.println("<label class='checkbox'>");
            out.println("<input type='checkbox' name='series' value='" + series.getName() + "'>" + series.getName());
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
        String playerName = request.getParameter("player");
        return engine.getPlayerByName(playerName);
    }

    private Card getCardIWantFromParameters(HttpServletRequest request) {
        String[] seriesNames = request.getParameterValues("series");
        Card card = new Card();
        for (String seriesName : seriesNames) {
            card.addSeries(new Series(seriesName));
        }

        return card;
    }
}
