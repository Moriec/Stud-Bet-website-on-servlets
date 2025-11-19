package com.studbet.servlet.admin;

import com.studbet.model.Achievement;
import com.studbet.service.entity.AchievementService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "create-achievement", urlPatterns = "/admin/create-achievement")
public class CreateAchievementServlet extends HttpServlet {

    private static final String FTL_PATH = "/WEB-INF/ftl/admin/admin-create-achievement.ftl";

    AchievementService achievementService;

    @Override
    public void init() throws ServletException {
        achievementService = (AchievementService) getServletContext().getAttribute("achievementService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(FTL_PATH).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            String name = request.getParameter("name");
            String description = request.getParameter("description");
            String iconUrl = request.getParameter("iconUrl");
            String pointsRewardStr = request.getParameter("pointsReward");
            String achievementType = request.getParameter("achievementType");
            String criteriaJson = request.getParameter("criteriaJson");

            if (name == null || name.trim().isEmpty() ||
                    pointsRewardStr == null || pointsRewardStr.trim().isEmpty()) {

                request.setAttribute("message", "Ошибка: все обязательные поля должны быть заполнены");
                request.setAttribute("messageType", "error");
                request.getRequestDispatcher(FTL_PATH).forward(request, response);
                return;
            }

            try {
                int pointsReward = Integer.parseInt(pointsRewardStr);
                if (pointsReward < 0) {
                    request.setAttribute("message", "Ошибка: награда не может быть отрицательной");
                    request.setAttribute("messageType", "error");
                    request.getRequestDispatcher(FTL_PATH).forward(request, response);
                    return;
                }

                Achievement achievement = new Achievement(
                        name.trim(),
                        description != null ? description.trim() : "",
                        iconUrl != null ? iconUrl.trim() : "",
                        pointsReward,
                        achievementType != null ? achievementType.trim() : "",
                        criteriaJson != null ? criteriaJson.trim() : ""
                );
                achievementService.createAchievement(achievement);

                request.setAttribute("message", "Успешно добавлено");
                request.setAttribute("messageType", "success");
                request.getRequestDispatcher(FTL_PATH).forward(request, response);

            } catch (NumberFormatException e) {
                request.setAttribute("message", "Ошибка: неверный формат награды");
                request.setAttribute("messageType", "error");
                request.getRequestDispatcher(FTL_PATH).forward(request, response);
            }

        } catch (Exception e) {
            request.setAttribute("message", "Ошибка сервера");
            request.setAttribute("messageType", "error");
            request.getRequestDispatcher(FTL_PATH).forward(request, response);
        }
    }
}

