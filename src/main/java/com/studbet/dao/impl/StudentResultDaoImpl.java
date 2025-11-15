package com.studbet.dao.impl;

import com.studbet.dao.StudentResultDao;
import com.studbet.model.StudentResult;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentResultDaoImpl implements StudentResultDao {

    private final DataSource dataSource;

    private final String SQL_SAVE = "insert into student_result (user_id, subject_id, actual_score, semester, academic_year, is_finelized) values (?, ?, ?, ?, ?, ?) " +
            "RETURNING result_id, user_id, subject_id, actual_score, semester, academic_year, is_finelized";
    private final String SQL_UPDATE = "update student_result set user_id = ?, subject_id = ?, actual_score = ?, semester = ?, academic_year = ?, is_finelized = ? where result_id = ?";
    private final String SQL_DELETE = "delete from student_result where result_id = ?";
    private final String SQL_FIND_BY_ID = "select * from student_result where result_id = ?";
    private final String SQL_FIND_ALL = "select * from student_result";
    private final String SQL_FIND_BY_USER_ID = "select * from student_result where user_id = ?";
    private final String SQL_FIND_BY_SUBJECT_ID = "select * from student_result where subject_id = ?";

    public StudentResultDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public StudentResult save(StudentResult result) {
        try(Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SAVE);
            preparedStatement.setInt(1, result.getUserId());
            preparedStatement.setInt(2, result.getSubjectId());
            preparedStatement.setInt(3, result.getActualScore());
            preparedStatement.setInt(4, result.getSemestr());
            preparedStatement.setString(5, result.getAcademicYear());
            preparedStatement.setBoolean(6, result.isFinelized());
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToStudentResult(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public StudentResult update(StudentResult result) {
        try(Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE);
            preparedStatement.setInt(1, result.getUserId());
            preparedStatement.setInt(2, result.getSubjectId());
            preparedStatement.setInt(3, result.getActualScore());
            preparedStatement.setInt(4, result.getSemestr());
            preparedStatement.setString(5, result.getAcademicYear());
            preparedStatement.setBoolean(6, result.isFinelized());
            preparedStatement.setInt(7, result.getResultId());
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                return findById(result.getResultId());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public int delete(StudentResult result) {
        try(Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE);
            preparedStatement.setInt(1, result.getResultId());
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                return result.getResultId();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    @Override
    public StudentResult findById(int id) {
        try(Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return mapResultSetToStudentResult(resultSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public List<StudentResult> getAll() {
        List<StudentResult> results = new ArrayList<>();
        try(Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_ALL);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                results.add(mapResultSetToStudentResult(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return results;
    }

    @Override
    public List<StudentResult> findByUserId(int userId) {
        List<StudentResult> results = new ArrayList<>();
        try(Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_USER_ID);
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                results.add(mapResultSetToStudentResult(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return results;
    }

    @Override
    public List<StudentResult> findBySubjectId(int subjectId) {
        List<StudentResult> results = new ArrayList<>();
        try(Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_SUBJECT_ID);
            preparedStatement.setInt(1, subjectId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                results.add(mapResultSetToStudentResult(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return results;
    }

    private StudentResult mapResultSetToStudentResult(ResultSet resultSet) throws SQLException {
        return new StudentResult(
                resultSet.getInt("result_id"),
                resultSet.getInt("user_id"),
                resultSet.getInt("subject_id"),
                resultSet.getInt("actual_score"),
                resultSet.getInt("semester"),
                resultSet.getString("academic_year"),
                resultSet.getBoolean("is_finelized")
        );
    }
}

