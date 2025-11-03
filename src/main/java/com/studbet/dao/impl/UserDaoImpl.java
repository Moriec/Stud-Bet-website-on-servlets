package com.studbet.dao.impl;

import com.studbet.dao.UserDao;
import com.studbet.model.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImpl implements UserDao {

    DataSource dataSource;

    private final String SQL_SAVE_USER = "insert into users (username, email, password_hash, role, balance) values (?, ?, ?, ?, ?) " +
            "RETURNING user_id, username, email, role";
    private final String SQL_FIND_BY_USERNAME = "select * from users where username = ?";
    private final String SQL_FIND_BY_EMAIL = "select * from users where email = ?";

    public UserDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public User save(User user) {
        try(Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SAVE_USER);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getRole());
            preparedStatement.setInt(5, user.getBalance());
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return new  User(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public User update(User user) {
        return null;
    }

    @Override
    public int delete(User user) {
        return 0;
    }

    @Override
    public User findById(int id) {
        return null;
    }

    @Override
    public User findByUsername(String username) {
        return findWithSqlReq(username, SQL_FIND_BY_USERNAME);
    }

    @Override
    public User findByEmail(String email) {
        return findWithSqlReq(email, SQL_FIND_BY_EMAIL);
    }

    private User findWithSqlReq(String find, String sqlRequest) {
        try(Connection connection = dataSource.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(sqlRequest);
            preparedStatement.setString(1, find);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return new User(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getString(7),
                        resultSet.getInt(8),
                        resultSet.getInt(9),
                        resultSet.getTimestamp(10).toLocalDateTime(),
                        resultSet.getTimestamp(11) != null ? resultSet.getTimestamp(11).toLocalDateTime() : null
                );
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
