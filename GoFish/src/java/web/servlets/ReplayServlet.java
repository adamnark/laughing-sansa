/*
 */
package web.servlets;

import engine.Engine;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static web.servlets.GoFishServlet.ATTR_ENGINE;

/**
 *
 * @author adamnark
 */
@WebServlet(name = "ReplayServlet", urlPatterns = {"/gameover"})
public class ReplayServlet extends GoFishServlet {

    private String winnerName;
    private int winnerScore;

    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Engine engine = (Engine) this.getServletContext().getAttribute(ATTR_ENGINE);
        this.winnerName = engine.getWinner().getName();
        this.winnerScore = engine.getWinner().getScore();

        super.processRequest(request, response);
    }

    @Override
    protected void printContent(PrintWriter out) {
        out.print("<h1>");
        out.print("Game over!");
        out.print("</h1>");
        printWinner(out);
        printReplayLink(out);
        printHomeLink(out);
    }

    private void printWinner(PrintWriter out) {
        out.print("<p>");
        out.print(this.winnerName + " won with " + this.winnerScore + " points!");
        out.print("</p>");
    }

    private void printHomeLink(PrintWriter out) {
        out.print("<p>");
        out.print("<a href='home'>");
        out.print("Go back to main menu.");
        out.print("</a>");
        out.print("</p>");
    }

    private void printReplayLink(PrintWriter out) {
        out.print("<p>");
        out.print("<a href='#'>");
        out.print("Play again with the same settings.");
        out.print("</a>");
        out.print("</p>");
    }
}
