package com.studbet.filter;

import com.studbet.enums.Role;
import com.studbet.security.session.UserSession;
import com.studbet.service.session.SessionService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/admin/*")
public class AdminAuthFilter implements Filter {

    SessionService sessionService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        ServletContext servletContext = filterConfig.getServletContext();
        sessionService = (SessionService) servletContext.getAttribute("sessionService");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        UserSession  userSession = sessionService.getUserSession((HttpServletRequest) request);

        if (userSession != null && userSession.getRole().equals(Role.ADMIN.name()) ) {
            // Пользователь авторизован как администратор
            chain.doFilter(request, response);
        } else {
            // Пользователь не имеет прав доступа
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/login");
        }
    }
}
