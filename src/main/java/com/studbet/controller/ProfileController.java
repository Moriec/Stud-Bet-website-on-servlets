package com.studbet.controller;

import com.studbet.dto.ProfileUser;
import com.studbet.model.Transaction;
import com.studbet.security.session.UserSession;
import com.studbet.service.bet.BetService;
import com.studbet.service.profile.ProfileService;
import com.studbet.service.session.SessionService;
import com.studbet.service.transaction.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/profile")
@RequiredArgsConstructor
public class ProfileController {

    private final SessionService sessionService;
    private final ProfileService profileService;
    private final TransactionService transactionService;
    private final BetService betService;

    @GetMapping
    public String profilePage(@RequestParam(required = false) Integer id, HttpServletRequest request, Model model) {
        UserSession currentUserSession = sessionService.getUserSession(request);
        
        int userId;
        if (id != null) {
            userId = id;
        } else {
            if (currentUserSession == null) {
                return "redirect:/login";
            }
            userId = currentUserSession.getId();
        }

        ProfileUser profileUser = profileService.findById(userId);
        if (profileUser == null) {
            return "profile/profile";
        }

        boolean isOwnProfile = currentUserSession != null && currentUserSession.getId() == userId;

        model.addAttribute("profileUser", profileUser);
        model.addAttribute("isOwnProfile", isOwnProfile);
        if (currentUserSession != null) {
            model.addAttribute("user", currentUserSession);
        }

        return "profile/profile";
    }

    @PostMapping
    public String updateProfile(@RequestParam Integer userId, @RequestParam String firstname, @RequestParam String lastname, HttpServletRequest request) {
        UserSession currentUserSession = sessionService.getUserSession(request);
        
        if (currentUserSession == null) {
            return "redirect:/login";
        }

        if (currentUserSession.getId() != userId) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You can only edit your own profile");
        }

        ProfileUser user = profileService.findById(userId);
        if (user != null) {
            user.setFirstname(firstname);
            user.setLastname(lastname);
            profileService.updateName(user);
        }
        
        return "redirect:/profile?id=" + userId;
    }

    @GetMapping("/transactions")
    public String transactionsPage(HttpServletRequest request, Model model) {
        UserSession userSession = sessionService.getUserSession(request);
        if (userSession == null) {
            return "redirect:/login";
        }

        List<Transaction> transactions = transactionService.findByUserId(userSession.getId());
        model.addAttribute("transactions", transactions);
        return "profile/transactions";
    }

    @GetMapping("/my-bets")
    public String myBetsPage(HttpServletRequest request, Model model) {
        UserSession userSession = sessionService.getUserSession(request);
        if (userSession == null) {
            return "redirect:/login";
        }

        int userId = userSession.getId();
        List<Map<String, Object>> betsWithEvents = betService.getUserBetsWithEvents(userId);
        Map<String, Integer> stats = betService.getUserBetsStats(userId);

        model.addAttribute("bets", betsWithEvents);
        model.addAttribute("stats", stats);

        return "bet/my-bets";
    }
}
