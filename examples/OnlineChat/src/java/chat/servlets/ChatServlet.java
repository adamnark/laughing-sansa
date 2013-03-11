package chat.servlets;

import chat.Constants;
import chat.logic.ChatManager;
import chat.utils.SessionUtils;
import chat.utils.ServletUtils;
import com.google.gson.Gson;
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
@WebServlet(name = "ChatServlet", urlPatterns = {"/chat"})
public class ChatServlet extends HttpServlet {

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
        ChatManager chatManager = ServletUtils.getChatManager(getServletContext());

        String username = SessionUtils.getUsername(request);
        if (username == null) {
            response.sendRedirect("index.html");
        }
        String userChatString = request.getParameter(Constants.CHAT_PARAMETER);

        if (userChatString != null && !userChatString.isEmpty()) {
            System.out.println("Adding chat string from " + username + ": " + userChatString);
            chatManager.addChatString(userChatString, username);
        }

        int chatVersion = ServletUtils.getIntParameter(request, Constants.CHAT_VERSION_PARAMETER);
        System.out.println("Server Chat version: " + chatManager.getVersion()
                + ", User '" + username + "' Chat version: " + chatVersion);

        if (chatVersion > -1) {
            PrintWriter out = response.getWriter();
            Gson gson = new Gson();
            try {
                String chatDelta = chatManager.getChat(chatVersion);
                ChatAndVersion cav = new ChatAndVersion(chatDelta, chatManager.getVersion());
                String jsonResponse = gson.toJson(cav);
                System.out.println(jsonResponse);
                out.print(jsonResponse);
                out.flush();
            } finally {
                out.close();
            }
        }
    }

    class ChatAndVersion {

        private String chat;
        private int version;

        public ChatAndVersion(String chat, int version) {
            this.chat = chat;
            this.version = version;
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
