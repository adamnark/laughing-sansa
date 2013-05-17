/*
 */
package web.servlets;

import java.io.PrintWriter;
import javax.servlet.annotation.WebServlet;
import web.servlets.printers.NavbarPrinter;

/**
 *
 * @author adamnark
 */
@WebServlet(name = "LoadGameServlet", urlPatterns = {"/loadgame"})
public class LoadGameServlet extends GoFishServlet {


    @Override
    protected NavbarPrinter.NavbarItems getActiveItem() {
        return NavbarPrinter.NavbarItems.LOADGAME;
    }

    @Override
    protected void printContent(PrintWriter out) {
        super.printContent(out); //To change body of generated methods, choose Tools | Templates.
    }
    
}
