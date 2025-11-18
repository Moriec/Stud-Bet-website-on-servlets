package com.studbet.servlet.main;

import com.studbet.service.auth.LoginService;
import com.studbet.service.session.SessionService;
import com.studbet.util.validate.UserValidate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "main", urlPatterns = "")
public class MainPageServlet extends HttpServlet {

    private static final String LOGIN_FTL_PATH = "WEB-INF/ftl/main.ftl";

    private SessionService sessionService;

    @Override
    public void init(){
        sessionService = (SessionService) getServletContext().getAttribute("sessionService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(sessionService.getUserSession(req) != null){
            req.setAttribute("user", sessionService.getUserSession(req));
        }
        req.getRequestDispatcher(LOGIN_FTL_PATH).forward(req, resp);
    }
}
