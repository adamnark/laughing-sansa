/*
 */
package web.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import web.servlets.printers.NavbarPrinter;

/**
 *
 * @author adamnark
 */
@WebServlet(name = "NewGameServlet", urlPatterns = {"/newgame"})
public class NewGameServlet extends GoFishServlet {

    private static final String PARAM_PLAYERNAME = "playername";
    private List<String> playerNames = new LinkedList<>();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String playername = request.getParameter(PARAM_PLAYERNAME);

        playername = playername == null ? "(null)" : playername;

        playerNames.add(playername);

        super.doPost(request, response);
    }

    @Override
    protected NavbarPrinter.NavbarItems getActiveItem() {
        return NavbarPrinter.NavbarItems.NEWGAME;
    }

    @Override
    protected void printContent(PrintWriter out) {
        printForm(out);
        printListOfPlayers(out);
    }

    private void printForm(PrintWriter out) {
        out.println("<div class=\"span6\">");
        out.println("<form class=\"form-horizontal\" action=\"newgame\" method=\"post\">");
        out.println("<fieldset>");
        out.println("");
        out.println("<!-- Form Name -->");
        out.println("<legend>Start a new game!</legend>");
        out.println("");
        out.println("<!-- Button Drop Down -->");
        out.println("<div class=\"control-group\">");
        out.println("<label class=\"control-label\" for=\"" + PARAM_PLAYERNAME + "\">Add player</label>");
        out.println("<div class=\"controls\">");
        out.println("<div class=\"input-append\">");
        out.println("<input name=\"" + PARAM_PLAYERNAME + "\" type=\"text\" id=\"" + PARAM_PLAYERNAME + "\" class=\"input-medium\" placeholder=\"Moxie\">");
        out.println("<input class=\"btn\" type=\"submit\" value=\"Add\" />");
        out.println("</div>");
        out.println("</div>");
        out.println("</div>");
        out.println("");
        out.println("<div class=\"control-group\">");
        out.println("<label class=\"control-label\" for=\"gameoptions\">Game Options</label>");
        out.println("<div class=\"controls\">");
        out.println("<label class=\"checkbox\" for=\"gameoptions-0\">");
        out.println("<input type=\"checkbox\" name=\"gameoptions\" id=\"gameoptions-0\" value=\"allow-multiple-requests\" checked=\"checked\">");
        out.println("Allow multiple requests");
        out.println("</label>");
        out.println("<label class=\"checkbox\" for=\"gameoptions-1\">");
        out.println("<input type=\"checkbox\" name=\"gameoptions\" id=\"gameoptions-1\" value=\"force-show-of-cards\">");
        out.println("Force show of cards");
        out.println("</label>");
        out.println("</div>");
        out.println("</div>");
        out.println("");
        out.println("<!-- Button -->");
        out.println("<div class=\"control-group form-actions\">");
        out.println("<label class=\"control-label\" for=\"buttonSubmit\"></label>");
        out.println("<div class=\"controls\">");
        out.println("<button id=\"buttonSubmit\" name=\"buttonSubmit\" class=\"btn btn-primary\" type='submit'>Start Game!</button>");
        out.println("</div>");
        out.println("</div>");
        out.println("");
        out.println("</fieldset>");
        out.println("</form>");
        out.println("</div>");
    }

    private void printListOfPlayers(PrintWriter out) {
        out.println("<div class=\"span4\" >");
        out.println("<legend>List of players:</legend>");
        out.println("");
        out.println("<ul>");
        for (String string : playerNames) {
            printPlayerItem(out, string);
        }

        out.println("</ul>");
        out.println("");
        out.println("</div>");
    }

    private void printPlayerItem(PrintWriter out, String kakiel) {
        out.println("<li>" + kakiel + "</li>");
    }
}
