/*
 */
package web.servlets;

import engine.Engine;
import engine.Validator;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.xml.bind.JAXBException;
import web.servlets.printers.ErrorPrinter;
import web.servlets.printers.NavbarPrinter;
import xml.SettingsFromXML;

/**
 *
 * @author adamnark
 */
@WebServlet(name = "LoadGameServlet", urlPatterns = {"/loadgame"})
@MultipartConfig
public class LoadGameServlet extends GoFishServlet {

    private static final String PARAM_FILE = "file1";
    private List<String> errors = new LinkedList<>();
    private SettingsFromXML settingsFromXML = null;
    public static final String ATTR_SETTINGS_FROM_XML = "attr-settings-from-xml";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.errors.clear();
        super.doGet(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.errors.clear();
        generateEngineFromParam(request);
        validateGeneratedEngine();
        if (settingsFromXML != null) {
            addSettingsToServletContext();
            request.getRequestDispatcher("/play").forward(request, response);
        }

        super.doPost(request, response);
    }

    private void generateEngineFromParam(HttpServletRequest request) throws IOException, ServletException {
        InputStream filecontent;
        try {
            final Part filePart = request.getPart(PARAM_FILE);
            filecontent = filePart.getInputStream();
            settingsFromXML = new SettingsFromXML(filecontent);
        } catch (IllegalStateException ex) {
            errors.add("I'm not sure what happend but that file was evil. Try smaller files.");
        } catch (JAXBException ex) {
            errors.add("You've provided an invalid GoFish XML file... I couldn't load it. ");
        }
    }

    private void validateGeneratedEngine() {
        if (settingsFromXML != null) {
            Engine e = settingsFromXML.makeEngine();
            Validator v = new Validator(e);
            boolean isValid = v.validateEngineState();
            if (!isValid) {
                errors.add("You've provided an invalid GoFish file... it did not pass validation:");
                errors.addAll(v.getErrors());
                settingsFromXML = null;
            } else {
                addEngineToServletContext(e);
            }
        }
    }

    @Override
    protected NavbarPrinter.NavbarItems getActiveItem() {
        return NavbarPrinter.NavbarItems.LOADGAME;
    }

    @Override
    protected void printContent(PrintWriter out) {
        printForm(out);
        ErrorPrinter.printErrors(out, errors);
    }

    private void printForm(PrintWriter out) {
        out.println("<div class='row'>");
        out.println("<div class='span6'>");
        out.println("<form ");
        out.println("class='form-horizontal' ");
        out.println("action='#' ");
        out.println("method='post'");
        out.println("enctype='multipart/form-data'>");
        out.println("<fieldset>");
        out.println("<legend>Load a game!</legend>");
        out.println("");
        out.println("<div class='control-group'>");
        out.println("<label class='control-label' for='filebutton'>XML File:</label>");
        out.println("<div class='controls'>");
        out.println("<input name='" + PARAM_FILE + "' type='file' id='filebutton' class='input-file'>");
        out.println("</div>");
        out.println("</div>");
        out.println("");
        out.println("<div class='control-group'>");
        out.println("<label class='control-label' for='start'>Start the game</label>");
        out.println("<div class='controls'>");
        out.println("<input type='hidden' name='action' value='start'>");
        out.println("<input type='submit' id='start' name='start' class='btn btn-primary' value='Load!'>");
        out.println("</div>");
        out.println("</div>");
        out.println("</fieldset>");
        out.println("</form>");
        out.println("</div>");
        out.println("</div>");
    }

    private void addEngineToServletContext(Engine e) {
        this.getServletContext().setAttribute(ATTR_ENGINE, e);
    }

    private void addSettingsToServletContext() {
        this.getServletContext().setAttribute(ATTR_SETTINGS_FROM_XML, settingsFromXML);
    }
}
