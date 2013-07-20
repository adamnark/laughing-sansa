/*
 */
package web.servlets.newgame;

import com.google.gson.Gson;
import engine.Engine;
import engine.GameSettings;
import engine.Validator;
import engine.factory.EngineFactory;
import engine.factory.PlayerItem;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import web.servlets.general.GoFishServlet;
import web.servlets.printers.ErrorPrinter;
import web.servlets.printers.NavbarPrinter;

/**
 *
 * @author adamnark
 */
@WebServlet(name = "NewGameServlet", urlPatterns = {"/newgame"})
public class NewGameServlet extends GoFishServlet {

    private static final String PARAM_NUMBER_OF_PLAYERS = "selectnumofplayers";
    private static final String PARAM_NUMBER_OF_COMPUTER_PLAYERS = "selectnumofcomps";
    private static final String PARAM_ALLOW_MULTIPLE = "allowmultiple";
    private static final String PARAM_FORCE_SHOW = "forceshow";
    private static final String PARAM_ACTION = "action";
    private static final String PARAM_ACTION_START = "start";
    private static final String PARAM_ACTION_IS_STARTED = "is_started";
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


        if (tryForwardToCurrentServlet(request, response)) {
        } else {

            String action = request.getParameter(PARAM_ACTION);
            if (action != null && action.equals(PARAM_ACTION_IS_STARTED)) {
                handleActionIsStarted(request, response);
            } else {
                super.doGet(request, response);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.errors.clear();
        String action = request.getParameter(PARAM_ACTION);
        if (action != null && action.equals(PARAM_ACTION_START)) {
            if (this.getServletContext().getAttribute(ATTR_ENGINE) == null) {
                startGameAndFwdRqst(request, response);
            }
        }

        super.doPost(request, response);
    }

    @Override
    protected NavbarPrinter.NavbarItems getActiveItem() {
        return NavbarPrinter.NavbarItems.NEWGAME;
    }

    @Override
    protected void printContent(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        out.println("<div class='row'>");
        out.println("<div id='message'></div>");
        out.println("");
        printForm(out);
        out.println("</div>");
        ErrorPrinter.printErrors(out, this.errors);
    }

    @Override
    protected void printMoreScripts(PrintWriter out) {
        super.printMoreScripts(out); 
        out.println("<script src=\"js/newGameSettings.js\"></script>");
    }

    private void printForm(PrintWriter out) {
        out.println("<div class='span6'>");
        out.println("");
        out.println("<form class='form-horizontal' action='newgame' method='post'>");
        out.println("<fieldset>");
        out.println("");
        out.println("<h3>Start a new game!</h3>");
        out.println("<ul class='nav nav-list'><li class='divider'></li></ul>");
        out.println("");
        out.println("<!-- Select Number of Players -->");
        out.println("<div class='control-group'>");
        out.println("<label class='control-label' for='" + PARAM_NUMBER_OF_PLAYERS + "'># of players</label>");
        out.println("<div class='controls'>");
        out.println("<select id='selectnumofplayers' name='" + PARAM_NUMBER_OF_PLAYERS + "' class='input-xlarge'>");
        out.println("<option value='3'>3 players</option>");
        out.println("<option value='4'>4 players</option>");
        out.println("<option value='5'>5 players</option>");
        out.println("<option value='6'>6 players</option>");
        out.println("</select>");
        out.println("</div>");
        out.println("</div>");
        out.println("");
        out.println("<!-- Select Number of Compter Players -->");
        out.println("<div class='control-group'>");
        out.println("<label class='control-label' for='" + PARAM_NUMBER_OF_COMPUTER_PLAYERS + "'># of computer players</label>");
        out.println("<div class='controls'>");
        out.println("<select id='selectnumofcomps' name='" + PARAM_NUMBER_OF_COMPUTER_PLAYERS + "' class='input-xlarge'>");
        out.println("</select>");
        out.println("</div>");
        out.println("</div>");
        out.println("");
        out.println("<!-- Multiple Checkboxi -->");
        out.println("<div class='control-group'>");
        out.println("<label class='control-label'>Options</label>");
        out.println("<div class='controls'>");
        out.println("<label class='checkbox'>");
        out.println("<input type='checkbox' name='" + PARAM_ALLOW_MULTIPLE + "' />");
        out.println("Allow multiple requests");
        out.println("</label>");
        out.println("<label class='checkbox'>");
        out.println("<input type='checkbox' name='" + PARAM_FORCE_SHOW + "' checked='checked' />");
        out.println("Force show of cards");
        out.println("</label>");
        out.println("</div>");
        out.println("</div>");
        out.println("<!-- Form Submission -->");
        out.println("<div class='control-group form-actions'>");
        out.println("<label class='control-label' for='buttonSubmit'></label>");
        out.println("<div class='controls'>");
        out.println("<button id='buttonSubmit'  name='" + PARAM_ACTION + "' value='" + PARAM_ACTION_START + "' class='btn btn-primary' type='submit'>Start Game!</button>");
        out.println("</div>");
        out.println("</div>");
        out.println("");
        out.println("</fieldset>");
        out.println("</form>");
        out.println("");
        out.println("</div>");
    }

    private Engine generateEngine(HttpServletRequest request) throws ServletException {
        GameSettings gs = generateGameSettings(request);
        generatePlayers(request);
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

    private void forwardRequestToWelcomeServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/NewWelcomeServlet").forward(request, response);
    }

    private void addDefaultPlayers() {
        boolean isHuman = true;
        PlayerItem player1 = new PlayerItem("Moxie", isHuman, 0);
        PlayerItem player2 = new PlayerItem("Boxie", !isHuman, 0);
        PlayerItem player3 = new PlayerItem("Foxie", !isHuman, 0);
        this.players.add(player1);
        this.players.add(player2);
        this.players.add(player3);
    }

    private void generatePlayers(HttpServletRequest request) throws ServletException {
        String numOfPlayersString = request.getParameter(PARAM_NUMBER_OF_PLAYERS);
        String numOfComputerPlayersString = request.getParameter(PARAM_NUMBER_OF_COMPUTER_PLAYERS);
        if (numOfComputerPlayersString == null || numOfPlayersString == null) {
            throw new ServletException("Missing parameters in post data!");
        }

        try {
            int numOfPlayers = Integer.parseInt(numOfPlayersString);
            int numOfComputerPlayers = Integer.parseInt(numOfComputerPlayersString);
            boolean isHuman = true;
            for (int i = 0; i < numOfPlayers - numOfComputerPlayers; i++) {
                String name = "";
                PlayerItem humanPlayer = new PlayerItem(name, isHuman, 0);
                this.players.add(humanPlayer);
            }
            
            for (int i = 0; i < numOfComputerPlayers; i++) {
                String name = "Bot" + Integer.toString(i + 1);
                PlayerItem aiPlayer = new PlayerItem(name, !isHuman, 0);
                this.players.add(aiPlayer);
            }
        } catch (NumberFormatException ex) {
            throw new ServletException("Bad formatting of integer parameters.");
        }
    }

    private void setNumOfHumansContextAttribute(HttpServletRequest request, Engine engine) {
        String numOfPlayersString = request.getParameter(PARAM_NUMBER_OF_PLAYERS);
        String numOfComputersString = request.getParameter(PARAM_NUMBER_OF_COMPUTER_PLAYERS);

        int numOfPlayers = Integer.parseInt(numOfPlayersString);
        int numOfComputers = Integer.parseInt(numOfComputersString);
        this.getServletContext().setAttribute(ATTR_NUM_OF_PLAYERS, numOfPlayers);
        this.getServletContext().setAttribute(ATTR_NUM_OF_COMPUTERS, numOfComputers);


    }

    private void handleActionIsStarted(HttpServletRequest request, HttpServletResponse response) {
        Gson gson = new Gson();
        boolean isStarted = this.getServletContext().getAttribute(ATTR_ENGINE) != null ? true : false;
        String answer = gson.toJson(isStarted);
        response.setContentType("application/json");
        try (PrintWriter out = response.getWriter()) {
            out.print(answer);
        } catch (Exception ex) {
        }
    }

    private void setCurrentServletAttribute() {
        this.getServletContext().setAttribute(ATTR_CURRENT_SETTING_SERVLET, "NewWelcomeServlet");
    }

    private void startGameAndFwdRqst(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Engine engine = generateEngine(request);
        setEngineContextAttribute(request, engine);
        setNumOfHumansContextAttribute(request, engine);
        setCurrentServletAttribute();
        resetSessionPlayersList();
        forwardRequestToWelcomeServlet(request, response);
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
