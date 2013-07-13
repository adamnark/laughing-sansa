/*
 */
package web.servlets.loadgame;

import com.google.gson.Gson;
import engine.Engine;
import engine.players.Player;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import web.servlets.containers.SessionPlayer;
import web.servlets.general.GoFishServlet;

/**
 *
 * @author adamnark
 */
@WebServlet(name = "LoadWelcome", urlPatterns = {"/LoadWelcome"})
public class LoadWelcomeServlet extends GoFishServlet {

    private LinkedList<String> availablePlayerNames = new LinkedList<>();
    private static final String PARAM_ACTION = "action";
    private static final String PARAM_ACTION_GET_AVAILABLE_PLAYERS = "get_available_players";
    private static final String PARAM_PICKED_NAME = "selectnames";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getAvailablePlayersFromServletContext();
        final String action = request.getParameter(PARAM_ACTION);
        final String selectedName = request.getParameter(PARAM_PICKED_NAME);

        if (action != null && action.equals(PARAM_ACTION_GET_AVAILABLE_PLAYERS)) {
            respondAvailablePlayers(response);
        } else if (selectedName != null) {
            respondToNameSelection(response, request, selectedName);
        } else {
            super.doGet(request, response);
        }
    }

    private void respondToNameSelection(HttpServletResponse response, HttpServletRequest request, String selectedName) throws IOException {
        Gson gson = new Gson();
        response.setContentType("application/json");


        Engine e = (Engine) this.getServletContext().getAttribute(ATTR_ENGINE);
        if (e != null) {
            Player p = e.getPlayerByName(selectedName);
            if (p != null && playerNameIsVacant(selectedName)) {
                if (tryAddSessionPlayer(request, p)) {
                    response.getWriter().print(gson.toJson("/lobby"));
                } else {
                    response.getWriter().print(gson.toJson("error adding you as a player. "));
                }
            } else {
                response.getWriter().print(gson.toJson("That name is taken! Too slow..."));
            }
        } else {
            response.getWriter().print(gson.toJson("no engine in context!"));
        }
    }

    @Override
    protected void printContent(PrintWriter out) {
        out.println("<div class='row'>");

        out.println("<div class='span7' id='message'>");
        out.println("</div>");

        out.println("<div class='span7'>");
        printForm(out);
        out.println("</div>");
        out.println("</div>");
    }

    private void printForm(PrintWriter out) {
//        out.println("<form class='form-horizontal' method='post' action='#'>");
        out.println("<div class='form-horizontal'>");
        out.println("<fieldset>");
        out.println("");
        out.println("<!-- Form Name -->");
        out.println("<legend>Pick a name for your player</legend>");
        out.println("");
        out.println("<!-- Select Basic -->");
        out.println("<div class='control-group'>");
        out.println("<label class='control-label' for='selectnames'>Player Name:</label>");
        out.println("<div class='controls'>");
        out.println("<select id='selectnames' name='selectnames' class='input-xlarge'>");
        out.println("</select>");
        out.println("</div>");
        out.println("</div>");
        out.println("");
        out.println("<!-- Button -->");
        out.println("<div class='control-group'>");
        out.println("<div class='controls'>");
        out.println("<button id='submitbutton' name='submitbutton' class='btn btn-primary'>Pick Name</button>");
        out.println("</div>");
        out.println("</div>");
        out.println("");
        out.println("</fieldset>");
        out.println("</div>");
//        out.println("</form>");
        out.println("");

    }

    @Override
    protected void printMoreScripts(PrintWriter out) {
        super.printMoreScripts(out);
        out.println("<script src='js/loadwelcome.js'></script>");
    }

    private void getAvailablePlayersFromServletContext() {
        List<String> playerNames = (List<String>) this.getServletContext().getAttribute(ATTR_LIST_OF_HUMAN_PLAYERS);
        if (playerNames != null) {
            this.getServletContext().removeAttribute(ATTR_LIST_OF_HUMAN_PLAYERS);
            for (String string : playerNames) {
                this.availablePlayerNames.add(string);
            }
        }
    }

    private void respondAvailablePlayers(HttpServletResponse response) throws IOException {
        respondJSONObject(availablePlayerNames, response);
    }

    private boolean playerNameIsVacant(String selectedName) {
        List<SessionPlayer> lst = (List<SessionPlayer>) this.getServletContext().getAttribute(ATTR_SESSION_PLAYERS_LIST);
        for (SessionPlayer sessionPlayer : lst) {
            if (sessionPlayer.getPlayer().getName().equals(selectedName)) {
                return false;
            }
        }

        return true;
    }

    private boolean tryAddSessionPlayer(HttpServletRequest request, Player p) {
        HttpSession sesh = request.getSession(true);
        SessionPlayer seshplayer = new SessionPlayer(p, sesh);
        List<SessionPlayer> lst = (List<SessionPlayer>) this.getServletContext().getAttribute(ATTR_SESSION_PLAYERS_LIST);

        boolean alreadyConnected = isAlreadyConnected(lst, seshplayer);
        boolean nameTaken = !this.availablePlayerNames.contains(p.getName());
        if (alreadyConnected || nameTaken) {
        } else {
            lst.add(seshplayer);
            this.availablePlayerNames.remove(p.getName());
            return true;
        }

        return false;
    }

    private boolean isAlreadyConnected(List<SessionPlayer> lst, SessionPlayer seshplayer) {
        for (SessionPlayer connectedPlayer : lst) {
            if (connectedPlayer.getSession().equals(seshplayer.getSession())) {
                return true;
            }
        }
        return false;
    }
}
