package com.studbet.servlet.liderboard;


import com.studbet.model.User;
import com.studbet.security.session.UserSession;
import com.studbet.service.entity.UserService;
import com.studbet.service.liderboard.LeaderboardService;
import com.studbet.service.session.SessionService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;


@WebServlet("/leaderboards")
public class LeaderboardServlet extends HttpServlet {

    private LeaderboardService leaderboardService;
    private SessionService sessionService;
    private UserService userService;

    private final String FTL_PATH = "/WEB-INF/ftl/leaderboard.ftl";

    @Override
    public void init() throws ServletException {
        leaderboardService = (LeaderboardService) getServletContext().getAttribute("leaderboardService");
        sessionService = (SessionService) getServletContext().getAttribute("sessionService");
        userService = (UserService) getServletContext().getAttribute("userService");
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
            User currentUser = userService.getUserById(userId);
            currentUser.setPassword(null);
            currentUser.setEmail(null);

            Map<String, Object> currentUserRank = leaderboardService.getUserRankInfo(userId);

            List<Map<String, Object>> topUsers = leaderboardService.getTop10Users();

            request.setAttribute("currentUser", currentUser);
            request.setAttribute("currentUserRank", currentUserRank);
            request.setAttribute("topUsers", topUsers);

            request.getRequestDispatcher(FTL_PATH).forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                    "Ошибка при загрузке лидербода");
        }
    }
}