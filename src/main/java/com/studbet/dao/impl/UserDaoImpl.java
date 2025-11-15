package com.studbet.dao.impl;

import com.studbet.dao.UserDao;
import com.studbet.model.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {

    DataSource dataSource;

    private final String SQL_SAVE_USER = "insert into users (username, email, first_name, last_name, password_hash, role, balance) values (?, ?, ?, ?, ?, ?, ?) " +
            "RETURNING user_id, username, email, first_name, last_name, password_hash, role, balance, rating_points, registration_date, last_login";
    private final String SQL_UPDATE_USER = "update users set username = ?, email = ?, first_name = ?, last_name = ?, password_hash = ?, role = ?, balance = ?, rating_points = ?, last_login = ? where user_id = ?";
    private final String SQL_DELETE_USER = "delete from users where user_id = ?";
    private final String SQL_FIND_BY_ID = "select * from users where user_id = ?";
    private final String SQL_FIND_ALL = "select * from users";
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
            preparedStatement.setString(3, user.getFirstname());
            preparedStatement.setString(4, user.getLastname());
            preparedStatement.setString(5, user.getPassword());
            preparedStatement.setString(6, user.getRole());
            preparedStatement.setInt(7, user.getBalance());
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToUser(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public User update(User user) {
        try(Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_USER);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getFirstname());
            preparedStatement.setString(4, user.getLastname());
            preparedStatement.setString(5, user.getPassword());
            preparedStatement.setString(6, user.getRole());
            preparedStatement.setInt(7, user.getBalance());
            preparedStatement.setInt(8, user.getRatingPoints());
            preparedStatement.setTimestamp(9, user.getLastLoginDate() != null ? Timestamp.valueOf(user.getLastLoginDate()) : null);
            preparedStatement.setInt(10, user.getId());
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                return findById(user.getId());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public int delete(User user) {
        try(Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_USER);
            preparedStatement.setInt(1, user.getId());
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                return user.getId();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    @Override
    public User findById(int id) {
        try(Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return mapResultSetToUser(resultSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        try(Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_ALL);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                users.add(mapResultSetToUser(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
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
                return mapResultSetToUser(resultSet);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    private User mapResultSetToUser(ResultSet resultSet) throws SQLException {
        return new User(
                resultSet.getInt("user_id"),
                resultSet.getString("username"),
                resultSet.getString("email"),
                resultSet.getString("first_name"),
                resultSet.getString("last_name"),
                resultSet.getString("password_hash"),
                resultSet.getString("role"),
                resultSet.getInt("balance"),
                resultSet.getInt("rating_points"),
                resultSet.getTimestamp("registration_date") != null ? resultSet.getTimestamp("registration_date").toLocalDateTime() : null,
                resultSet.getTimestamp("last_login") != null ? resultSet.getTimestamp("last_login").toLocalDateTime() : null
        );
    }
}
