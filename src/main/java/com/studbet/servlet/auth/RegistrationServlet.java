package com.studbet.servlet.auth;

import com.studbet.model.User;
import com.studbet.security.session.UserSession;
import com.studbet.service.auth.CheckLoginService;
import com.studbet.service.auth.RegistrationService;
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

@WebServlet(name = "SignUp", urlPatterns = "/sign_up")
public class RegistrationServlet extends HttpServlet {

    private CheckLoginService checkLoginService;
    private RegistrationService registrationService;
    private SessionService sessionService;
    private UserValidate userValidate;

    private static final String SIGN_UP_FTL_PATH = "WEB-INF/ftl/auth/sign_up.ftl";

    @Override
    public void init() {
        checkLoginService = (CheckLoginService) getServletContext().getAttribute("checkLoginService");
        registrationService = (RegistrationService) getServletContext().getAttribute("registrationService");
        sessionService = (SessionService) getServletContext().getAttribute("sessionService");
        userValidate = (UserValidate) getServletContext().getAttribute("userValidate");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(SIGN_UP_FTL_PATH).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Получаем данные
        String username = req.getParameter("username");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        Map<String, String> errors = new HashMap<>();

        if(!userValidate.isEmailValid(email)){
            errors.put("general", "Недопустимый формат электронной почты");
        }
        if(!userValidate.isPasswordValid(password)){
            errors.put("general", "Недопустимое кол-во цифр в пароле");
        }
        if(!userValidate.isUsernameValid(username)){
            errors.put("general", "Недопустимый формат имени пользователя");
        }
        if(!errors.isEmpty()){
            req.setAttribute("username", username);
            req.setAttribute("email", email);
            req.setAttribute("errors", errors);

            req.getRequestDispatcher(SIGN_UP_FTL_PATH).forward(req, resp);
        }

        try {
            //Проверяем, существует ли пользователь с тем же именем или почтой
            if (checkLoginService.checkUsername(username)) {
                errors.put("usernameExists", "Имя пользователя уже существует");
            }
            if (checkLoginService.checkEmail(email)) {
                errors.put("emailExists", "Email уже существует");
            }

            // Если нет ошибок, добавим пользователя
            if (!errors.isEmpty()) {
                req.setAttribute("username", username);
                req.setAttribute("email", email);
                req.setAttribute("errors", errors);

                req.getRequestDispatcher(SIGN_UP_FTL_PATH).forward(req, resp);
            } else {
                UserSession userSession = registrationService.saveUser(new User(username, email, password, "STUDENT"));
                sessionService.createSession(req, userSession);
                resp.sendRedirect(getServletContext().getContextPath());
            }
        } catch (IOException ex){
            throw ex; // Не удалось произвести redirect на корень
        } catch (Exception e) {
            errors.put("general", "Не удалось пройти регистрацию");

            req.setAttribute("username", username);
            req.setAttribute("email", email);
            req.setAttribute("errors", errors);

            req.getRequestDispatcher(SIGN_UP_FTL_PATH).forward(req, resp);
        }
    }
}
