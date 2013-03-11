package examples.servletcontex;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author blecherl
 */
@WebServlet(name = "InitServlet", urlPatterns = {"/InitServlet"})
public class InitServlet extends HttpServlet {

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

        //get the name from the HTTP request (the form the user filled and submitted)
        //on the first call this parameter will be null since the user didn't have the form
        String nameFromParameter = request.getParameter("name");
//        String hiddenNameField = request.getParameter("hiddenNameField");
        
        //get a list of names from the Servlet Context
        //on the first call the value will be null
        Object nameListFromContextObj = getServletContext().getAttribute("nameList");

        //if there is no value for 'nameList' - create a new list
        //and put it in the Servlet Context (this means to put a reference to the list
        //in the Servlet Context)
        if (nameListFromContextObj == null) {
            getServletContext().setAttribute("nameList", new LinkedList<String>());
            nameListFromContextObj = getServletContext().getAttribute("nameList");
        }
        
        //now that we are sure that there is a list in the Servlet Context, get it
        //and cast it from Object ot a List<String>
        List<String> namesFromContext = (List<String>)nameListFromContextObj;

        PrintWriter out = response.getWriter();

        try {
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet InitServlet</title>");
            out.println("</head>"); 
            out.println("<body>");
            out.println("<h1>Name Input Servlet</h1>");

            //if the request did not have a name - return an empty form
            if (nameFromParameter == null) {
                //print the form that will be returned to the user
                writeUserNameInputHTMLCode(out, "", null);
            } else {
                //the request had a name - check if it is already in the list from the Servlet Context
                //if so - do not add is again and print an error message
                //(print the form as well in order to allow the user to enter another name)
                if (namesFromContext.contains(nameFromParameter)) {
                    //print the form that will be returned to the user
                    writeUserNameInputHTMLCode(out, nameFromParameter, null);
                    //print error message saying name is already in the list
                    out.println("<h2 style='color: red'>" + nameFromParameter + " is already on the Island</h2>");
                } else {
//                    hiddenNameField = nameFromParameter;
                    //add the name to the list - modifying the list changes it
                    namesFromContext.add(nameFromParameter);
                    //print the form that will be returned to the user
                    writeUserNameInputHTMLCode(out, "", null);
                    //print error message saying name is already in the list
                    out.println("<h2 style='color: green'>" + nameFromParameter + " has entered the Island</h2>");
                }
            }

            out.println("<div style='border: solid 1px'>");
            if ("Sawyer".equals(nameFromParameter)) {
                getServletContext().getRequestDispatcher("/IslandServlet").forward(request, response);
            } else if ("Ben".equals(nameFromParameter)) {
                //DO NOT CLOSE THE RESPONSE WRITER IN THE INCLUDED SERVLER !!!
                //SEE IslandServlet.JAVA for detailes
                getServletContext().getRequestDispatcher("/IslandServlet").include(request, response);
            }
            out.println("</div>");
            
            if (nameFromParameter != null){
                out.println("<h2>We Know you are " + null +"</h2>");
            }
            out.println("<a href='IslandServlet'>Go to the Island</a>");
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
        }
    }

    private void writeUserNameInputHTMLCode(PrintWriter out, String nameValue, String hiddenNameField) {
        out.println("<form action='InitServlet' method='post'>");
        out.println("Enter Name: <input type='text' name='name' value='" + nameValue + "'/>");
        out.println("<input type='submit' value='Submit Name'/>");
        out.println("<input type='hidden' name='hiddenNameField' value='"+hiddenNameField+"'/>");
        out.println("</form>");
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
