package com.studbet.dao.impl;

import com.studbet.dao.BetDao;
import com.studbet.model.Bet;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class BetDaoImpl implements BetDao {

    private final DataSource dataSource;

    private final String SQL_SAVE = "insert into bets (bettor_id, event_id, bet_amount, status, payout_amount, predicted_score_min, predicted_score_max) values (?, ?, ?, ?, ?, ?, ?) " +
            "RETURNING bet_id, bettor_id, event_id, bet_amount, status, payout_amount, created_at, resolved_at, predicted_score_min, predicted_score_max";
    private final String SQL_UPDATE = "update bets set bettor_id = ?, event_id = ?, bet_amount = ?, status = ?, payout_amount = ?, resolved_at = ?, predicted_score_min = ?, predicted_score_max = ? where bet_id = ?";
    private final String SQL_DELETE = "delete from bets where bet_id = ?";
    private final String SQL_FIND_BY_ID = "select * from bets where bet_id = ?";
    private final String SQL_FIND_ALL = "select * from bets";
    private final String SQL_FIND_BY_BETTOR_ID = "select * from bets where bettor_id = ?";
    private final String SQL_FIND_BY_EVENT_ID = "select * from bets where event_id = ?";
    private final String SQL_FIND_BY_STATUS = "select * from bets where status = ?";

    public BetDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Bet save(Bet bet) {
        try(Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SAVE);
            preparedStatement.setInt(1, bet.getBettorId());
            preparedStatement.setInt(2, bet.getEventId());
            preparedStatement.setInt(3, bet.getBetAmount());
            preparedStatement.setString(4, bet.getStatus());
            preparedStatement.setObject(5, bet.getPayoutAmount() > 0 ? bet.getPayoutAmount() : null);
            preparedStatement.setInt(6, bet.getPredictedScoreMin());
            preparedStatement.setInt(7, bet.getPredictedScoreMax());
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToBet(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Bet update(Bet bet) {
        try(Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE);
            preparedStatement.setInt(1, bet.getBettorId());
            preparedStatement.setInt(2, bet.getEventId());
            preparedStatement.setInt(3, bet.getBetAmount());
            preparedStatement.setString(4, bet.getStatus());
            preparedStatement.setObject(5, bet.getPayoutAmount() > 0 ? bet.getPayoutAmount() : null);
            preparedStatement.setTimestamp(6, bet.getResolvedAt() != null ? Timestamp.valueOf(bet.getResolvedAt()) : null);
            preparedStatement.setInt(7, bet.getPredictedScoreMin());
            preparedStatement.setInt(8, bet.getPredictedScoreMax());
            preparedStatement.setInt(9, bet.getId());
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                return findById(bet.getId());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public int delete(Bet bet) {
        try(Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE);
            preparedStatement.setInt(1, bet.getId());
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                return bet.getId();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    @Override
    public Bet findById(int id) {
        try(Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return mapResultSetToBet(resultSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public List<Bet> getAll() {
        List<Bet> bets = new ArrayList<>();
        try(Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_ALL);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                bets.add(mapResultSetToBet(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return bets;
    }

    @Override
    public List<Bet> findByBettorId(int bettorId) {
        List<Bet> bets = new ArrayList<>();
        try(Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_BETTOR_ID);
            preparedStatement.setInt(1, bettorId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                bets.add(mapResultSetToBet(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return bets;
    }

    @Override
    public List<Bet> findByEventId(int eventId) {
        List<Bet> bets = new ArrayList<>();
        try(Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_EVENT_ID);
            preparedStatement.setInt(1, eventId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                bets.add(mapResultSetToBet(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return bets;
    }

    @Override
    public List<Bet> findByStatus(String status) {
        List<Bet> bets = new ArrayList<>();
        try(Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_STATUS);
            preparedStatement.setString(1, status);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                bets.add(mapResultSetToBet(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return bets;
    }

    private Bet mapResultSetToBet(ResultSet resultSet) throws SQLException {
        int payoutAmount = resultSet.getInt("payout_amount");
        if (resultSet.wasNull()) {
            payoutAmount = 0;
        }
        return new Bet(
                resultSet.getInt("bet_id"),
                resultSet.getInt("bettor_id"),
                resultSet.getInt("event_id"),
                resultSet.getInt("bet_amount"),
                resultSet.getString("status"),
                payoutAmount,
                resultSet.getTimestamp("created_at") != null ? resultSet.getTimestamp("created_at").toLocalDateTime() : null,
                resultSet.getTimestamp("resolved_at") != null ? resultSet.getTimestamp("resolved_at").toLocalDateTime() : null,
                resultSet.getInt("predicted_score_min"),
                resultSet.getInt("predicted_score_max")
        );
    }
}

