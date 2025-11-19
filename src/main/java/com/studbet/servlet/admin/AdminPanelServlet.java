package com.studbet.servlet.admin;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin")
public class AdminPanelServlet extends HttpServlet {

    private final String FTL_PATH = "/WEB-INF/ftl/admin/admin-panel.ftl";

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher(FTL_PATH).forward(request, response);
    }
}
