/*
 */
package web.servlets.game;

import engine.Engine;
import engine.cards.Card;
import engine.factory.PlayerItem;
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
    private static final String PARAM_QUERY_LIST_LAST_TEN_LOGS = "relog";
    private static final String PARAM_QUERY_HAND = "hand";
    private static final String PARAM_QUERY_GRAVEYARD = "graveyard";
    private static final String PARAM_QUERY_COMMANDS = "commands";
    private static final String PARAM_QUERY_IS_OVER = "isover";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (isSessionValid(request)) {
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
                    case PARAM_QUERY_LIST_LAST_TEN_LOGS:
                        respondLastLogs(request, response);
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
                    case PARAM_QUERY_IS_OVER:
                        respondIsOver(request, response);
                        break;
                    default:
                        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "StatusServlet: what is this query?");
                        break;
                }
            } else {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "StatusServlet: No query!");
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "don't know you!");
        }
    }

    private void respondPlayersList(HttpServletResponse response) throws IOException {
        Engine e = getEngineFromServletContext();
        List<PlayerItem> lst = new LinkedList<>();
        for (Player player : e.getPlayers()) {
            if (player.isPlaying()) {
                lst.add(new PlayerItem(player));
            }
        }

        super.respondJSONObject(lst, response);
    }

    private void respondCurrentPlayer(HttpServletResponse response) throws IOException {
        Engine e = getEngineFromServletContext();
        if (e != null) {
            String answer = e.getCurrentPlayer().getName();
            super.respondJSONObject(answer, response);
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    private void respondNewLogs(HttpServletRequest request, HttpServletResponse response) throws IOException {
        SessionPlayer sessionPlayer = getSessionPlayer(request.getSession(true));
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
        Engine e = getEngineFromServletContext();
        if (e != null
                && e.getGameSettings().isForceShowOfSeries()
                && e.getLastCardsThrown() != null) {
            for (Card card : e.getLastCardsThrown()) {
                lst.add(new CardContainer(card));
            }
        }
        respondJSONObject(lst, response);
    }

    private void respondCommands(HttpServletRequest request, HttpServletResponse response) throws IOException {
        LinkedList<String> lst = new LinkedList<>();
        Player currentPlayer = getEngineFromServletContext().getCurrentPlayer();
        SessionPlayer sp = getSessionPlayer(request.getSession(true));
        if (sp != null) {


            if (sp.getPlayer().getName().equals(currentPlayer.getName())) {
                if (!sp.isHasThrownFour()) {
                    lst.add(COMMAND_THROW);
                }

                if (!sp.isHasRequestedCard()) {
                    lst.add(COMMAND_REQUEST);
                }

                lst.add(COMMAND_SKIP);
            }

            lst.add(COMMAND_QUIT);
            respondJSONObject(lst, response);
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "I don't know you!");
        }
    }

    private void respondIsOver(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Engine e = getEngineFromServletContext();
        boolean isOver = e != null ? e.isGameOver() : true;
        respondJSONObject(isOver, response);
    }

    private void respondLastLogs(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession s = request.getSession(true);
        SessionPlayer sessionPlayer = getSessionPlayer(s);
        if (sessionPlayer != null) {
            LinkedList<String> log = getEngineFromServletContext().getEventQueue();
            LinkedList<String> output = new LinkedList<>();

            int numOfLogs = log.size() >= 10 ? 10 : log.size();
            for (int i = log.size() - 1; numOfLogs > 0; i--, numOfLogs--) {
                output.add(log.get(i).toString());
            }

            sessionPlayer.setLogIndex(log.size());
            super.respondJSONObject(output, response);
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "I don't know you.");
        }
    }
}
