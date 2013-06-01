/*
 */
package web.servlets.printers;

import java.io.PrintWriter;
import java.util.Collection;

/**
 *
 * @author adam
 */
public class ErrorPrinter {

    public static  void printErrors(PrintWriter out, Collection<String> errors) {
        if (!errors.isEmpty()) {
//            out.println("<div class=\"row\">");
//            out.println("<div class=\"alert alert-error span3 offset1\">");
            out.println("<div class=\"alert alert-error\">");
            out.println("<ul>");
            for (String error : errors) {
                out.println("<li>");
                out.println(error);
                out.println("</li>");
            }
            
            out.println("</ul>");
            out.println("</div>");
            out.println("</div>");
            out.println("");
        }
    }
}
