package com.studbet.servlet.profile;

import com.studbet.dto.ProfileUser;
import com.studbet.security.session.UserSession;
import com.studbet.service.profile.ProfileService;
import com.studbet.service.session.SessionService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "profile", urlPatterns = "/profile")
public class ProfileServlet extends HttpServlet {

    private static final String FTL_PATH = "/WEB-INF/ftl/profile/profile.ftl";

    private SessionService sessionService;
    private ProfileService profileService;

    @Override
    public void init() throws ServletException {
        sessionService = (SessionService) getServletContext().getAttribute("sessionService");
        profileService = (ProfileService) getServletContext().getAttribute("profileService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userIdStr = request.getParameter("id");
        UserSession currentUserSession = sessionService.getUserSession(request);
        
        int userId;
        if (userIdStr != null && !userIdStr.trim().isEmpty()) {
            try {
                userId = Integer.parseInt(userIdStr);
            } catch (NumberFormatException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid user ID");
                return;
            }
        } else {
            // Если ID не передан, то профиль текущго пользователя
            if (currentUserSession == null) {
                response.sendRedirect("login");
                return;
            }
            userId = currentUserSession.getId();
        }

        ProfileUser profileUser = profileService.findById(userId);
        if (profileUser == null) {
            request.getRequestDispatcher(FTL_PATH).forward(request, response);
        }

        boolean isOwnProfile = currentUserSession != null && currentUserSession.getId() == userId;

        request.setAttribute("profileUser", profileUser);
        request.setAttribute("isOwnProfile", isOwnProfile);
        if (currentUserSession != null) {
            request.setAttribute("user", currentUserSession);
        }

        request.getRequestDispatcher(FTL_PATH).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserSession currentUserSession = sessionService.getUserSession(request);
        
        if (currentUserSession == null) {
            response.sendRedirect("login");
            return;
        }

        String userIdStr = request.getParameter("userId");
        if (userIdStr == null || userIdStr.trim().isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "User ID is required");
            return;
        }

        int userId;
        try {
            userId = Integer.parseInt(userIdStr);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid user ID");
            return;
        }

        // Проверяем, что пользователь редактирует свой профиль
        if (currentUserSession.getId() != userId) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "You can only edit your own profile");
            return;
        }

        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");

        ProfileUser user = profileService.findById(userId);
        if (user == null) {
            request.getRequestDispatcher(FTL_PATH).forward(request, response);
            return;
        }

        user.setFirstname(firstname != null ? firstname.trim() : null);
        user.setLastname(lastname != null ? lastname.trim() : null);

        try {
            profileService.updateName(user);
        }catch (Exception e){
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Много символов");
        }

        request.setAttribute("message", "Профиль успешно обновлен");
        request.setAttribute("messageType", "success");
        request.setAttribute("profileUser", user);
        request.setAttribute("isOwnProfile", true);
        request.setAttribute("user", currentUserSession);

        request.getRequestDispatcher(FTL_PATH).forward(request, response);
    }
}
