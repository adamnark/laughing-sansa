/*
 */
package web.servlets;

import engine.Engine;
import engine.Factory.EngineFactory;
import engine.Factory.PlayerItem;
import engine.GameSettings;
import engine.Validator;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import web.servlets.exceptions.PlayerNameExistsException;
import web.servlets.exceptions.PlayerNameIsEmptyException;
import web.servlets.exceptions.TooManyPlayersException;
import web.servlets.printers.ErrorPrinter;
import web.servlets.printers.NavbarPrinter;
import web.servlets.printers.PlayerItemPrinter;

/**
 *
 * @author adamnark
 */
@WebServlet(name = "NewGameServlet", urlPatterns = {"/newgame"})
public class NewGameServlet extends GoFishServlet {

    private static final String PARAM_PLAYERNAME = "playername";
    private static final String PARAM_IS_HUMAN = "ishuman";
    private static final String PARAM_ALLOW_MULTIPLE = "allowmultiple";
    private static final String PARAM_FORCE_SHOW = "forceshow";
    private static final String PARAM_ACTION = "action";
    private static final String PARAM_ACTION_ADD = "Add";
    private static final String PARAM_ACTION_START = "start";
    public static final String ATTR_LAST_CONFIGURATION = "attr-last-conf";
    private List<PlayerItem> players;
    private List<String> errors;
    private GameMetadata lastConfiguration;

    public NewGameServlet() {
        super();
        players = new LinkedList<>();
        errors = new LinkedList<>();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.errors.clear();
        this.players.clear();
        //addDefaultPlayers();

        super.doGet(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.errors.clear();
        switch (request.getParameter(PARAM_ACTION)) {
            case PARAM_ACTION_ADD:
                tryAddPlayer(request);
                break;
            case PARAM_ACTION_START:
                Engine engine = generateEngine(request);
                if (validateEngine(engine)) {
                    setEngineContextAttribute(request, engine);
                    forwardRequestToPlayServlet(request, response);
                }
                break;
            default:
                break;
        }
        super.doPost(request, response);
    }

    @Override
    protected NavbarPrinter.NavbarItems getActiveItem() {
        return NavbarPrinter.NavbarItems.NEWGAME;
    }

    @Override
    protected void printContent(PrintWriter out) {
        out.println("<div class='row'>");
        printForm(out);
        printListOfPlayers(out);
        out.println("</div>");
        ErrorPrinter.printErrors(out, this.errors);
    }

    private void printForm(PrintWriter out) {
        out.println("<div class='span6'>");
        out.println("<form class='form-horizontal' action='newgame' method='post'>");
        out.println("<fieldset>");
        out.println("");
        out.println("<h3>Start a new game!</h3>");
        out.println("<ul class=\"nav nav-list\"><li class=\"divider\"></li></ul>");
        out.println("");
        out.println("<div class='control-group'>");
        out.println("<label class='control-label' for='" + PARAM_PLAYERNAME + "'>Add player</label>");
        out.println("<div class='controls'>");
        out.println("<div class='input-append'>");
        out.println("<input name='" + PARAM_PLAYERNAME + "' type='text' id='" + PARAM_PLAYERNAME + "' class='input-medium' placeholder='Name'>");
        out.println("<input class='btn' type='submit' name='" + PARAM_ACTION + "' value='" + PARAM_ACTION_ADD + "' />");
        out.println("</div>");
        out.println("<label class='checkbox'><input type='checkbox' name='" + PARAM_IS_HUMAN + "' />Human player</label>");
        out.println("");
        out.println("</div>");
        out.println("</div>");
        out.println("");
        out.println("<div class='control-group'>");
        out.println("<label class='control-label' for='gameoptions'>Game Options</label>");
        out.println("<div class='controls'>");
        out.println("<label class='checkbox'><input type='checkbox' name='" + PARAM_ALLOW_MULTIPLE + "' />");
        out.println("Allow multiple requests");
        out.println("</label>");
        out.println("<label class='checkbox'><input type='checkbox' name='" + PARAM_FORCE_SHOW + "' checked='checked'/>");
        out.println("Force show of cards");
        out.println("</label>");
        out.println("</div>");
        out.println("</div>");
        out.println("");
        out.println("<div class='control-group form-actions'>");
        out.println("<label class='control-label' for='buttonSubmit'></label>");
        out.println("<div class='controls'>");
        out.println("<button id='buttonSubmit'  name='" + PARAM_ACTION + "' value='" + PARAM_ACTION_START + "'  class='btn btn-primary' type='submit'>Start Game!</button>");
        out.println("</div>");
        out.println("</div>");
        out.println("");
        out.println("</fieldset>");
        out.println("</form>");
        out.println("</div>");
    }

    private void printListOfPlayers(PrintWriter out) {
        out.println("<div class='span3'>");
        out.println("<h3>List of players:</h3>");
        out.println("<ul class='nav nav-list'><li class='divider'></li>");
        for (PlayerItem playerItem : players) {
            printPlayerItem(out, playerItem);
        }

        out.println("</ul>");
        out.println("");
        out.println("</div>");
    }

    private void printPlayerItem(PrintWriter out, PlayerItem item) {
        out.println("<li>");

        out.println("<div style='display:inline-block'>");
        out.println(PlayerItemPrinter.makeImgTag(item));
        out.println("</div>");

        out.println("<div style='display:inline-block'>");
        out.println(item.getPlayerName());
        out.println("</div>");

        out.println("</li>");
    }

    private void addPlayerFromRequest(HttpServletRequest request) throws PlayerNameExistsException, PlayerNameIsEmptyException, TooManyPlayersException {
        String name = request.getParameter(PARAM_PLAYERNAME);
        boolean isHuman = request.getParameter(PARAM_IS_HUMAN) != null;
        validateName(name);
        this.players.add(new PlayerItem(name, isHuman));
    }

    private void validateName(String name) throws PlayerNameExistsException, PlayerNameIsEmptyException, TooManyPlayersException {
        if (name.isEmpty()) {
            throw new PlayerNameIsEmptyException();
        }

        if (this.nameExists(name)) {
            throw new PlayerNameExistsException(name);
        }

        if (this.players.size() >= 6) {
            throw new TooManyPlayersException();
        }
    }

    private boolean nameExists(String name) {
        boolean val = false;
        for (PlayerItem playerItem : players) {
            if (name.equals(playerItem.getPlayerName())) {
                val = true;
                break;
            }
        }

        return val;
    }

    private void tryAddPlayer(HttpServletRequest request) {
        try {
            this.addPlayerFromRequest(request);
        } catch (PlayerNameExistsException ex) {
            this.errors.add(ex.getPlayerName() + " is taken!");
        } catch (PlayerNameIsEmptyException ex) {
            this.errors.add("Nothing is not a good name for you. Type something.");
        } catch (TooManyPlayersException ex) {
            this.errors.add("Enough players already! Start the game!");
        }
    }

    private Engine generateEngine(HttpServletRequest request) {
        GameSettings gs = generateGameSettings(request);
        this.lastConfiguration = new GameMetadata(gs, players);
        this.getServletContext().setAttribute(ATTR_LAST_CONFIGURATION, lastConfiguration);
        
        return EngineFactory.generateEngine(players, gs);
    }

    private GameSettings generateGameSettings(HttpServletRequest request) {
        GameSettings gs = new GameSettings();
        boolean allowMultiple = request.getParameter(PARAM_ALLOW_MULTIPLE) != null;
        boolean forceShow = request.getParameter(PARAM_FORCE_SHOW) != null;

        gs.setAllowMutipleRequests(allowMultiple);
        gs.setForceShowOfSeries(forceShow);

        return gs;
    }

    private boolean validateEngine(Engine engine) {
        Validator v = new Validator(engine);
        if (!v.validateEngineState()) {
            this.errors.addAll(v.getErrors());
            return false;
        }

        return true;
    }

    
    
    private void setEngineContextAttribute(HttpServletRequest request, Engine engine) {
        this.getServletContext().setAttribute(ATTR_ENGINE, engine);
    }

    private void forwardRequestToPlayServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/play").forward(request, response);
    }

    private void addDefaultPlayers() {
        boolean isHuman = true;
        PlayerItem player1 = new PlayerItem("Moxie", isHuman);
        PlayerItem player2 = new PlayerItem("Boxie", !isHuman);
        PlayerItem player3 = new PlayerItem("Foxie", !isHuman);
        this.players.add(player1);
        this.players.add(player2);
        this.players.add(player3);
    }

    public class GameMetadata {

        private GameSettings gameSettings;
        private List<PlayerItem> players;

        public GameMetadata(GameSettings gameSettings, List<PlayerItem> players) {
            this.gameSettings = gameSettings;
            this.players = new LinkedList<>(players);
        }

        public GameSettings getGameSettings() {
            return gameSettings;
        }

        public List<PlayerItem> getPlayers() {
            return players;
        }
    }
}
