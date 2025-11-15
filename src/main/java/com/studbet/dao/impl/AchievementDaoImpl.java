package com.studbet.dao.impl;

import com.studbet.dao.AchevementsDao;
import com.studbet.model.Achievement;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AchievementDaoImpl implements AchevementsDao {

    private final DataSource dataSource;

    private final String SQL_SAVE = "insert into achievements (achevements_name, description, icon_url, points_reward, achievement_type, criteria_json) values (?, ?, ?, ?, ?, ?) " +
            "RETURNING achievements_id, achevements_name, description, icon_url, points_reward, achievement_type, criteria_json";
    private final String SQL_UPDATE = "update achievements set achevements_name = ?, description = ?, icon_url = ?, points_reward = ?, achievement_type = ?, criteria_json = ? where achievements_id = ?";
    private final String SQL_DELETE = "delete from achievements where achievements_id = ?";
    private final String SQL_FIND_BY_ID = "select * from achievements where achievements_id = ?";
    private final String SQL_FIND_ALL = "select * from achievements";

    public AchievementDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Achievement save(Achievement achievement) {
        try(Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SAVE);
            preparedStatement.setString(1, achievement.getName());
            preparedStatement.setString(2, achievement.getDescription());
            preparedStatement.setString(3, achievement.getIcon_url());
            preparedStatement.setInt(4, achievement.getPointsReward());
            preparedStatement.setString(5, achievement.getAchievementType());
            preparedStatement.setString(6, achievement.getCriteriaJson());
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToAchievement(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Achievement update(Achievement achievement) {
        try(Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE);
            preparedStatement.setString(1, achievement.getName());
            preparedStatement.setString(2, achievement.getDescription());
            preparedStatement.setString(3, achievement.getIcon_url());
            preparedStatement.setInt(4, achievement.getPointsReward());
            preparedStatement.setString(5, achievement.getAchievementType());
            preparedStatement.setString(6, achievement.getCriteriaJson());
            preparedStatement.setInt(7, achievement.getId());
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                return findById(achievement.getId());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public int delete(Achievement achievement) {
        try(Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE);
            preparedStatement.setInt(1, achievement.getId());
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                return achievement.getId();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    @Override
    public Achievement findById(int id) {
        try(Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return mapResultSetToAchievement(resultSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public List<Achievement> getAll() {
        List<Achievement> achievements = new ArrayList<>();
        try(Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_ALL);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                achievements.add(mapResultSetToAchievement(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return achievements;
    }

    private Achievement mapResultSetToAchievement(ResultSet resultSet) throws SQLException {
        return new Achievement(
                resultSet.getInt("achievements_id"),
                resultSet.getString("achevements_name"),
                resultSet.getString("description"),
                resultSet.getString("icon_url"),
                resultSet.getInt("points_reward"),
                resultSet.getString("achievement_type"),
                resultSet.getString("criteria_json")
        );
    }
}

