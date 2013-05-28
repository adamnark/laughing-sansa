/*
 */
package web.servlets;

import engine.Engine;
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

    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.engine = (Engine) this.getServletContext().getAttribute(ATTR_ENGINE);
        super.processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Request r = makeRequestObjectFromParameters(request);
        RequestValidator.validateRequest(r, engine.getCurrentPlayer().getHand());
        addRequestObjectToServletContext(r);
        forwardToPlayServlet(request);
    }

    private Request makeRequestObjectFromParameters(HttpServletRequest request) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void addRequestObjectToServletContext(Request r) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void forwardToPlayServlet(HttpServletRequest request) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    protected void printContent(PrintWriter out) {
        if (engine != null) {
            out.println("<h3>");
            out.println("Let's make a request!");
            out.println("</h3>");


            out.println("<form name='requestform' action='play' method='post' class='form' >");
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
}
