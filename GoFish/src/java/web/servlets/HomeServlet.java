/*
 */
package web.servlets;

import java.io.PrintWriter;
import javax.servlet.annotation.WebServlet;
import web.servlets.printers.NavbarPrinter;

/**
 *
 * @author adamnark
 */
@WebServlet(name = "HomeServlet", urlPatterns = {"/home"})
public class HomeServlet extends GoFishServlet {

    @Override
    protected String getPageTitle() {
        return "GoFish Home -- Adam Narkunski";
    }

    @Override
    protected NavbarPrinter.NavbarItems getActiveItem() {
        return NavbarPrinter.NavbarItems.HOME;
    }

    @Override
    protected void printContent(PrintWriter out) {
        out.println("<div class=\"row\">");
        out.println("");
        out.println("<div class=\"span6 offset3\">");
        out.println("<div class=\"row\">");
        out.println("<div class=\"span6 text-center\">");
        out.println("<h1>Go Fish! v3.0</h1>");
        out.println("</div>");
        out.println("</div>");
        out.println("");
        out.println("<div class=\"row\">");
        out.println("<div class=\"span6 text-center\">");
        out.println("Adam Narkunski");
        out.println("</div>");
        out.println("</div>");
        out.println("");
        printButton(out, "<a class=\"btn-large btn-primary btn-block\" href=\"newgame\"><img src=\"img/original/menu_manual_icon.png\"/>Start a new game</a>");
        printButton(out, "<a class=\"btn-large btn-danger btn-block\" href=\"loadgame\"><img src=\"img/original/menu_xml_icon.png\" />Load a game</a>");
        printButton(out, "<a class=\"btn-large btn-success btn-block\" href=\"about\"><img src=\"img/original/about_icon.png\" />About</a>");
        out.println("</div>");
        out.println("</div>");
        out.println("");
    }

    private void printButton(PrintWriter out, String content) {
        out.println("<div class=\"row\">");
        out.println("<div class=\"span6 text-center\">");
        out.println("<div class=\"btn-toolbar\">");
        out.println(content);
        out.println("</div>");
        out.println("</div>");
        out.println("</div>");
        out.println("");
    }
}
