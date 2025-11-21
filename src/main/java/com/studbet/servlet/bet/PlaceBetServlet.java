package com.studbet.servlet.bet;

import com.studbet.dao.TransactionDao;
import com.studbet.model.Bet;
import com.studbet.model.User;
import com.studbet.security.session.UserSession;
import com.studbet.service.bet.BetService;
import com.studbet.service.entity.UserService;
import com.studbet.service.session.SessionService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "placeBet", urlPatterns = "/place-bet")
public class PlaceBetServlet extends HttpServlet {

    private SessionService sessionService;
    private BetService betService;
    private UserService userService;

    @Override
    public void init() throws ServletException {
        sessionService = (SessionService) getServletContext().getAttribute("sessionService");
        betService = (BetService) getServletContext().getAttribute("betService");
        userService = (UserService) getServletContext().getAttribute("userService");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserSession userSession = sessionService.getUserSession(req);

        if (userSession == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

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

            if (betAmount <= 0) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                out.print("{\"success\": false, \"message\": \"Сумма ставки должна быть больше 0\"}");
                return;
            }

            if (predictedScoreMin < 0 || predictedScoreMax < predictedScoreMin) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                out.print("{\"success\": false, \"message\": \"Некорректный диапазон прогноза\"}");
                return;
            }

            User user = userService.getUserById(userSession.getId());
            if (user == null) {
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                out.print("{\"success\": false, \"message\": \"Пользователь не найден\"}");
                return;
            }

            if (user.getBalance() < betAmount) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                out.print("{\"success\": false, \"message\": \"Недостаточно средств на балансе\"}");
                return;
            }

            Bet bet = new Bet();
            bet.setBettorId(user.getId());
            bet.setEventId(eventId);
            bet.setBetAmount(betAmount);
            bet.setPredictedScoreMin(predictedScoreMin);
            bet.setPredictedScoreMax(predictedScoreMax);

            Bet savedBet = betService.placeBet(bet, user);
            
            if (savedBet != null) {
                user = userService.getUserById(user.getId());
                resp.setStatus(HttpServletResponse.SC_OK);
                out.print("{\"success\": true, \"message\": \"Ставка успешно размещена\", \"newBalance\": " + user.getBalance() + "}");
            } else {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                out.print("{\"success\": false, \"message\": \"Не удалось разместить ставку\"}");
            }

        } catch (NumberFormatException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.print("{\"success\": false, \"message\": \"Некорректные числовые значения\"}");
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            out.print("{\"success\": false, \"message\": \"Внутренняя ошибка сервера\"}");
        }
    }
}

