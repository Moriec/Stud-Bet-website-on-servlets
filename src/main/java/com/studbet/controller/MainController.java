package com.studbet.controller;

import com.studbet.service.main.MainPageService;
import com.studbet.service.session.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final SessionService sessionService;
    private final MainPageService mainPageService;

    @GetMapping("/")
    public String mainPage(HttpServletRequest request, Model model) {
        if (sessionService.getUserSession(request) != null) {
            model.addAttribute("user", sessionService.getUserSession(request));
        }
        model.addAttribute("bettingEvents", mainPageService.getAviableBettingEvents());
        return "main";
    }

    @GetMapping("/faq")
    public String faqPage() {
        return "faq";
    }

    @GetMapping("/rules")
    public String rulesPage() {
        return "rules";
    }
}
