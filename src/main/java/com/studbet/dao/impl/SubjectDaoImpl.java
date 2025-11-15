package com.studbet.dao.impl;

import com.studbet.dao.SubjectDao;
import com.studbet.model.Subject;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SubjectDaoImpl implements SubjectDao {

    private final DataSource dataSource;

    private final String SQL_SAVE = "insert into subjects (subject_name, subject_code, description, max_score) values (?, ?, ?, ?) " +
            "RETURNING subject_id, subject_name, subject_code, description, max_score";
    private final String SQL_UPDATE = "update subjects set subject_name = ?, subject_code = ?, description = ?, max_score = ? where subject_id = ?";
    private final String SQL_DELETE = "delete from subjects where subject_id = ?";
    private final String SQL_FIND_BY_ID = "select * from subjects where subject_id = ?";
    private final String SQL_FIND_ALL = "select * from subjects";
    private final String SQL_FIND_BY_CODE = "select * from subjects where subject_code = ?";

    public SubjectDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Subject save(Subject subject) {
        try(Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SAVE);
            preparedStatement.setString(1, subject.getName());
            preparedStatement.setString(2, subject.getCode());
            preparedStatement.setString(3, subject.getDescription());
            preparedStatement.setInt(4, subject.getMaxScore());
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToSubject(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Subject update(Subject subject) {
        try(Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE);
            preparedStatement.setString(1, subject.getName());
            preparedStatement.setString(2, subject.getCode());
            preparedStatement.setString(3, subject.getDescription());
            preparedStatement.setInt(4, subject.getMaxScore());
            preparedStatement.setInt(5, subject.getId());
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                return findById(subject.getId());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public int delete(Subject subject) {
        try(Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE);
            preparedStatement.setInt(1, subject.getId());
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                return subject.getId();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    @Override
    public Subject findById(int id) {
        try(Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return mapResultSetToSubject(resultSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public List<Subject> getAll() {
        List<Subject> subjects = new ArrayList<>();
        try(Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_ALL);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                subjects.add(mapResultSetToSubject(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return subjects;
    }

    @Override
    public Subject findByCode(String code) {
        try(Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_CODE);
            preparedStatement.setString(1, code);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return mapResultSetToSubject(resultSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    private Subject mapResultSetToSubject(ResultSet resultSet) throws SQLException {
        return new Subject(
                resultSet.getInt("subject_id"),
                resultSet.getString("subject_name"),
                resultSet.getString("subject_code"),
                resultSet.getString("description"),
                resultSet.getInt("max_score")
        );
    }
}

