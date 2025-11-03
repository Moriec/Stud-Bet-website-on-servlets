package com.studbet.servlet.auth;

import com.studbet.security.session.UserSession;
import com.studbet.service.auth.LoginService;
import com.studbet.service.session.SessionService;
import com.studbet.util.validate.UserValidate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "login", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    SessionService sessionService;
    UserValidate userValidate;
    LoginService loginService;

    private static final String LOGIN_FTL_PATH = "WEB-INF/ftl/auth/login.ftl";

    @Override
    public void init(){
        sessionService = (SessionService) getServletContext().getAttribute("sessionService");
        userValidate = (UserValidate) getServletContext().getAttribute("userValidate");
        loginService = (LoginService) getServletContext().getAttribute("loginService");
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (sessionService.getUserSession(req) != null) {
            resp.sendRedirect(getServletContext().getContextPath());
        } else {
            req.getRequestDispatcher(LOGIN_FTL_PATH).forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login").trim();
        String password = req.getParameter("password");

        Map<String, String> errors = new HashMap<>();

        try {
            UserSession userSession = null;
            if (userValidate.isEmailValid(login)) {
                userSession = loginService.findUserbyEmail(login, password);
            } else if (userValidate.isUsernameValid(login)) {
                userSession = loginService.findUserbyUsername(login, password);
            }

            if (userSession != null) {
                sessionService.createSession(req, userSession);
                resp.sendRedirect(getServletContext().getContextPath());
            } else {
                errors.put("invalidCredentials", "Неверный логин или пароль");

                req.setAttribute("login", login);
                req.setAttribute("errors", errors);

                req.getRequestDispatcher(LOGIN_FTL_PATH).forward(req, resp);
            }
        } catch (Exception ex) {
            errors.put("general", "Не удалось пройти аутентификацию");
            req.setAttribute("login", login);
            req.setAttribute("errors", errors);

            req.getRequestDispatcher(LOGIN_FTL_PATH).forward(req, resp);
        }
    }
}