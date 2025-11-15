package com.studbet.dao.impl;

import com.studbet.dao.BettingEventDao;
import com.studbet.model.BettingEvent;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class BettingEventDaoImpl implements BettingEventDao {

    private final DataSource dataSource;

    private final String SQL_SAVE = "insert into betting_events (target_user_id, subject_id, semester, academic_year, event_type, status) values (?, ?, ?, ?, ?, ?) " +
            "RETURNING event_id, target_user_id, subject_id, semester, academic_year, event_type, status, created_at, close_it";
    private final String SQL_UPDATE = "update betting_events set target_user_id = ?, subject_id = ?, semester = ?, academic_year = ?, event_type = ?, status = ?, close_it = ? where event_id = ?";
    private final String SQL_DELETE = "delete from betting_events where event_id = ?";
    private final String SQL_FIND_BY_ID = "select * from betting_events where event_id = ?";
    private final String SQL_FIND_ALL = "select * from betting_events";
    private final String SQL_FIND_BY_TARGET_USER_ID = "select * from betting_events where target_user_id = ?";
    private final String SQL_FIND_BY_SUBJECT_ID = "select * from betting_events where subject_id = ?";
    private final String SQL_FIND_BY_STATUS = "select * from betting_events where status = ?";

    public BettingEventDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public BettingEvent save(BettingEvent event) {
        try(Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SAVE);
            preparedStatement.setInt(1, event.getTargetUserId());
            preparedStatement.setInt(2, event.getSubjectId());
            preparedStatement.setInt(3, event.getSemestr());
            preparedStatement.setString(4, event.getAcademicYear());
            preparedStatement.setString(5, event.getEventType());
            preparedStatement.setString(6, event.getStatus());
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToBettingEvent(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public BettingEvent update(BettingEvent event) {
        try(Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE);
            preparedStatement.setInt(1, event.getTargetUserId());
            preparedStatement.setInt(2, event.getSubjectId());
            preparedStatement.setInt(3, event.getSemestr());
            preparedStatement.setString(4, event.getAcademicYear());
            preparedStatement.setString(5, event.getEventType());
            preparedStatement.setString(6, event.getStatus());
            preparedStatement.setTimestamp(7, event.getClosedIt() != null ? Timestamp.valueOf(event.getClosedIt()) : null);
            preparedStatement.setInt(8, event.getId());
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                return findById(event.getId());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public int delete(BettingEvent event) {
        try(Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE);
            preparedStatement.setInt(1, event.getId());
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                return event.getId();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    @Override
    public BettingEvent findById(int id) {
        try(Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return mapResultSetToBettingEvent(resultSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public List<BettingEvent> getAll() {
        List<BettingEvent> events = new ArrayList<>();
        try(Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_ALL);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                events.add(mapResultSetToBettingEvent(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return events;
    }

    @Override
    public List<BettingEvent> findByTargetUserId(int userId) {
        List<BettingEvent> events = new ArrayList<>();
        try(Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_TARGET_USER_ID);
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                events.add(mapResultSetToBettingEvent(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return events;
    }

    @Override
    public List<BettingEvent> findBySubjectId(int subjectId) {
        List<BettingEvent> events = new ArrayList<>();
        try(Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_SUBJECT_ID);
            preparedStatement.setInt(1, subjectId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                events.add(mapResultSetToBettingEvent(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return events;
    }

    @Override
    public List<BettingEvent> findByStatus(String status) {
        List<BettingEvent> events = new ArrayList<>();
        try(Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_STATUS);
            preparedStatement.setString(1, status);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                events.add(mapResultSetToBettingEvent(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return events;
    }

    private BettingEvent mapResultSetToBettingEvent(ResultSet resultSet) throws SQLException {
        return new BettingEvent(
                resultSet.getInt("event_id"),
                resultSet.getInt("target_user_id"),
                resultSet.getInt("subject_id"),
                resultSet.getInt("semester"),
                resultSet.getString("academic_year"),
                resultSet.getString("event_type"),
                resultSet.getString("status"),
                resultSet.getTimestamp("created_at") != null ? resultSet.getTimestamp("created_at").toLocalDateTime() : null,
                resultSet.getTimestamp("close_it") != null ? resultSet.getTimestamp("close_it").toLocalDateTime() : null
        );
    }
}

