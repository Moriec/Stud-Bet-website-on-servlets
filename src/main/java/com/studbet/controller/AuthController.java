package com.studbet.controller;

import com.studbet.model.User;
import com.studbet.security.session.UserSession;
import com.studbet.service.auth.CheckLoginService;
import com.studbet.service.auth.LoginService;
import com.studbet.service.auth.RegistrationService;
import com.studbet.service.session.SessionService;
import com.studbet.util.validate.UserValidate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final SessionService sessionService;
    private final UserValidate userValidate;
    private final LoginService loginService;
    private final CheckLoginService checkLoginService;
    private final RegistrationService registrationService;

    @GetMapping("/login")
    public String loginPage(HttpServletRequest request) {
        if (sessionService.getUserSession(request) != null) {
            return "redirect:/";
        }
        return "auth/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String login, @RequestParam String password, HttpServletRequest request, Model model) {
        Map<String, String> errors = new HashMap<>();
        try {
            UserSession userSession = null;
            String trimmedLogin = login.trim();
            if (userValidate.isEmailValid(trimmedLogin)) {
                userSession = loginService.findUserbyEmail(trimmedLogin, password);
            } else if (userValidate.isUsernameValid(trimmedLogin)) {
                userSession = loginService.findUserbyUsername(trimmedLogin, password);
            }

            if (userSession != null) {
                sessionService.createSession(request, userSession);
                return "redirect:/";
            } else {
                errors.put("invalidCredentials", "Неверный логин или пароль");
                model.addAttribute("login", login);
                model.addAttribute("errors", errors);
                return "auth/login";
            }
        } catch (Exception ex) {
            errors.put("general", "Не удалось пройти аутентификацию");
            model.addAttribute("login", login);
            model.addAttribute("errors", errors);
            return "auth/login";
        }
    }

    @GetMapping("/sign_up")
    public String registrationPage() {
        return "auth/sign_up";
    }

    @PostMapping("/sign_up")
    public String register(@RequestParam String username, @RequestParam String email, @RequestParam String password, HttpServletRequest request, Model model) {
        Map<String, String> errors = new HashMap<>();

        if (!userValidate.isEmailValid(email)) {
            errors.put("general", "Недопустимый формат электронной почты");
        }
        if (!userValidate.isPasswordValid(password)) {
            errors.put("general", "Недопустимое кол-во цифр в пароле");
        }
        if (!userValidate.isUsernameValid(username)) {
            errors.put("general", "Недопустимый формат имени пользователя");
        }

        if (!errors.isEmpty()) {
            model.addAttribute("username", username);
            model.addAttribute("email", email);
            model.addAttribute("errors", errors);
            return "auth/sign_up";
        }

        try {
            if (checkLoginService.checkUsername(username)) {
                errors.put("usernameExists", "Имя пользователя уже существует");
            }
            if (checkLoginService.checkEmail(email)) {
                errors.put("emailExists", "Email уже существует");
            }

            if (!errors.isEmpty()) {
                model.addAttribute("username", username);
                model.addAttribute("email", email);
                model.addAttribute("errors", errors);
                return "auth/sign_up";
            } else {
                UserSession userSession = registrationService.saveUser(new User(username, email, password, "STUDENT"));
                sessionService.createSession(request, userSession);
                return "redirect:/";
            }
        } catch (Exception e) {
            errors.put("general", "Не удалось пройти регистрацию");
            model.addAttribute("username", username);
            model.addAttribute("email", email);
            model.addAttribute("errors", errors);
            return "auth/sign_up";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        sessionService.deleteSession(request);
        return "redirect:/";
    }

    @PostMapping("/logout")
    public String logoutPost(HttpServletRequest request) {
        sessionService.deleteSession(request);
        return "redirect:/";
    }
}
