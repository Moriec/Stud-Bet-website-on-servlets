package com.studbet.dao.impl;

import com.studbet.dao.UserAchievementDao;
import com.studbet.model.UserAchievement;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserAchievementDaoImpl implements UserAchievementDao {

    private final DataSource dataSource;

    private final String SQL_SAVE = "insert into user_achievements (user_id, achievements_id) values (?, ?) " +
            "RETURNING user_id, achievements_id, earned_at";
    private final String SQL_UPDATE = "update user_achievements set earned_at = ? where user_id = ? and achievements_id = ?";
    private final String SQL_DELETE = "delete from user_achievements where user_id = ? and achievements_id = ?";
    private final String SQL_FIND_BY_ID = "select * from user_achievements where user_id = ? and achievements_id = ?";
    private final String SQL_FIND_ALL = "select * from user_achievements";
    private final String SQL_FIND_BY_USER_ID = "select * from user_achievements where user_id = ?";
    private final String SQL_FIND_BY_ACHIEVEMENT_ID = "select * from user_achievements where achievements_id = ?";

    public UserAchievementDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public UserAchievement save(UserAchievement userAchievement) {
        try(Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SAVE);
            preparedStatement.setInt(1, userAchievement.getUserId());
            preparedStatement.setInt(2, userAchievement.getAchievementId());
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToUserAchievement(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public UserAchievement update(UserAchievement userAchievement) {
        try(Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE);
            preparedStatement.setTimestamp(1, userAchievement.getEarned_at() != null ? 
                    java.sql.Timestamp.valueOf(userAchievement.getEarned_at()) : null);
            preparedStatement.setInt(2, userAchievement.getUserId());
            preparedStatement.setInt(3, userAchievement.getAchievementId());
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                return findById(userAchievement.getUserId(), userAchievement.getAchievementId());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public int delete(UserAchievement userAchievement) {
        try(Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE);
            preparedStatement.setInt(1, userAchievement.getUserId());
            preparedStatement.setInt(2, userAchievement.getAchievementId());
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                return userAchievement.getUserId();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    @Override
    public UserAchievement findById(int userId, int achievementId) {
        try(Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_ID);
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, achievementId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return mapResultSetToUserAchievement(resultSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public List<UserAchievement> getAll() {
        List<UserAchievement> userAchievements = new ArrayList<>();
        try(Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_ALL);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                userAchievements.add(mapResultSetToUserAchievement(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userAchievements;
    }

    @Override
    public List<UserAchievement> findByUserId(int userId) {
        List<UserAchievement> userAchievements = new ArrayList<>();
        try(Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_USER_ID);
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                userAchievements.add(mapResultSetToUserAchievement(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userAchievements;
    }

    @Override
    public List<UserAchievement> findByAchievementId(int achievementId) {
        List<UserAchievement> userAchievements = new ArrayList<>();
        try(Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_ACHIEVEMENT_ID);
            preparedStatement.setInt(1, achievementId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                userAchievements.add(mapResultSetToUserAchievement(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userAchievements;
    }

    private UserAchievement mapResultSetToUserAchievement(ResultSet resultSet) throws SQLException {
        return new UserAchievement(
                resultSet.getInt("user_id"),
                resultSet.getInt("achievements_id"),
                resultSet.getTimestamp("earned_at") != null ? 
                        resultSet.getTimestamp("earned_at").toLocalDateTime() : null
        );
    }
}

