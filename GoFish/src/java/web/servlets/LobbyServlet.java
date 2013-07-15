/*
 */
package web.servlets;

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
import web.servlets.containers.CardContainer;
import web.servlets.containers.SessionPlayer;
import web.servlets.general.GoFishServlet;

/**
 *
 * @author adamnark
 */
@WebServlet(name = "lobby", urlPatterns = {"/lobby"})
public class LobbyServlet extends GoFishServlet {

    private static final String PARAM_ACTION = "action";
    private static final String PARAM_ACTION_LIST = "list";
    private static final String PARAM_ACTION_IS_STARTED = "is_started";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter(PARAM_ACTION);
        if (action != null) {
            if (action.equals(PARAM_ACTION_LIST)) {
                respondListConnectedNames(response);
            } else if (action.equals(PARAM_ACTION_IS_STARTED)) {
                respondIsStarted(response);
            } else {
                super.doGet(request, response);
            }
        } else {
            super.doGet(request, response);
        }
    }

    @Override
    protected void printContent(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        out.println("<div class='row'>");
        out.println("<div class='span7 alert alert-info' id='message'>");
        out.println("Players are connecting..");
        out.println("</div>");
        out.println("");
        out.println("<div class='span7'>");
        out.println("<ul id='players'>");
        out.println("</ul>");
        out.println("</div>");
        out.println("</div>");
    }

    @Override
    protected void printMoreScripts(PrintWriter out) {
        super.printMoreScripts(out);
        out.println("<script src='js/lobby.js'></script>");
    }

    private void respondListConnectedNames(HttpServletResponse response) throws IOException {
        List<String> names = new LinkedList<>();
        List<SessionPlayer> players = (List<SessionPlayer>) this.getServletContext().getAttribute(ATTR_SESSION_PLAYERS_LIST);
        for (SessionPlayer sessionPlayer : players) {
            names.add(sessionPlayer.getPlayer().getName());
        }

        respondJSONObject(names, response);
    }

    private void respondIsStarted(HttpServletResponse response) throws IOException {
        Engine e = (Engine) this.getServletContext().getAttribute(ATTR_ENGINE);
        List<SessionPlayer> connectedPlayers = (List<SessionPlayer>) this.getServletContext().getAttribute(ATTR_SESSION_PLAYERS_LIST);
        int totalPlayers = getNumOfHumanPlayers(e);
        int numOfConnectedPlayers = connectedPlayers.size();
        boolean isAllConnected = totalPlayers == numOfConnectedPlayers;
        
        if (isAllConnected){
            e.startGame();
            CardContainer.initClass(e);
        }
        
        respondJSONObject(isAllConnected, response);
    }

    private int getNumOfHumanPlayers(Engine e) {
        int n = 0;
        for (Player player : e.getPlayers()) {
            if (player.isHuman()) {
                n++;
            }
        }

        return n;
    }
}
