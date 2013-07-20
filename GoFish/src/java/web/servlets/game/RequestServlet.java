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
import web.servlets.PlayServlet;
import web.servlets.general.GoFishServlet;
import web.servlets.printers.ErrorPrinter;

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

    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.engine = (Engine) this.getServletContext().getAttribute(PlayServlet.ATTR_ENGINE);
        String action = request.getParameter("action");
        this.wasRequestFormPosted = action != null && action.equals(FORM_NAME);
        if (!wasRequestFormPosted) {
            super.processRequest(request, response);
        } else {
            this.errors.clear();
            Request r = makeRequestObjectFromParameters(request);
            if (r == null) {
                super.processRequest(request, response);
            } else {
                try {
                    RequestValidator.tryValidateRequest(r, engine.getCurrentPlayer().getHand());
                    addRequestObjectToServletContext(r);
                    forwardToPlayServlet(request, response);
                } catch (RequestValidator.OwnSeriesMissingException ex) {
                    this.errors.add("Pick at least one series that exists in your hand.");
                } catch (RequestValidator.InvalidCardException ex) {
                    this.errors.add("Pick exactly " + engine.getCurrentPlayer().getHand().getCards().get(0).getSeries().size() + " serieses.");
                } catch (RequestValidator.MissingPlayerException ex) {
                    this.errors.add("Don't forget to pick a player.");
                }
            }
        }
        super.processRequest(request, response);
    }

    private Request makeRequestObjectFromParameters(HttpServletRequest request) {
        Player otherPlayer = getOtherPlayerFromParameters(request);
        Card cardIWant = getCardIWantFromParameters(request);
        return new Request(otherPlayer, cardIWant);
    }

    private void addRequestObjectToServletContext(Request r) {
        this.getServletContext().setAttribute(PlayServlet.ATTR_REQUEST, r);
    }

    private void forwardToPlayServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("play").forward(request, response);
    }

    @Override
    protected void printContent(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        if (engine != null) {
            out.println("<h3>");
            out.println("Let's make a request!");
            out.println("</h3>");

            out.println("<form name='" + FORM_NAME + "' action='request'  method='post' class='form' >");
            out.println("<input type='hidden' name='action' value='" + FORM_NAME + "'>");
            printAvailablePlayersRadios(out);
            out.println("<hr>");
            printAvailableSeriesCheckboxes(out);
            out.println("<input type='submit' value='Request!'>");
            out.println("</form>");
            out.println("<hr>");
            ErrorPrinter.printErrors(out, errors);
        }
    }

    private void printAvailableSeriesCheckboxes(PrintWriter out) {
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
        return playerName != null ? engine.getPlayerByName(playerName) : null;
    }

    private Card getCardIWantFromParameters(HttpServletRequest request) {
        String[] seriesNames = request.getParameterValues("series");
        if (seriesNames == null) {
            return null;
        }

        Card card = new Card();
        for (String seriesName : seriesNames) {
            card.addSeries(new Series(seriesName));
        }

        return card;
    }
}
