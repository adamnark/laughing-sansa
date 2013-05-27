/*
 */
package web.servlets;

import engine.Engine;
import engine.Factory.PlayerItem;
import engine.cards.Card;
import engine.cards.Series;
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
        super.processRequest(request, response);
    }

    @Override
    protected NavbarPrinter.NavbarItems getActiveItem() {
        return NavbarPrinter.NavbarItems.HOME;
    }

    @Override
    protected void printContent(PrintWriter out) {
        printPlayersList(out);
        out.println("<hr>");
        printHand(out);
    }

    private void printPlayersList(PrintWriter out) {
        Player currentPlayer = engine.getCurrentPlayer();
        out.println("<h3>" + "Players:" + "</h1>");
        out.println("<ul class='inline'>");
        for (Player player : engine.getPlayers()) {
            if (player.equals(currentPlayer)) {
                out.print("<li style='background:rgb(205, 255, 150);'>");
            } else {
                out.print("<li>");
            }

            out.print(PlayerItemPrinter.makeImgTag(new PlayerItem(player)));
            out.print(" ");
            out.print(player.getName());
            out.print(" : ");
            out.print(player.getScore());
            out.println();
        }

        out.println("</ul>");
    }

    private void printHand(PrintWriter out) {
        
        out.println("<h3>");
        out.println( engine.getCurrentPlayer().getName() + "'s Hand:");
        out.println("</h3>");
        
        out.println("<ul>");

        for (Card card : engine.getCurrentPlayer().getHand().getCards()) {
            printCard(out, card);
        }
        out.println("</ul>");
    }

    private void printCard(PrintWriter out, Card card) {
        out.println("<li>");
        out.print(card.getName());
        out.print(" : {");
        for (Series series : card.getSeries()) {
            out.print(series.getName());
            out.print(" ");
        }
        out.print("}");
        
        out.println("</li>");
        
    }
}
