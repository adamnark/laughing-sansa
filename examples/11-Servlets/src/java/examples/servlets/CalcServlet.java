/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package examples.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author kirsh
 */
@WebServlet(name = "CalcServlet", urlPatterns = "/calc")
public class CalcServlet extends HttpServlet {

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
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String s1 = request.getParameter("x1");
        String s2 = request.getParameter("x2");
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        String result = null;
        if (s1 != null && s2 != null && !s1.isEmpty() && !s2.isEmpty()) {
            int x1 = Integer.parseInt(s1);
            int x2 = Integer.parseInt(s2);
            if (action.equals("sum")){
                result = String.valueOf(x1 + x2);
            } else if (action.equals("minus")) {
                result = String.valueOf(x1 - x2);
            }
        }
        PrintWriter out = response.getWriter();
        try {
            out.println("<html>");
            out.println("<head>");
            out.println("<title>CalcServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<form action='calc'>");
            out.println("    <input type='text' name='x1' value='"+(s1 != null ? s1 : "") +"'/>");
            out.println("     +");
            out.println("    <input type='text' name='x2' value='"+(s2 != null ? s2 : "") +"'/>");
            out.println("    <input type='hidden' name='action' value='sum' />");
            out.println("    <input type='submit' value='Sum' />");
            out.println("</form>");
            
//            out.println("<form action='calc'>");
//            out.println("    <input type='text' name='x1' value='"+(s1 != null ? s1 : "") +"'/>");
//            out.println("     -");
//            out.println("    <input type='text' name='x2' value='"+(s2 != null ? s2 : "") +"'/>");
//            out.println("    <input type='hidden' name='action' value='minus' />");
//            out.println("    <input type='submit' value='Minus' />");
//            out.println("</form>");

            if (result != null) {
                String operatorSign = action.equals("sum") ? " + " : action.equals("minus") ? " - " : "";
                out.println("<h2>" + s1 + operatorSign + s2 + " = " + result + "</h2>");
            }
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
