/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package examples.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author kirsh
 */
@WebServlet(name = "GuestServlet", urlPatterns = "/guestslist")
public class GuestServlet extends HttpServlet {

    private List<String> guestsList = new ArrayList<String>(); 

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
    protected void processRequest(
            HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            //get the guests list from the Servlet Context - which is shared
//            between all servlets in the web application
//            List<String> guestsList =
//                (List<String>)getServletContext().getAttribute("guests");

//            if (guestsList == null) {
//                guestsList = new ArrayList<String>();
//                getServletContext().setAttribute("guests", guestsList);
//            }
            //get the guests list from the Session Context - which is 
            //unique for each browser that is connected to the server
//            HttpSession session = request.getSession();
//            List<String> guestsList =
//                (List<String>)session.getAttribute("guests");
//            if (guestsList == null){
//                guestsList = new ArrayList<String>();
//                session.setAttribute("guests", guestsList);
//            }

            String guestName = request.getParameter("guest");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet GuestServlet</title>");
            out.println("</head>");
            out.println("<body>");

            out.println("<form action='guestslist' method='post'>");
            out.println("<input type='text' name='guest' value='"+(guestName != null ? guestName : "")+"'/>");
            out.println("<input type='submit' value='Add'/>");
            out.println("</form>");

            if (guestName != null) {
                guestsList.add(guestName);
            }

            out.println("<ol>");
            for (String name : guestsList) {
                out.println("<li>");
                out.println(name);
                out.println("</li>");
            }
            out.println("</ol>");

            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
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
}