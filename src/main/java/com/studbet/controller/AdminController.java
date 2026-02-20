package com.studbet.controller;

import com.studbet.enums.Role;
import com.studbet.model.Achievement;
import com.studbet.model.BettingEvent;
import com.studbet.model.StudentResult;
import com.studbet.model.Subject;
import com.studbet.model.User;
import com.studbet.service.bet.BetCompleteService;
import com.studbet.service.entity.AchievementService;
import com.studbet.service.entity.BettingEventService;
import com.studbet.service.entity.StudentResultService;
import com.studbet.service.entity.SubjectService;
import com.studbet.service.entity.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final UserService userService;
    private final SubjectService subjectService;
    private final AchievementService achievementService;
    private final BettingEventService bettingEventService;
    private final StudentResultService studentResultService;
    private final BetCompleteService betCompleteService;

    @GetMapping
    public String adminPanel() {
        return "admin/admin-panel";
    }

    @GetMapping("/create-user")
    public String createUserPage() {
        return "admin/admin-create-user";
    }

    @PostMapping("/create-user")
    public String createUser(@RequestParam String username, @RequestParam String email, 
                             @RequestParam String password, @RequestParam String role, Model model) {
        try {
            if (username.trim().isEmpty() || email.trim().isEmpty() || password.trim().isEmpty() || role.trim().isEmpty()) {
                model.addAttribute("message", "Ошибка: все обязательные поля должны быть заполнены");
                model.addAttribute("messageType", "error");
                return "admin/admin-create-user";
            }

            if (!role.equals(Role.STUDENT.name()) && !role.equals(Role.ADMIN.name())) {
                model.addAttribute("message", "Ошибка: неверная роль");
                model.addAttribute("messageType", "error");
                return "admin/admin-create-user";
            }

            User user = new User(username.trim(), email.trim(), password, role.trim());
            userService.createUser(user);

            model.addAttribute("message", "Успешно добавлено");
            model.addAttribute("messageType", "success");
        } catch (Exception e) {
            model.addAttribute("message", "Ошибка сервера");
            model.addAttribute("messageType", "error");
        }
        return "admin/admin-create-user";
    }

    @GetMapping("/create-subject")
    public String createSubjectPage() {
        return "admin/admin-create-subject";
    }

    @PostMapping("/create-subject")
    public String createSubject(@RequestParam String name, @RequestParam Integer maxScore, Model model) {
        try {
            Subject subject = new Subject();
            subject.setName(name);
            subject.setMaxScore(maxScore);
            subjectService.createSubject(subject);
            model.addAttribute("message", "Успешно добавлено");
            model.addAttribute("messageType", "success");
        } catch (Exception e) {
            model.addAttribute("message", "Ошибка сервера");
            model.addAttribute("messageType", "error");
        }
        return "admin/admin-create-subject";
    }

    @GetMapping("/create-achievement")
    public String createAchievementPage() {
        return "admin/admin-create-achievement";
    }

    @PostMapping("/create-achievement")
    public String createAchievement(@RequestParam String name, @RequestParam String description, 
                                    @RequestParam String type, @RequestParam Integer requirementValue, Model model) {
        try {
            Achievement achievement = new Achievement();
            achievement.setName(name);
            achievement.setDescription(description);
            achievement.setAchievementType(type);
            achievement.setPointsReward(requirementValue);
            achievementService.createAchievement(achievement);
            model.addAttribute("message", "Успешно добавлено");
            model.addAttribute("messageType", "success");
        } catch (Exception e) {
            model.addAttribute("message", "Ошибка сервера");
            model.addAttribute("messageType", "error");
        }
        return "admin/admin-create-achievement";
    }

    @GetMapping("/create-betting-event")
    public String createBettingEventPage() {
        return "admin/admin-create-betting-event";
    }

    @PostMapping("/create-betting-event")
    public String createBettingEvent(@RequestParam Integer targetUserId, @RequestParam Integer subjectId,
                                     @RequestParam Integer semester, @RequestParam String academicYear,
                                     @RequestParam String eventType, @RequestParam String status,
                                     @RequestParam String description, Model model) {
        try {
            BettingEvent event = new BettingEvent();
            event.setTargetUserId(targetUserId);
            event.setSubjectId(subjectId);
            event.setSemestr(semester);
            event.setAcademicYear(academicYear);
            event.setEventType(eventType);
            event.setStatus(status);
            event.setDescription(description);
            bettingEventService.createBettingEvent(event);
            model.addAttribute("message", "Успешно добавлено");
            model.addAttribute("messageType", "success");
        } catch (Exception e) {
            model.addAttribute("message", "Ошибка сервера");
            model.addAttribute("messageType", "error");
        }
        return "admin/admin-create-betting-event";
    }

    @GetMapping("/create-student-result")
    public String createStudentResultPage() {
        return "admin/admin-create-student-result";
    }

    @PostMapping("/create-student-result")
    public String createStudentResult(@RequestParam Integer userId, @RequestParam Integer subjectId,
                                      @RequestParam Integer actualScore, @RequestParam Integer semester,
                                      @RequestParam String academicYear, Model model) {
        try {
            StudentResult result = new StudentResult();
            result.setUserId(userId);
            result.setSubjectId(subjectId);
            result.setActualScore(actualScore);
            result.setSemestr(semester);
            result.setAcademicYear(academicYear);
            
            studentResultService.createStudentResult(result);
            betCompleteService.completeBet(result);
            
            model.addAttribute("message", "Результат добавлен, ставки обработаны");
            model.addAttribute("messageType", "success");
        } catch (Exception e) {
            model.addAttribute("message", "Ошибка сервера");
            model.addAttribute("messageType", "error");
        }
        return "admin/admin-create-student-result";
    }
}
