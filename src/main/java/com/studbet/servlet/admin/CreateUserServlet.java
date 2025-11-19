package com.studbet.servlet.admin;

import com.studbet.enums.Role;
import com.studbet.model.User;
import com.studbet.service.entity.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "create-user", urlPatterns = "/admin/create-user")
public class CreateUserServlet extends HttpServlet {

    private static final String FTL_PATH = "/WEB-INF/ftl/admin/admin-create-user.ftl";

    UserService userService;

    @Override
    public void init() throws ServletException {
        userService = (UserService) getServletContext().getAttribute("userService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(FTL_PATH).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            String username = request.getParameter("username");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String role = request.getParameter("role");

            if (username == null || username.trim().isEmpty() ||
                    email == null || email.trim().isEmpty() ||
                    password == null || password.trim().isEmpty() ||
                    role == null || role.trim().isEmpty()) {

                request.setAttribute("message", "Ошибка: все обязательные поля должны быть заполнены");
                request.setAttribute("messageType", "error");
                request.getRequestDispatcher(FTL_PATH).forward(request, response);
                return;
            }

            if (!role.equals(Role.STUDENT.name()) && !role.equals(Role.ADMIN.name())) {
                request.setAttribute("message", "Ошибка: неверная роль");
                request.setAttribute("messageType", "error");
                request.getRequestDispatcher(FTL_PATH).forward(request, response);
                return;
            }

            User user = new User(username.trim(), email.trim(), password, role.trim());
            userService.createUser(user);

            request.setAttribute("message", "Успешно добавлено");
            request.setAttribute("messageType", "success");
            request.getRequestDispatcher(FTL_PATH).forward(request, response);

        } catch (Exception e) {
            request.setAttribute("message", "Ошибка сервера");
            request.setAttribute("messageType", "error");
            request.getRequestDispatcher(FTL_PATH).forward(request, response);
        }
    }
}

