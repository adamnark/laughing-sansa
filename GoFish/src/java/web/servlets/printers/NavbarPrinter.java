/*
 */
package web.servlets.printers;

import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author adamnark
 */
public class NavbarPrinter {

    public enum NavbarItems {

        NEWGAME {
            @Override
            public String toString() {
                return "New Game";
            }
        },
        LOADGAME {
            @Override
            public String toString() {
                return "Load Game";
            }
        },
        ABOUT {
            @Override
            public String toString() {
                return "About";
            }
        },
    }

    public static void printNavbar(PrintWriter out, NavbarItems activeItem) throws IOException {
        out.println("<div class=\"navbar navbar-inverse navbar-fixed-top\">");
        out.println("<div class=\"navbar-inner\">");
        out.println("<div class=\"container\">");
        out.println("<a class=\"brand\" href=\"home\">Go Fish</a>");
        out.println("<div class=\"nav-collapse collapse\">");
        out.println("<ul class=\"nav\">");

        for (NavbarItems item : NavbarItems.values()) {
            String title = item.toString();
            String link = title.replaceAll("\\s", "");
            link = link.toLowerCase();
            boolean isActiveItem = item.equals(activeItem);
            printListItem(out, title, link, isActiveItem);
        }

        out.println("</ul>");
        
        out.println("</div>");
        out.println("</div>");
        out.println("</div>");
        out.println("</div>");
        out.println("");
    }

    private static void printListItem(PrintWriter out, String title, String href, boolean isActive) {
        String c = isActive ? " class=\"active\"" : "";
        out.println("<li" + c + "><a href=\"" + href + "\">" + title + "</a></li>");
    }
}
