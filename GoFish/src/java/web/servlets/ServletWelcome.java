/*
 */
package web.servlets;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

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
            printHTMLHead(out);
            printHTMLBody(out);
            out.println("<br> here: " + request.getContextPath());
                    
            printHTMLTail(out);
        }
    }

    private void printHTMLBody(PrintWriter out) {
        out.println("<body>");
        out.println("<h1>Servlet ServletWelcome at kooki </h1>");
        out.println("</body>");

    }

    private void printHTMLHead(PrintWriter out) {
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>"); 
        printHTMLLinkStylesheet(out);
        out.println("<title>Servlet ServletWelcome</title>");
        out.println("</head>");
    }

    private void printHTMLLinkStylesheet(PrintWriter out) {
        out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/gofish.css\" />");
    }

    private void printHTMLTail(PrintWriter out) {
        out.println("</html>");
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
