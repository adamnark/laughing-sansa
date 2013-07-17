/*
 */
package web.servlets.game;

import engine.Engine;
import engine.Factory.PlayerItem;
import engine.cards.Card;
import engine.players.Player;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import web.servlets.containers.CardContainer;
import web.servlets.containers.SessionPlayer;
import web.servlets.general.GoFishServlet;

/**
 *
 * @author adamnark
 */
@WebServlet(name = "StatusServlet", urlPatterns = {"/status"})
public class StatusServlet extends GoFishServlet {

    private static final String PARAM_QUERY = "q";
    private static final String PARAM_QUERY_LIST_PLAYERS = "list";
    private static final String PARAM_QUERY_LIST_CURRENT_PLAYER = "current";
    private static final String PARAM_QUERY_LIST_LOGS = "log";
    private static final String PARAM_QUERY_HAND = "hand";
    private static final String PARAM_QUERY_GRAVEYARD = "graveyard";
    private static final String PARAM_QUERY_COMMANDS = "commands";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String query = request.getParameter(PARAM_QUERY);
        if (query != null) {
            switch (query) {
                case PARAM_QUERY_LIST_PLAYERS:
                    respondPlayersList(response);
                    break;
                case PARAM_QUERY_LIST_CURRENT_PLAYER:
                    respondCurrentPlayer(response);
                    break;
                case PARAM_QUERY_LIST_LOGS:
                    respondNewLogs(request, response);
                    break;
                case PARAM_QUERY_HAND:
                    respondHand(request, response);
                    break;
                case PARAM_QUERY_GRAVEYARD:
                    respondGraveyard(request, response);
                    break;
                case PARAM_QUERY_COMMANDS:
                    respondCommands(request, response);
                    break;
                default:
                        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "StatusServlet: what is this query?");
                    break;
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST,"StatusServlet: No query!");
        }
    }

    private void respondPlayersList(HttpServletResponse response) throws IOException {
        Engine e = getEngineFromServletContext();
        List<PlayerItem> lst = new LinkedList<>();

        for (Player player : e.getPlayers()) {
            lst.add(new PlayerItem(player));
        }

        super.respondJSONObject(lst, response);
    }



    private void respondCurrentPlayer(HttpServletResponse response) throws IOException {
        String answer = getEngineFromServletContext().getCurrentPlayer().getName();
        super.respondJSONObject(answer, response);
    }

    private void respondNewLogs(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession s = request.getSession();
        SessionPlayer sessionPlayer = getSessionPlayer(s);
        if (sessionPlayer != null) {
            LinkedList<String> log = getEngineFromServletContext().getEventQueue();
            LinkedList<String> output = new LinkedList<>();
            int i;
            for (i = sessionPlayer.getLogIndex(); i < log.size(); i++) {
                output.add(log.get(i).toString());
            }


            sessionPlayer.setLogIndex(i);

            super.respondJSONObject(output, response);
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "I don't know you.");
        }
    }

    private void respondHand(HttpServletRequest request, HttpServletResponse response) throws IOException {
        SessionPlayer sp = getSessionPlayer(request.getSession(true));
        LinkedList<CardContainer> lst = new LinkedList<>();

        if (sp != null) {
            for (Card card : sp.getPlayer().getHand().getCards()) {
                lst.add(new CardContainer(card));
            }
        }

        respondJSONObject(lst, response);
    }

    private void respondGraveyard(HttpServletRequest request, HttpServletResponse response) throws IOException {
        LinkedList<CardContainer> lst = new LinkedList<>();
        for (Card card : getEngineFromServletContext().getLastCardsThrown()) {
            lst.add(new CardContainer(card));
        }

        respondJSONObject(lst, response);
    }

    private void respondCommands(HttpServletRequest request, HttpServletResponse response) throws IOException {
        LinkedList<String> lst = new LinkedList<>();
        Player currentPlayer = getEngineFromServletContext().getCurrentPlayer();
        SessionPlayer sp = getSessionPlayer(request.getSession(true));
        if (sp.getPlayer().getName().equals(currentPlayer.getName())) {
            lst.add(COMMAND_SKIP);
            lst.add(COMMAND_REQUEST);
            lst.add(COMMAND_THROW);
        }
        
        lst.add(COMMAND_QUIT);

        respondJSONObject(lst, response);
    }
}
