package com.studbet.service.session;

import com.studbet.security.session.UserSession;

import javax.servlet.http.HttpServletRequest;

public interface SessionService {
    void createSession(HttpServletRequest req, UserSession userSession); // Создать новую сессию с данными пользователя
    UserSession getUserSession(HttpServletRequest req); // Получить данные пользователя, которые лежат в сессии
    void deleteSession(HttpServletRequest req);
}
