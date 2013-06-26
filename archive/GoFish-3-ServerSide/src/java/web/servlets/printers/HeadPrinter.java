package web.servlets.printers;

import java.io.PrintWriter;

/**
 *
 * @author adamnark
 */
public class HeadPrinter {

    public static void printHeadStart(PrintWriter out, String title) {
        out.println("<head>");
        out.println("<meta charset=\"utf-8\">");
        out.println("<title>" + title + "</title>");
        out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
        out.println("<meta name=\"description\" content=\"Go Fish v3.0\">");
        out.println("<meta name=\"author\" content=\"Adam Narkunski\">");
        out.println("");
        printStyles(out);

    }

    public static void printHeadEnd(PrintWriter out) {
        out.println("</head>");
        out.println("");
    }

    private static void printStyles(PrintWriter out) {
        out.println("<link href='css/gofish.css' rel='stylesheet'>");
        out.println("<link href=\"bootstrap/css/bootstrap.css\" rel=\"stylesheet\">");
        out.println("<style>body {padding-top: 60px; /* 60px to make the container go all the way to the bottom of the topbar */}</style>");
        out.println("<link href=\"bootstrap/css/bootstrap.css\" rel=\"stylesheet\">");
        out.println("<link rel=\"shortcut icon\" href=\"img/favicon.png\">");

    }

    public static void printScripts(PrintWriter out) {
        out.println("<!-- Le javascript");
        out.println("================================================== -->");
        out.println("<script src=\"bootstrap/js/jquery.js\"></script>");
        out.println("<script src=\"bootstrap/js/bootstrap.min.js\"></script>");
        out.println("");
    }
}
