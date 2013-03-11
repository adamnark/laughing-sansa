package examples.click;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author blecherl
 */
@WebServlet(name = "ClickServlet", urlPatterns = {"/click"})
public class ClickServlet extends HttpServlet {

    public static final int BOARD_SIZE = 10;

    int leftClickedRow = 0;
    int leftClickedCol = 0;

    int rightClickedRow = 0;
    int rightClickedCol = 0;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String rowStr = request.getParameter("row");
        String colStr = request.getParameter("col");
        String button = request.getParameter("button");

        if (rowStr != null && colStr != null && button != null) {
            //0 or 1 - means left button
            if ("0".equals(button) || "1".equals(button)) {
                leftClickedRow = Integer.parseInt(rowStr);
                leftClickedCol = Integer.parseInt(colStr);
            }
            //2 - means right button
            else if ("2".equals(button)) {
                rightClickedRow = Integer.parseInt(rowStr);
                rightClickedCol = Integer.parseInt(colStr);
            } else {
                leftClickedRow = 0;
                leftClickedCol = 0;
                rightClickedRow = 0;
                rightClickedCol = 0;
            }
        }

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {

            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ClickServlet</title>");
            out.println("<link rel='stylesheet' type='text/css' href='click/click.css' />");
            out.println("<script src='click/click.js' type='text/javascript'></script>");
            out.println("</head>");

            out.println("<body>");

            //oncontextmenu=\"return false\" - causes the right click menu to not open
            out.println("<table oncontextmenu=\"return false\">");
            for (int col=1 ; col <= BOARD_SIZE ; col++ ) {
                out.println("<tr>");
                for (int row=1 ; row <= BOARD_SIZE ; row++ ) {
                    String className;
                    if (row == leftClickedRow && col == leftClickedCol) {
                        className = "redborder";
                    } else if (row == rightClickedRow && col == rightClickedCol) {
                        className = "blueborder";
                    } else {
                         className = "";
                    }

                    //each td element is set to call 'myclick' on mouse up passing
                    //three parameters - the event parameter comes from the browser
                    //and contains details on the mouse click event;
                    //row and col are generated when the page is generated
                    //and are fixed for each td element
                    out.println("<td class= '" + className + "' onmouseup = myclick>");
                    out.println("<span>" + col * row + "</span>");
                    out.println("</td>");
                }
                out.println("</tr>");
            }
            out.println("</table>");

            //type='hidden' means the field is not visible
            //also - notice there is no type='submit' input since this form
            //will be submitted using JavaScript
            out.println("<form id='clickform' method='post' action='click'>");
            out.println("<input id='col' type='hidden' name='col'/>");
            out.println("<input id='row' type='hidden' name='row'/>");
            out.println("<input id='button' type='hidden' name='button'/>");
            out.println("</form>");

            out.println("</body>");
            out.println("</html>");

        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
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
     * Handles the HTTP <code>POST</code> method.
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
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
