package com.studbet.servlet.admin;

import com.studbet.model.Subject;
import com.studbet.service.entity.SubjectService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "create-subject", urlPatterns = "/admin/create-subject")
public class CreateSubjectServlet extends HttpServlet {

    private static final String FTL_PATH = "/WEB-INF/ftl/admin/admin-create-subject.ftl";

    SubjectService subjectService;

    @Override
    public void init() throws ServletException {
        subjectService = (SubjectService) getServletContext().getAttribute("subjectService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(FTL_PATH).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            String name = request.getParameter("name");
            String code = request.getParameter("code");
            String description = request.getParameter("description");
            String maxScoreStr = request.getParameter("maxScore");

            if (name == null || name.trim().isEmpty() ||
                    code == null || code.trim().isEmpty() ||
                    maxScoreStr == null || maxScoreStr.trim().isEmpty()) {

                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                return;
            }

            try {
                int maxScore = Integer.parseInt(maxScoreStr);
                if (maxScore < 1) {
                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    return;
                }

                Subject subject = new Subject(name.trim(), code.trim(), description.trim(), maxScore);
                subjectService.createSubject(subject);

                request.setAttribute("message", "Успешно добавлено");
                request.getRequestDispatcher(FTL_PATH).forward(request, response);

            } catch (NumberFormatException e) {
                request.setAttribute("message", "Ошибка: плохой запрос");
                request.getRequestDispatcher(FTL_PATH).forward(request, response);
            }

        } catch (Exception e) {
            request.setAttribute("message", "Ошибка сервера");
            request.getRequestDispatcher(FTL_PATH).forward(request, response);
        }
    }
}
