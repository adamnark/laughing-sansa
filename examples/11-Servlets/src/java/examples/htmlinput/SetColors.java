package examples.htmlinput;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SetColors extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String bg = request.getParameter("bgcolor");
        String fg = request.getParameter("fgcolor");
        String size = request.getParameter("size");
        out.println("<html><head><title>Set Colors Example"
                + "</title></head>");

        out.println("<body style=\"color:" + fg
                + ";background-color:" + bg + ";font-size:" + size + "px\">");
        out.println("<h1>Set Colors Example</h1>");
        out.println("<p>You requested a background color " + bg + "</p>");
        out.println("<p>You requested a font color " + fg + "</p>");
        out.println("<p>You requested a font size " + size + "</p>");

        out.println("</body></html>");
    }
}