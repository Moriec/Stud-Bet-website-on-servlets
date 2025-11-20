package com.studbet.servlet.admin;

import com.studbet.model.BettingEvent;
import com.studbet.service.entity.BettingEventService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "create-betting-event", urlPatterns = "/admin/create-betting-event")
public class CreateBettingEventServlet extends HttpServlet {

    private static final String FTL_PATH = "/WEB-INF/ftl/admin/admin-create-betting-event.ftl";

    BettingEventService bettingEventService;

    @Override
    public void init() throws ServletException {
        bettingEventService = (BettingEventService) getServletContext().getAttribute("bettingEventService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(FTL_PATH).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            String targetUserIdStr = request.getParameter("targetUserId");
            String subjectIdStr = request.getParameter("subjectId");
            String semestrStr = request.getParameter("semestr");
            String academicYear = request.getParameter("academicYear");
            String eventType = request.getParameter("eventType");
            String status = request.getParameter("status");
            String description = request.getParameter("description");

            if (targetUserIdStr == null || targetUserIdStr.trim().isEmpty() ||
                    subjectIdStr == null || subjectIdStr.trim().isEmpty() ||
                    semestrStr == null || semestrStr.trim().isEmpty() ||
                    academicYear == null || academicYear.trim().isEmpty() ||
                    eventType == null || eventType.trim().isEmpty() ||
                    status == null || status.trim().isEmpty()) {

                request.setAttribute("message", "Ошибка: все обязательные поля должны быть заполнены");
                request.setAttribute("messageType", "error");
                request.getRequestDispatcher(FTL_PATH).forward(request, response);
                return;
            }

            try {
                int targetUserId = Integer.parseInt(targetUserIdStr);
                int subjectId = Integer.parseInt(subjectIdStr);
                int semestr = Integer.parseInt(semestrStr);

                if (targetUserId < 1 || subjectId < 1 || semestr < 1 || semestr > 2) {
                    request.setAttribute("message", "Ошибка: неверные значения полей");
                    request.setAttribute("messageType", "error");
                    request.getRequestDispatcher(FTL_PATH).forward(request, response);
                    return;
                }

                BettingEvent bettingEvent = new BettingEvent(
                        targetUserId,
                        subjectId,
                        semestr,
                        academicYear.trim(),
                        eventType.trim(),
                        status.trim(),
                        description != null ? description.trim() : ""
                );
                bettingEventService.createBettingEvent(bettingEvent);

                request.setAttribute("message", "Успешно добавлено");
                request.setAttribute("messageType", "success");
                request.getRequestDispatcher(FTL_PATH).forward(request, response);

            } catch (NumberFormatException e) {
                request.setAttribute("message", "Ошибка: неверный формат числовых полей");
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

