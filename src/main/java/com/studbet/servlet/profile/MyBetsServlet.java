package com.studbet.servlet.profile;


import com.studbet.security.session.UserSession;
import com.studbet.service.bet.BetService;
import com.studbet.service.session.SessionService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;


@WebServlet("/profile/my-bets")
public class MyBetsServlet extends HttpServlet {

    private BetService betService;
    private SessionService sessionService;

    private final String FTL_PATH = "/WEB-INF/ftl/bet/my-bets.ftl";

    @Override
    public void init() throws ServletException {
        betService = (BetService) getServletContext().getAttribute("betService");
        sessionService = (SessionService) getServletContext().getAttribute("sessionService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            UserSession userSession = sessionService.getUserSession(request);
            if (userSession == null) {
                response.sendRedirect(request.getContextPath() + "/login");
                return;
            }

            int userId = userSession.getId();
            List<Map<String, Object>> betsWithEvents = betService.getUserBetsWithEvents(userId);
            Map<String, Integer> stats = betService.getUserBetsStats(userId);

            request.setAttribute("bets", betsWithEvents);
            request.setAttribute("stats", stats);

            request.getRequestDispatcher(FTL_PATH).forward(request, response);

        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                    "Ошибка при загрузке ставок");
        }
    }
}