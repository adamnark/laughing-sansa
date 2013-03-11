package chat.utils;

import chat.Constants;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author blecherl
 */
public class SessionUtils {

    public static String getUsername (HttpServletRequest request) {
        Object sessionAttribute = request.getSession().getAttribute(Constants.USERNAME);
        return sessionAttribute != null ? sessionAttribute.toString() : null;
    }

    public static void clearSession (HttpServletRequest request) {
        request.getSession().invalidate();
    }
}
