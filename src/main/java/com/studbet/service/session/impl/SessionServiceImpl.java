package com.studbet.service.session.impl;

import com.studbet.security.session.UserSession;
import com.studbet.service.session.SessionService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

@Service
public class SessionServiceImpl implements SessionService {

    @Override
    public void createSession(HttpServletRequest req, UserSession userSession) {
        // Инвалидируем старую сессию
        HttpSession oldSession = req.getSession(false);
        if (oldSession != null) {
            oldSession.invalidate();
        }

        // Создание новой сессии
        HttpSession newSession = req.getSession(true);

        if(userSession.getRole().equals("STUDENT")){
            newSession.setMaxInactiveInterval(60 * 60);
        }
        if(userSession.getRole().equals("ADMIN")){
            newSession.setMaxInactiveInterval(15 * 60);
        }

        newSession.setAttribute("userSession", userSession);
    }

    @Override
    public UserSession getUserSession(HttpServletRequest req) {
        HttpSession session = req.getSession();
        return (UserSession) session.getAttribute("userSession");
    }

    @Override
    public void deleteSession(HttpServletRequest req) {
        HttpSession session = req.getSession();

        if(session != null) {
            session.invalidate();
        }
    }
}
