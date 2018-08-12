package codesquad.utils;

import codesquad.domain.User;

import javax.servlet.http.HttpSession;

public class SessionUtils {
    public static final String USER_SESSION_KEY = "loginedUser";

    public static void setUserInSession(HttpSession session, User loginUser) {
        session.setAttribute(USER_SESSION_KEY, loginUser);
    }

    public static User getUserFromSession(HttpSession session) {
        return (User) session.getAttribute(SessionUtils.USER_SESSION_KEY);
    }

    public static boolean isLoginUser(HttpSession session) {
        return session.getAttribute(USER_SESSION_KEY) != null;
    }

    public static void removeUserInSession(HttpSession session){
        session.removeAttribute(USER_SESSION_KEY);
    }
}
