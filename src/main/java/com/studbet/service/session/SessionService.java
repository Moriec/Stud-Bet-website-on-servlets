package com.studbet.service.session;

import com.studbet.security.session.UserSession;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public interface SessionService {
    void createSession(HttpServletRequest req, UserSession userSession); // Создать новую сессию с данными пользователя
    UserSession getUserSession(HttpServletRequest req); // Получить данные пользователя, которые лежат в сессии
    void deleteSession(HttpServletRequest req);
}
