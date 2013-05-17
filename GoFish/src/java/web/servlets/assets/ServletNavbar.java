/*
 */
package web.servlets.assets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author adamnark
 */
@WebServlet(name = "ServletNavbar", urlPatterns = {"/navbar"})
public class ServletNavbar extends HttpServlet {

    public static final String REQUEST_ATTRIBUTE = "activeNavBarItem";
    
    public enum NavbarItems {

        HOME {
            @Override
            public String toString() {
                return "Home";
            }
        },
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

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        NavbarItems activeItem = getActiveItemFromRequest(request);
        printNavBar(out, activeItem);
    }

    private NavbarItems getActiveItemFromRequest(HttpServletRequest request) {
        Object obj = request.getAttribute(REQUEST_ATTRIBUTE);
        NavbarItems activeItem;
        try {
            activeItem = (NavbarItems) obj;
        } catch (IllegalArgumentException ex) {
            activeItem = null;
        }
        return activeItem;
    }

    private void printNavBar(PrintWriter out, NavbarItems activeItem) {
        out.println("<div class=\"navbar navbar-inverse navbar-fixed-top\">");
        out.println("<div class=\"navbar-inner\">");
        out.println("<div class=\"container\">");
        out.println("<a class=\"brand\" href=\"#\">Go Fish</a>");
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

    private void printListItem(PrintWriter out, String title, String href, boolean isActive) {
        String c = isActive ? " class=\"active\"" : "";
        out.println("<li" + c + "><a href=\"" + href + "\">" + title + "</a></li>");
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
