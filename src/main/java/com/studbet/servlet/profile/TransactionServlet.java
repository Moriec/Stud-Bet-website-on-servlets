package com.studbet.servlet.profile;


import com.studbet.model.Transaction;
import com.studbet.security.session.UserSession;
import com.studbet.service.session.SessionService;
import com.studbet.service.transaction.TransactionService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/profile/transactions")
public class TransactionServlet extends HttpServlet {
    private TransactionService transactionService;
    private SessionService sessionService;

    @Override
    public void init() throws ServletException {
        transactionService =  (TransactionService) getServletContext().getAttribute("transactionService");
        sessionService = (SessionService)getServletContext().getAttribute("sessionService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Проверка авторизации
        UserSession userSession = sessionService.getUserSession(request);

        // Получить ВСЕ транзакции
        List<Transaction> transactions = transactionService.findByUserId(userSession.getId());

        // Передать в FTL
        request.setAttribute("transactions", transactions);
        request.getRequestDispatcher("/WEB-INF/ftl/profile/transactions.ftl").forward(request, response);
    }
}
