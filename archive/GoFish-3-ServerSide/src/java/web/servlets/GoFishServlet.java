/*
 */
package web.servlets;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import web.servlets.printers.HeadPrinter;
import web.servlets.printers.NavbarPrinter;

/**
 *
 * @author adamnark
 */
public class GoFishServlet extends HttpServlet {

    protected static final String ATTR_ENGINE = "attribute-engine";

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
        } catch (Exception ex) {
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
        String title = getPageTitle();

        HeadPrinter.printHeadStart(out, title);
        moreHTMLHead(out);
        HeadPrinter.printHeadEnd(out);


    }

    protected void moreHTMLHead(PrintWriter out) {
    }

    protected String getPageTitle() {
        return "GoFish [@adamnark]";
    }

    private void printHTMLBody(PrintWriter out) throws ServletException, IOException {
        out.println("<body>");
        printNavBar(out);
        out.println("<div class=\"container\">");
        printContent(out);
        out.println("</div> <!-- container -->");
        out.println("");
        printScripts(out);
        out.println("</body>");
    }

    private void printNavBar(PrintWriter out) throws ServletException, IOException {
        NavbarPrinter.NavbarItems activeItem = getActiveItem();
        NavbarPrinter.printNavbar(out, activeItem);
    }

    protected NavbarPrinter.NavbarItems getActiveItem() {
        return null;
    }

    private void printScripts(PrintWriter out) {
        HeadPrinter.printScripts(out);
        printMoreScripts(out);
    }

    protected void printContent(PrintWriter out) {
        out.println("<center><h1>you should override me!</h1><br /><i>GoFish Servlet</i></center>");
    }

    protected void printMoreScripts(PrintWriter out) {
    }
}
