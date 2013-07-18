/*
 */
package web.servlets.game;

import engine.Engine;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import web.servlets.containers.SessionPlayer;
import web.servlets.general.GoFishServlet;

/**
 *
 * @author adamnark
 */
@WebServlet(name = "BoardServlet", urlPatterns = {"/board"})
public class BoardServlet extends GoFishServlet {

    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<SessionPlayer> spList = getSessionPlayersFromServletContext();
        SessionPlayer sp = getSessionPlayer(request.getSession());
        if (!spList.contains(sp)) {
            request.getRequestDispatcher("/home").forward(request, response);
        } else {
            super.processRequest(request, response);
        }
    }

    @Override
    protected void printContent(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        // printPlayersList(out);
        printTitle(out, "Players");
        out.println("<ul id='list' class='inline'></ul>");
        out.println("<hr>");
        // printPlayerForm(out);
        // ErrorPrinter.printErrors(out, this.errors);
        out.println("<div id='commands'></div>");
        out.println("<hr>");
        out.println("<div id='message'>mofing</div>");
        out.println("<hr>");
        out.println("<table class='table'>");
        out.println("<thead>");
        out.println("<tr>");
        out.println("<th>");
        printTitle(out, "Your cards");
        out.println("</th>");
        out.println("<th>");
        printTitle(out, "Graveyard");
        out.println("</th>");
        out.println("<th>");
        printTitle(out, "Message log");
        out.println("</th>");
        out.println("</tr>");
        out.println("</thead>");
        out.println("<tbody>");
        out.println("<tr>");
        out.println("<td id='hand' class='hand'>");
        //printHand(out);
        out.println("</td>");
        out.println("<td id='graveyard' class='graveyard'>");
        //printGraveyard(out);
        out.println("</td>");
        out.println("<td class='log'>");
        out.println("<ul id='log'>");
        out.println("</ul>");
        //printLastMessages(out, MESSAGE_LOG_LENGTH);
        out.println("</td>");
        out.println("</tr>");
        out.println("</tbody>");
        out.println("</table>");
    }

    @Override
    protected void printMoreScripts(PrintWriter out) {
        out.println("<script src='js/board.js'> </script>");

    }
    
    private void printTitle(PrintWriter out, String title) {
        out.println("<h3>");
        out.println(title);
        out.println("</h3>");
    }
}
