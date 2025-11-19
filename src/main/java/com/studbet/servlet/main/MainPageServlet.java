package com.studbet.servlet.main;

import com.studbet.service.main.MainPageService;
import com.studbet.service.session.SessionService;

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
    private MainPageService mainPageService;

    @Override
    public void init(){
        sessionService = (SessionService) getServletContext().getAttribute("sessionService");
        mainPageService = (MainPageService) getServletContext().getAttribute("mainPageService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(sessionService.getUserSession(req) != null){
            req.setAttribute("user", sessionService.getUserSession(req));
        }
        
        req.setAttribute("bettingEvents", mainPageService.getAviableBettingEvents());
        
        req.getRequestDispatcher(LOGIN_FTL_PATH).forward(req, resp);
    }
}
