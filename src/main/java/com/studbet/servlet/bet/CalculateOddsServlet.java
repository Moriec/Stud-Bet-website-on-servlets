package com.studbet.servlet.bet;

import com.studbet.dao.BettingEventDao;
import com.studbet.dao.SubjectDao;
import com.studbet.model.BettingEvent;
import com.studbet.model.Subject;
import com.studbet.service.bet.BetService;
import com.studbet.service.entity.BettingEventService;
import com.studbet.service.entity.SubjectService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "calculateOdds", urlPatterns = "/calculate-odds")
public class CalculateOddsServlet extends HttpServlet {

    private BetService betService;
    private SubjectService subjectService;

    @Override
    public void init() throws ServletException {
        betService = (BetService) getServletContext().getAttribute("betService");
        subjectService = (SubjectService) getServletContext().getAttribute("subjectService");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String eventIdStr = req.getParameter("eventId");
        String betAmountStr = req.getParameter("betAmount");
        String predictedScoreMinStr = req.getParameter("predictedScoreMin");
        String predictedScoreMaxStr = req.getParameter("predictedScoreMax");

        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();

        if (eventIdStr == null || betAmountStr == null || predictedScoreMinStr == null || predictedScoreMaxStr == null) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.print("{\"success\": false, \"message\": \"Не все параметры указаны\"}");
            return;
        }

        try {
            int eventId = Integer.parseInt(eventIdStr);
            int betAmount = Integer.parseInt(betAmountStr);
            int predictedScoreMin = Integer.parseInt(predictedScoreMinStr);
            int predictedScoreMax = Integer.parseInt(predictedScoreMaxStr);

            BettingEvent event = betService.getBettingEventById(eventId);
            if (event == null) {
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                out.print("{\"success\": false, \"message\": \"Событие не найдено\"}");
                return;
            }

            Subject subject = subjectService.getSubjectById(event.getSubjectId());
            int maxScore = subject != null ? subject.getMaxScore() : 100;

            double odds = betService.calculateOdds(betAmount, predictedScoreMin, predictedScoreMax, maxScore);
            int payoutAmount = (int) Math.round(betAmount * odds);

            resp.setStatus(HttpServletResponse.SC_OK);
            out.print("{\"success\": true, \"odds\": " + odds + ", \"payoutAmount\": " + payoutAmount + "}");

        } catch (NumberFormatException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.print("{\"success\": false, \"message\": \"Некорректные числовые значения\"}");
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            out.print("{\"success\": false, \"message\": \"Внутренняя ошибка сервера\"}");
        }
    }
}

