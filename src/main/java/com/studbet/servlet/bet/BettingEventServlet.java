package com.studbet.servlet.bet;

import com.studbet.dao.SubjectDao;
import com.studbet.model.BettingEvent;
import com.studbet.model.Subject;
import com.studbet.model.User;
import com.studbet.security.session.UserSession;
import com.studbet.service.bet.BetService;
import com.studbet.service.entity.SubjectService;
import com.studbet.service.entity.UserService;
import com.studbet.service.session.SessionService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "bettingEvent", urlPatterns = "/betting-event")
public class BettingEventServlet extends HttpServlet {

    private static final String FTL_PATH = "WEB-INF/ftl/bet/betting-event.ftl";

    private SessionService sessionService;
    private BetService betService;
    private SubjectService subjectService;
    private UserService  userService;

    @Override
    public void init() throws ServletException {
        sessionService = (SessionService) getServletContext().getAttribute("sessionService");
        betService = (BetService) getServletContext().getAttribute("betService");
        subjectService = (SubjectService) getServletContext().getAttribute("subjectService");
        userService = (UserService) getServletContext().getAttribute("userService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserSession userSession = sessionService.getUserSession(req);

        if (userSession == null) {
            resp.sendRedirect("login");
            return;
        }

        String eventIdStr = req.getParameter("id");
        if (eventIdStr == null || eventIdStr.trim().isEmpty()) {
            resp.sendRedirect(getServletContext().getContextPath());
            return;
        }

        int eventId;
        try {
            eventId = Integer.parseInt(eventIdStr);
        } catch (NumberFormatException e) {
            resp.sendRedirect(getServletContext().getContextPath());
            return;
        }

        BettingEvent event = betService.getBettingEventById(eventId);
        if (event == null || !"OPEN".equals(event.getStatus())) {
            resp.sendRedirect(getServletContext().getContextPath());
            return;
        }

        Subject subject = subjectService.getSubjectById(event.getSubjectId());

        User user = userService.getUserById(userSession.getId());
        
        req.setAttribute("event", event);
        req.setAttribute("subject", subject);
        req.setAttribute("user", userSession);
        req.setAttribute("userBalance", user != null ? user.getBalance() : 0);
        req.setAttribute("maxScore", subject != null ? subject.getMaxScore() : 100);

        req.getRequestDispatcher(FTL_PATH).forward(req, resp);
    }
}

