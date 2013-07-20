/*
 */
package web.servlets.general;

import com.google.gson.Gson;
import engine.Engine;
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
    protected static final String COMMAND_QUIT = "quit";
    protected static final String COMMAND_SKIP = "skip";
    protected static final String COMMAND_THROW = "throw";
    protected static final String COMMAND_REQUEST = "request";

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
        request.getSession(true);
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html lang=\"en\">");
            printHTMLHead(out);
            printHTMLBody(request, response);
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

    private void printHTMLBody(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        out.println("<body>");
        printNavBar(out);
        out.println("<div class=\"container\">");
        printContent(request, response);
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

    protected void printContent(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        out.println("<center><h1>you should override me!</h1><br /><i>GoFish Servlet</i></center>");
    }

    protected void printMoreScripts(PrintWriter out) {
    }

    protected boolean tryForwardToCurrentServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String currentServlet = (String) this.getServletContext().getAttribute(ATTR_CURRENT_SETTING_SERVLET);
        if (currentServlet != null) {
            HttpSession s = request.getSession(true);
            s.setAttribute(SESSION_ATTR_MESSAGE, "Game already defined. Try joining.");
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

    protected void printSessionMessage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession s = request.getSession(true);
        String message = (String) s.getAttribute(SESSION_ATTR_MESSAGE);
        if (message != null) {
            response.getWriter().print(message);
            s.setAttribute(SESSION_ATTR_MESSAGE, null);
        }
    }

    protected Engine getEngineFromServletContext() {
        return (Engine) this.getServletContext().getAttribute(ATTR_ENGINE);
    }

    protected List<SessionPlayer> getSessionPlayersFromServletContext() {
        return (List<SessionPlayer>) this.getServletContext().getAttribute(ATTR_SESSION_PLAYERS_LIST);
    }

    protected SessionPlayer getSessionPlayer(HttpSession s) {
        List<SessionPlayer> lst = (List<SessionPlayer>) this.getServletContext().getAttribute(ATTR_SESSION_PLAYERS_LIST);
        if (lst != null) {
            for (SessionPlayer sessionPlayer : lst) {
                if (sessionPlayer.getSession().equals(s)) {
                    return sessionPlayer;
                }
            }
        }

        return null;
    }

    protected boolean isSessionValid(HttpServletRequest request) {
        HttpSession session = request.getSession();
        SessionPlayer sp = getSessionPlayer(session);
        if (sp == null) {
            return false;

        } else {
            return true;
        }
    }
}
