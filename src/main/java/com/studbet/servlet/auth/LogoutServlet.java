package com.studbet.servlet.auth;

import com.studbet.service.session.SessionService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "logout", urlPatterns = "/logout")
public class LogoutServlet extends HttpServlet {

    SessionService sessionService;

    @Override
    public void init(){
        sessionService = (SessionService) getServletContext().getAttribute("sessionService");
    }

    @Override
    protected void doGet(HttpServletRequest req,  HttpServletResponse resp) throws IOException {
        sessionService.deleteSession(req);
        resp.sendRedirect(getServletContext().getContextPath());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        sessionService.deleteSession(req);
        resp.sendRedirect(getServletContext().getContextPath());
    }
}
