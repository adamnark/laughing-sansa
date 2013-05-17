/*
 */
package web.servlets;

import java.io.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import web.servlets.assets.NavbarPrinter;

/**
 *
 * @author adamnark
 */
@WebServlet(name = "ServletWelcome", urlPatterns = {"/welcome"})
public class ServletWelcome extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html lang=\"en\">");
            printHTMLHead(out);

            printHTMLBody(out);
            out.println("</html>");
        }
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

    private void printHTMLHead(PrintWriter out) {
        out.println("<head>");
        out.println("<meta charset=\"utf-8\">");
        out.println("<title>Go Fish -- Adam Narkunski</title>");
        out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
        out.println("<meta name=\"description\" content=\"Go Fish v3.0\">");
        out.println("<meta name=\"author\" content=\"Adam Narkunski\">");
        out.println("");
        out.println("<!-- Le styles -->");
        out.println("<link href=\"bootstrap/css/bootstrap.css\" rel=\"stylesheet\">");
        out.println("<style>body {padding-top: 60px; /* 60px to make the container go all the way to the bottom of the topbar */}</style>");
        out.println("<link href=\"bootstrap/css/bootstrap.css\" rel=\"stylesheet\">");
        out.println("<link rel=\"shortcut icon\" href=\"img/favicon.png\">");
        out.println("</head>");
        out.println("");
    }

    private void printHTMLBody(PrintWriter out) throws ServletException, IOException {
        out.println("<body>");
        printNavBar(out);
        printMenu(out);

        printScripts(out);
        out.println("</body>");
    }

    private void printNavBar(PrintWriter out) throws ServletException, IOException {
        NavbarPrinter.printNavbar(out, NavbarPrinter.NavbarItems.HOME);
    }

    private void printScripts(PrintWriter out) {
        out.println("<!-- Le javascript");
        out.println("================================================== -->");
        out.println("<script src=\"bootstrap/js/jquery.js\"></script>");
        out.println("<script src=\"bootstrap/js/bootstrap.min.js\"></script>");
        out.println("");
    }

    private void printMenu(PrintWriter out) {
        out.println("<div class=\"container\">");
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
        printButton(out, "<a class=\"btn-large btn-danger btn-block\" href=\"#loadxml\"><img src=\"img/original/menu_xml_icon.png\" />Load from XML</a>");
        printButton(out, "<a class=\"btn-large btn-success btn-block\" href=\"#about\"><img src=\"img/original/about_icon.png\" />About</a>");

        out.println("</div>");
        out.println("</div>");
        out.println("</div>");
        out.println("");
        out.println("</div> <!-- /container -->");
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
