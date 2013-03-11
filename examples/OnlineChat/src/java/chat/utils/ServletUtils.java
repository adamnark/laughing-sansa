package chat.utils;

import chat.logic.ChatManager;
import chat.logic.UserManager;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author blecherl
 */
public class ServletUtils {
    public static UserManager getUserManager (ServletContext servletContext) {
        if (servletContext.getAttribute("userManager") == null) {
            servletContext.setAttribute("userManager", new UserManager());
        }
        return (UserManager) servletContext.getAttribute("userManager");
    }

    public static ChatManager getChatManager (ServletContext servletContext) {
        if (servletContext.getAttribute("chatManager") == null) {
            servletContext.setAttribute("chatManager", new ChatManager());
        }
        return (ChatManager) servletContext.getAttribute("chatManager");
    }

    public static int getIntParameter(HttpServletRequest request, String name) {
        String value = request.getParameter(name);
        if (value != null) {
            try {
                return Integer.parseInt(value);
            } catch (NumberFormatException numberFormatException) {
                return -1;
            }
        }
        return -1;
    }

}
