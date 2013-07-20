/*
 */
package web.servlets.game;

import engine.Engine;
import engine.cards.Card;
import engine.players.Player;
import engine.players.exceptions.InvalidFourException;
import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import web.playerinterface.WebFourPicker;
import web.servlets.containers.SessionPlayer;
import web.servlets.general.GoFishServlet;

/**
 *
 * @author adamnark
 */
@WebServlet(name = "ActionServlet", urlPatterns = {"/do"})
public class ActionServlet extends GoFishServlet {

    private static final String PARAM_ACTION = "a";
    private static final String PARAM_CARDS_CLCTN = "cards[]";

    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter(PARAM_ACTION);
        resetTimeStamp(request);
        if (action != null) {
            switch (action) {
                case COMMAND_SKIP:
                    handleCommandSkip(request, response);
                    break;
                case COMMAND_QUIT:
                    handleCommandQuit(request, response);
                    break;
                case COMMAND_THROW:
                    handleCommandThrow(request, response);
                    break;
                default:
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ActionServlet: Unknown action");
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ActionServlet: No action provided");
        }

    }

    private void handleCommandSkip(HttpServletRequest request, HttpServletResponse response) {
        SessionPlayer sp = getSessionPlayer(request.getSession(true));
        if (sp != null) {
            Player p = sp.getPlayer();

            Engine e = getEngineFromServletContext();
            if (p.getName().equals(e.getCurrentPlayer().getName())) {
                sp.setHasRequestedCard(false);
                sp.setHasThrownFour(false);
                e.advanceTurn();
            }
        }
    }

    private void handleCommandQuit(HttpServletRequest request, HttpServletResponse response) {
        SessionPlayer sp = getSessionPlayer(request.getSession(true));
        List<SessionPlayer> sessionPlayersList = getSessionPlayersFromServletContext();
        if (sp != null && sessionPlayersList != null) {
            sessionPlayersList.remove(sp);
            getEngineFromServletContext().removerPlayer(sp.getPlayer());
        }
    }

    private void handleCommandThrow(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<String> messages = new LinkedList<>();
        Engine engine = getEngineFromServletContext();
        String[] cards = request.getParameterValues(PARAM_CARDS_CLCTN);
        SessionPlayer sessionPlayer = getSessionPlayer(request.getSession(true));
        if (sessionPlayer != null) {
            boolean isSessionPlayerCurrentPlayer = engine.getCurrentPlayer().equals(sessionPlayer.getPlayer());
            if (cards == null) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ActionServlet.handleCommandThrow: no cards argument");
            } else if (!isSessionPlayerCurrentPlayer) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ActionServlet.handleCommandThrow: it's not your turn");
            } else {
                List<Card> clickedCards = retrieveCardsById(cards, engine);
                if (engine.getCurrentPlayer().isHuman()) {
                    ((WebFourPicker) engine.getCurrentPlayer().getFourPicker()).setCardsToThrow(clickedCards);
                }
                try {
                    engine.currentPlayerThrowFour();
                    sessionPlayer.setHasThrownFour(true);
                    messages.add("Four cards thrown.");
                } catch (InvalidFourException ex) {
                    messages.add(ex.getMessage());
                }

                respondJSONObject(messages, response);
            }
        }
    }

    private List<Card> retrieveCardsById(String[] cards, Engine engine) {
        List<Card> lst = new LinkedList<>();
        if (cards != null) {
            for (String string : cards) {
                Card c = engine.getCardByName(string);
                lst.add(c);
            }
        }
        return lst;
    }

    private void resetTimeStamp(HttpServletRequest request) {
        SessionPlayer sp = getSessionPlayer(request.getSession());
        if (sp != null) {
            Date d = new Date();
            long now = d.getTime();
            sp.setTimeStamp(now);
        }
    }
}
