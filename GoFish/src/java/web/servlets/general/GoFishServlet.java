/*
 */
package web.servlets.general;

import com.google.gson.Gson;
import java.io.*;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import web.servlets.containers.SessionPlayer;
import web.servlets.printers.HeadPrinter;
import web.servlets.printers.NavbarPrinter;

/**
 *
 * @author adamnark
 */
public class GoFishServlet extends HttpServlet {

    protected static final String ATTR_ENGINE = "attribute-engine";
    protected static final String ATTR_NUM_OF_PLAYERS = "attribute-players";
    protected static final String ATTR_NUM_OF_COMPUTERS = "attribute-computers";
    protected static final String ATTR_LIST_OF_HUMAN_PLAYERS = "attr-list-of-human-players";
    protected static final String ATTR_SESSION_PLAYERS_LIST = "attr-session-players-list";
    protected static final String ATTR_CURRENT_SETTING_SERVLET = "attr-current-setting-servlet";
    protected static final String SESSION_ATTR_MESSAGE = "session-attr-message";

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

    protected void resetSessionPlayersList() {
        List<SessionPlayer> lst = new LinkedList<>();
        this.getServletContext().setAttribute(ATTR_SESSION_PLAYERS_LIST, lst);
    }

    private void printHTMLHead(PrintWriter out) {
        String title = getPageTitle();
        HeadPrinter.printHeadStart(out, title);
        moreHTMLHead(out);
        HeadPrinter.printHeadEnd(out);
    }

    protected void moreHTMLHead(PrintWriter out) {
    }

    protected String getPageTitle() {
        return "GoFish @adamnark";
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

    protected boolean tryForwardToCurrentServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String currentServlet = (String) this.getServletContext().getAttribute(ATTR_CURRENT_SETTING_SERVLET);
        if (currentServlet != null) {
            request.getRequestDispatcher(currentServlet).forward(request, response);
            return true;
        }
        return false;
    }

    protected void respondJSONObject(Object obj, HttpServletResponse response) throws IOException {
        Gson gson = new Gson();
        String answer = gson.toJson(obj);
        response.setContentType("json");
        response.getWriter().print(answer);
    }
}
