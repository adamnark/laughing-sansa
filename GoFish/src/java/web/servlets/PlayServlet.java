/*
 */
package web.servlets;

import engine.Engine;
import engine.Factory.PlayerItem;
import engine.players.Player;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import web.servlets.printers.NavbarPrinter;
import web.servlets.printers.PlayerItemPrinter;

/**
 *
 * @author adam
 */
@WebServlet(name = "PlayServlet", urlPatterns = {"/play"})
public class PlayServlet extends GoFishServlet {

    private Engine engine;

    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.engine = (Engine) this.getServletContext().getAttribute(ATTR_ENGINE);
        super.processRequest(request, response); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected NavbarPrinter.NavbarItems getActiveItem() {
        return NavbarPrinter.NavbarItems.HOME;
    }

    @Override
    protected void printContent(PrintWriter out) {
        out.println("<h3>" + "Players:"  +"</h1>");

        out.println("<ul class='inline'>");
        for (Player player : engine.getPlayers()) {
            PlayerItem pi = new PlayerItem(player);
            out.print("<li>");
            out.print(PlayerItemPrinter.makeImgTag(pi) + pi.getPlayerName()); 
            out.print("</li>");
        }
        out.println("</ul>");
        
        out.print("<p> current player is ");
        out.print(engine.getCurrentPlayer().getName());
        out.println("</p>");
        
        out.println("<p>");
        out.println("isAllowMutipleRequests: " + engine.getGameSettings().isAllowMutipleRequests());
        out.println("</p>");
        out.println("<p>");
        out.println("isForceShowOfSeries: " + engine.getGameSettings().isForceShowOfSeries());
        out.println("</p>");
        
        
        
    }
}
