/*
 */
package web.servlets.general;

import engine.Engine;
import engine.factory.EngineFactory;
import engine.players.Player;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import web.servlets.containers.SessionPlayer;
import static web.servlets.general.GoFishServlet.ATTR_CURRENT_SETTING_SERVLET;
import static web.servlets.general.GoFishServlet.ATTR_ENGINE;
import static web.servlets.general.GoFishServlet.ATTR_LIST_OF_HUMAN_PLAYERS;
import static web.servlets.loadgame.LoadGameServlet.ATTR_SETTINGS_FROM_XML;
import web.servlets.newgame.NewGameServlet;
import static web.servlets.newgame.NewGameServlet.ATTR_LAST_MANUAL_CONFIGURATION;
import xml.SettingsFromXML;

/**
 *
 * @author adamnark
 */
@WebServlet(name = "ReplayServlet", urlPatterns = {"/gameover"})
public class ReplayServlet extends GoFishServlet {

    private String winnerName;
    private int winnerScore;
    private static final String PARAM_REPLAY = "replay";
    private static final String PARAM_REPLAY_YES = "y";
    private static final String PARAM_REPLAY_NO = "n";

    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String replay = request.getParameter(PARAM_REPLAY);
        if (replay != null && replay.equals(PARAM_REPLAY_YES)) {
            regenerateEngine();
        } else if (replay != null && replay.equals(PARAM_REPLAY_NO)) {
            clearAttributes();
        } else {
            getGameStats();
            super.processRequest(request, response);
        }
    }

    @Override
    protected void printContent(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        out.println("<div class='span6'>");

        out.println("<div class='row'>");
        out.println("<div class='span6 text-center'>");
        out.println("<h1>");
        out.println("Game over!");
        out.println("</h1>");
        out.println("</div>");
        out.println("</div>");
        out.println("");
        printWinner(out);
        printReplayLink(out);
        printNewGameLink(out);
        //printHomeLink(out);
        out.println("</div>");
        //printHiddenForm(out);
    }

    @Override
    protected void printMoreScripts(PrintWriter out) {
        out.print("<script src='js/gameover.js'></script>");
    }

    private void printWinner(PrintWriter out) {
        out.println("<div class='row'>");
        out.println("<div class='span6 text-center'>");
        out.println("<h3>");
        out.println(this.winnerName + " won with " + this.winnerScore + " points!");
        out.println("</h3>");
        out.println("</div>");
        out.println("</div>");
    }

    private void printNewGameLink(PrintWriter out) {
        out.println("<div class='row'>");
        out.println("<div class='span6 text-center'>");
        out.println("<div class='btn-toolbar'>");
        out.println("<a id='kill' href='home' class='btn-large btn-primary btn-block'>");
        out.println("Start a new game");
        out.println("</a>");
        out.println("</div>");
        out.println("</div>");
        out.println("</div>");
    }

//    private void printHomeLink(PrintWriter out) {
//        out.println("<div class='row'>");
//        out.println("<div class='span6 text-center'>");
//        out.println("<div class='btn-toolbar'>");
//        out.println("<a href='home' class='btn-large btn-success btn-block'>");
//        out.println("Go back to main menu");
//        out.println("</a>");
//        out.println("</div>");
//        out.println("</div>");
//        out.println("</div>");
//    }
    private void printReplayLink(PrintWriter out) {
        out.println("<div class='row'>");
        out.println("<div class='span6 text-center'>");
        out.println("<div class='btn-toolbar'>");
        out.println("<a id='replay' class='btn-large btn-danger btn-block'>");
        out.println("Start a game with the same settings!");
        out.println("</a>");
        out.println("</div>");
        out.println("</div>");
        out.println("</div>");
    }

    private Engine regenerateEngineFromLastConfiguration() {
        Engine engine = null;
        NewGameServlet.GameMetadata lastConfiguration = (NewGameServlet.GameMetadata) this.getServletContext().getAttribute(ATTR_LAST_MANUAL_CONFIGURATION);
        if (lastConfiguration != null) {
            engine = EngineFactory.generateEngine(
                    lastConfiguration.getPlayers(),
                    lastConfiguration.getGameSettings());
            clearHumanNames(engine);
        }

        return engine;
    }

    private Engine regenerateEngineFromXML() {
        Engine engine = null;
        SettingsFromXML settingsFromXML = (SettingsFromXML) this.getServletContext().getAttribute(ATTR_SETTINGS_FROM_XML);
        if (settingsFromXML != null) {
            engine = settingsFromXML.makeEngine();
            this.getServletContext().setAttribute(ATTR_LIST_OF_HUMAN_PLAYERS, engine.getHumanNames());
        }

        return engine;
    }

    private void regenerateEngine() {
        Engine engineManual = this.regenerateEngineFromLastConfiguration();
        Engine engineXML = this.regenerateEngineFromXML();
        Engine lastEngine = engineManual != null ? engineManual : engineXML;
        this.getServletContext().setAttribute(ATTR_ENGINE, lastEngine);
        List<SessionPlayer> lst = (List<SessionPlayer>) this.getServletContext().getAttribute(ATTR_SESSION_PLAYERS_LIST);
        lst.clear();
    }

    private void getGameStats() {
        Engine engine = getEngineFromServletContext();
        this.winnerName = engine.getWinner().getName();
        this.winnerScore = engine.getWinner().getScore();
    }

    private void clearAttributes() {
        List<SessionPlayer> lst = getSessionPlayersFromServletContext();
        lst.clear();
        this.getServletContext().setAttribute(ATTR_LIST_OF_HUMAN_PLAYERS, null);
        this.getServletContext().setAttribute(ATTR_ENGINE, null);
        this.getServletContext().setAttribute(ATTR_LAST_MANUAL_CONFIGURATION, null);
        this.getServletContext().setAttribute(ATTR_SETTINGS_FROM_XML, null);
        this.getServletContext().setAttribute(ATTR_CURRENT_SETTING_SERVLET, null);
    }

    private void clearHumanNames(Engine engine) {
        for (Player player : engine.getPlayers()) {
            if (player.isHuman()) {
                player.setName("");
            }
        }
    }
}
