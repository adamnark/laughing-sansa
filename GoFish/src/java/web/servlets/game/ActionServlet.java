/*
 */
package web.servlets.game;

import engine.Engine;
import engine.players.Player;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import web.servlets.general.GoFishServlet;

/**
 *
 * @author adamnark
 */
@WebServlet(name = "ActionServlet", urlPatterns = {"/do"})
public class ActionServlet extends GoFishServlet {

    private static final String PARAM_ACTION = "a";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter(PARAM_ACTION);
        if (action != null) {
            switch (action) {
                case COMMAND_SKIP:
                    handleCommandSkip(request, response);
                    break;
                default:
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ActionServlet: Unknown action");
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ActionServlet: No action provided");
        }

    }

    private void handleCommandSkip(HttpServletRequest request, HttpServletResponse response) {
        Player p = getSessionPlayer(request.getSession()).getPlayer();
        Engine e = getEngineFromServletContext();
        if (p.equals(e.getCurrentPlayer())) {
            e.advanceTurn();
        }
    }
}
