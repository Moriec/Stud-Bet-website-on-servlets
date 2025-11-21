package com.studbet.servlet.admin;

import com.studbet.model.StudentResult;
import com.studbet.service.bet.BetCompleteService;
import com.studbet.service.entity.StudentResultService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "create-student-result", urlPatterns = "/admin/create-student-result")
public class CreateStudentResultServlet extends HttpServlet {

    private static final String FTL_PATH = "/WEB-INF/ftl/admin/admin-create-student-result.ftl";

    StudentResultService studentResultService;
    BetCompleteService betCompleteService;

    @Override
    public void init() throws ServletException {
        studentResultService = (StudentResultService) getServletContext().getAttribute("studentResultService");
        betCompleteService = (BetCompleteService) getServletContext().getAttribute("betCompleteService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(FTL_PATH).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            String userIdStr = request.getParameter("userId");
            String subjectIdStr = request.getParameter("subjectId");
            String actualScoreStr = request.getParameter("actualScore");
            String semestrStr = request.getParameter("semestr");
            String academicYear = request.getParameter("academicYear");
            String isFinalizedStr = request.getParameter("isFinalized");

            if (userIdStr == null || userIdStr.trim().isEmpty() ||
                    subjectIdStr == null || subjectIdStr.trim().isEmpty() ||
                    actualScoreStr == null || actualScoreStr.trim().isEmpty() ||
                    semestrStr == null || semestrStr.trim().isEmpty() ||
                    academicYear == null || academicYear.trim().isEmpty()) {

                request.setAttribute("message", "Ошибка: все обязательные поля должны быть заполнены");
                request.setAttribute("messageType", "error");
                request.getRequestDispatcher(FTL_PATH).forward(request, response);
                return;
            }

            try {
                int userId = Integer.parseInt(userIdStr);
                int subjectId = Integer.parseInt(subjectIdStr);
                int actualScore = Integer.parseInt(actualScoreStr);
                int semestr = Integer.parseInt(semestrStr);

                if (actualScore < 0) {
                    request.setAttribute("message", "Ошибка: неверные значения полей");
                    request.setAttribute("messageType", "error");
                    request.getRequestDispatcher(FTL_PATH).forward(request, response);
                    return;
                }

                boolean isFinalized = isFinalizedStr != null && isFinalizedStr.equals("true");

                StudentResult studentResult = new StudentResult(
                        userId,
                        subjectId,
                        actualScore,
                        semestr,
                        academicYear.trim(),
                        isFinalized
                );
                studentResultService.createStudentResult(studentResult);

                betCompleteService.completeBet(studentResult);

                request.setAttribute("message", "Успешно добавлено");
                request.setAttribute("messageType", "success");
                request.getRequestDispatcher(FTL_PATH).forward(request, response);

            } catch (NumberFormatException e) {
                request.setAttribute("message", "Ошибка: неверный формат числовых полей");
                request.setAttribute("messageType", "error");
                request.getRequestDispatcher(FTL_PATH).forward(request, response);
            }

        } catch (Exception e) {
            request.setAttribute("message", "Ошибка сервера");
            request.setAttribute("messageType", "error");
            request.getRequestDispatcher(FTL_PATH).forward(request, response);
        }
    }
}

