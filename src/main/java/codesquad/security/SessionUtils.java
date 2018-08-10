package codesquad.security;

import codesquad.domain.User;

import javax.servlet.http.HttpSession;
import java.util.Optional;

public class SessionUtils {
    public static final String USER_SESSION_KEY = "loginedUser";

    public static void setUserInSession(HttpSession session, User loginUser) {
        session.setAttribute(USER_SESSION_KEY, loginUser);
    }

    public static boolean isLoginUser(HttpSession session) {
        return session.getAttribute(USER_SESSION_KEY) != null;
    }

    public static Optional<User> getLoginUser(HttpSession session) {
        return Optional.ofNullable((User)session.getAttribute(USER_SESSION_KEY));
    }

    public static void removeUserInSession(HttpSession session){
        session.removeAttribute(USER_SESSION_KEY);
    }
}
