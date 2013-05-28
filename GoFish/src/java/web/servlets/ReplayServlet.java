/*
 */
package web.servlets;

import java.io.PrintWriter;
import javax.servlet.annotation.WebServlet;

/**
 *
 * @author adamnark
 */
@WebServlet(name = "ReplayServlet", urlPatterns = {"/gameover"})
public class ReplayServlet extends GoFishServlet {

    @Override
    protected void printContent(PrintWriter out) {
        out.print("<h1>");
        out.print("Game over!");
        out.print("</h1>");
    }


    
}
