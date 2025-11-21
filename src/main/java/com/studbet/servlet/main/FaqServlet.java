package com.studbet.servlet.main;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/faq")
public class FaqServlet extends HttpServlet {

    private final String FTL_PATH = "WEB-INF/ftl/faq.ftl";

    @Override
    protected void doGet(HttpServletRequest req,  HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(FTL_PATH).forward(req, resp);
    }
}