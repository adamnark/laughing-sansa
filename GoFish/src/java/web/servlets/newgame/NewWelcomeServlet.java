package web.servlets.newgame;

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
import web.servlets.printers.ErrorPrinter;

/**
 *
 * @author adamnark
 */
@WebServlet(name = "NewWelcomeServlet", urlPatterns = {"/NewWelcomeServlet"})
public class NewWelcomeServlet extends GoFishServlet {

    private static String PARAM_PLAYER_NAME = "name";
    //private Engine engine;
    private LinkedList<String> errors;

    public NewWelcomeServlet() {
        this.errors = new LinkedList<>();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String playerNameParam = request.getParameter(PARAM_PLAYER_NAME);
        //this.engine = (Engine) this.getServletContext().getAttribute(ATTR_ENGINE);
        if (playerNameParam != null) {
            HttpSession sesh = request.getSession(true);
            if (tryAddPlayer(playerNameParam, sesh)) {
                response.sendRedirect("lobby");
            } else {
                super.doPost(request, response);
            }
        } else {
            super.doPost(request, response);
        }
    }

    @Override
    protected void printContent(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        out.println("<div class='row'>");
        out.println("<div class='span7 alert alert-info' id='message'>");
        printSessionMessage(request, response);
        out.println("</div>");

        out.println("<div class='span7'>");
        printNameForm(out);
        ErrorPrinter.printErrors(out, errors);
        this.errors.clear();
        out.println("</div>");

        out.println("</div>");
    }

    private void printNameForm(PrintWriter out) {
        out.println("<form class='form-horizontal' method='POST' action='NewWelcomeServlet'>");
        out.println("<fieldset>");
        out.println("<!-- Form Name -->");
        out.println("<legend>Pick a name</legend>");
        out.println("");
        out.println("<!-- Text input-->");
        out.println("<div class='control-group'>");
        out.println("<label class='control-label' for='name'>Pick one:</label>");
        out.println("<div class='controls'>");
        out.println("<input name='name' id='name' type='text' class='input-xlarge'>");
        out.println("</div>");
        out.println("</div>");
        out.println("");
        out.println("<!-- Form Submission -->");
        out.println("<div class='control-group form-actions'>");
        out.println("<label class='control-label' for='buttonSubmit'></label>");
        out.println("<div class='controls'>");
        out.println("<input type='submit' id='buttonSubmit' class='btn btn-primary' value='Pick Name!' />");
        out.println("</div>");
        out.println("</div>");
        out.println("");
        out.println("</fieldset>");
        out.println("</form>");
        out.println("");
        out.println("");
    }

    private boolean tryAddPlayer(String playerName, HttpSession sesh) {
        boolean retval;

        int numOfPlayers = (int) this.getServletContext().getAttribute(ATTR_NUM_OF_PLAYERS);
        int numOfComputers = (int) this.getServletContext().getAttribute(ATTR_NUM_OF_COMPUTERS);
        boolean humanNameExists = isHumanNameTaken(playerName);
        boolean computerNameExists = this.isComputerPlayerHasName(playerName);

        List<SessionPlayer> sessionPlayers = (List<SessionPlayer>) this.getServletContext().getAttribute(ATTR_SESSION_PLAYERS_LIST);

        if (sessionPlayers.size() >= numOfPlayers - numOfComputers) {
            this.errors.add("no more room for players....");
            retval = false;
        } else if (humanNameExists || computerNameExists) {
            this.errors.add(playerName + " is taken, pick another name");
            retval = false;
        } else if (playerName.isEmpty()) {
            this.errors.add("You can't pick an empty name..");
            retval = false;
        } else if (hasIllegalChars(playerName)) {
            this.errors.add("Numbers and letters only in the name");
            retval = false;
        } else {
            Player p = getVacantPlayer();
            if (p == null) {
                this.errors.add("we ran out of room for more players while you waited...");
                retval = false;
            } else {
                p.setName(playerName);
                sessionPlayers.add(new SessionPlayer(p, sesh));
                retval = true;
            }
        }

        return retval;
    }

    private boolean isComputerPlayerHasName(String playerName) {
        Engine e = (Engine) this.getServletContext().getAttribute(ATTR_ENGINE);
        for (Player player : e.getPlayers()) {
            if (!player.isHuman() && playerName.equals(player.getName())) {
                return true;
            }
        }

        return false;
    }

    private boolean isHumanNameTaken(String playerName) {
        List<SessionPlayer> sessionPlayers = (List<SessionPlayer>) this.getServletContext().getAttribute(ATTR_SESSION_PLAYERS_LIST);
        for (SessionPlayer sessionPlayer : sessionPlayers) {
            if (sessionPlayer.getPlayer().getName().equals(playerName)) {
                return true;
            }
        }

        return false;
    }

    private Player getVacantPlayer() {
        Engine e = (Engine) this.getServletContext().getAttribute(ATTR_ENGINE);
        for (Player player : e.getPlayers()) {
            if (player.isHuman() && player.getName().isEmpty()) {
                return player;
            }
        }
        return null;
    }

    private boolean hasIllegalChars(String playerName) {
        return !playerName.matches("\\w+");
    }
}
