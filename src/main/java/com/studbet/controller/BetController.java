package com.studbet.controller;

import com.studbet.model.Bet;
import com.studbet.model.BettingEvent;
import com.studbet.model.Subject;
import com.studbet.model.User;
import com.studbet.security.session.UserSession;
import com.studbet.service.bet.BetService;
import com.studbet.service.entity.SubjectService;
import com.studbet.service.entity.UserService;
import com.studbet.service.session.SessionService;
import com.studbet.service.liderboard.LeaderboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class BetController {

    private final SessionService sessionService;
    private final BetService betService;
    private final SubjectService subjectService;
    private final UserService userService;
    private final LeaderboardService leaderboardService;

    @GetMapping("/betting-event")
    public String bettingEventPage(@RequestParam Integer id, HttpServletRequest request, Model model) {
        UserSession userSession = sessionService.getUserSession(request);

        if (userSession == null) {
            return "redirect:/login";
        }

        BettingEvent event = betService.getBettingEventById(id);
        if (event == null || !"OPEN".equals(event.getStatus())) {
            return "redirect:/";
        }

        Subject subject = subjectService.getSubjectById(event.getSubjectId());
        User user = userService.getUserById(userSession.getId());
        
        model.addAttribute("event", event);
        model.addAttribute("subject", subject);
        model.addAttribute("user", userSession);
        model.addAttribute("userBalance", user != null ? user.getBalance() : 0);
        model.addAttribute("maxScore", subject != null ? subject.getMaxScore() : 100);

        return "bet/betting-event";
    }

    @PostMapping("/place-bet")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> placeBet(
            @RequestParam Integer eventId,
            @RequestParam Integer betAmount,
            @RequestParam Integer predictedScoreMin,
            @RequestParam Integer predictedScoreMax,
            HttpServletRequest request) {
        
        UserSession userSession = sessionService.getUserSession(request);
        Map<String, Object> response = new HashMap<>();

        if (userSession == null) {
            response.put("success", false);
            response.put("message", "Нужна авторизация");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }

        if (betAmount <= 0) {
            response.put("success", false);
            response.put("message", "Сумма ставки должна быть больше 0");
            return ResponseEntity.badRequest().body(response);
        }

        if (predictedScoreMin < 0 || predictedScoreMax < predictedScoreMin) {
            response.put("success", false);
            response.put("message", "Некорректный диапазон прогноза");
            return ResponseEntity.badRequest().body(response);
        }

        User user = userService.getUserById(userSession.getId());
        if (user == null) {
            response.put("success", false);
            response.put("message", "Пользователь не найден");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        if (user.getBalance() < betAmount) {
            response.put("success", false);
            response.put("message", "Недостаточно средств на балансе");
            return ResponseEntity.badRequest().body(response);
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
            response.put("success", true);
            response.put("message", "Ставка успешно размещена");
            response.put("newBalance", user.getBalance());
            return ResponseEntity.ok(response);
        } else {
            response.put("success", false);
            response.put("message", "Не удалось разместить ставку");
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping("/leaderboards")
    public String leaderboardsPage(HttpServletRequest request, Model model) {
        UserSession userSession = sessionService.getUserSession(request);
        if (userSession == null) {
            return "redirect:/login";
        }

        int userId = userSession.getId();
        User currentUser = userService.getUserById(userId);
        currentUser.setPassword(null);
        currentUser.setEmail(null);

        Map<String, Object> currentUserRank = leaderboardService.getUserRankInfo(userId);
        List<Map<String, Object>> topUsers = leaderboardService.getTop10Users();

        model.addAttribute("currentUser", currentUser);
        model.addAttribute("currentUserRank", currentUserRank);
        model.addAttribute("topUsers", topUsers);

        return "leaderboard";
    }
}
