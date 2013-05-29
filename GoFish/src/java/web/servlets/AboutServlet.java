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
@WebServlet(name = "AboutServlet", urlPatterns = {"/about"})
public class AboutServlet extends GoFishServlet {

    @Override
    protected NavbarPrinter.NavbarItems getActiveItem() {
        return NavbarPrinter.NavbarItems.ABOUT;
    }

    @Override
    protected void printContent(PrintWriter out) {
        out.println("<div class=\"row\">");
        out.println("");
        out.println("<div class=\"span6 \">");
        out.println("<div class=\"row\">");
        out.println("<div class=\"span6 text-center\">");
        out.println("<h1>Go Fish! v3.0</h1>");
        out.println("<div>by Adam Narkunski</div>");
        out.println("<div>30●●●●113</div>");
        out.println("</div>");
        out.println("</div>");
        out.println("</div>");
        out.println("</div>");
        out.println("<div class=\"row\">");
        out.println("<div class=\"span6 \">");
        out.println("&nbsp;");
        out.println("</div>");
        out.println("</div>");
        out.println("");
        out.println("<div class=\"row\">");
        out.println("<div class=\"span6 \">");
        out.println("<div class=\"row\">");
        out.println("<div class=\"span6 text-center\">");
        out.println("");
        out.println("<img class=\"img-rounded\" src=\"img/about.jpg\" alt=\"grr\">");
        out.println("</div> ");
        out.println("</div>");
        out.println("</div>");
        out.println("</div>");
    }
}
