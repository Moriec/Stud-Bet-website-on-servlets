package com.studbet.dao.impl;

import com.studbet.dao.TransactionDao;
import com.studbet.model.Transaction;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TransactionDaoImpl implements TransactionDao {

    private final DataSource dataSource;

    private final String SQL_SAVE = "insert into transactions (user_id, transaction_type, amount, balance_before, balance_after, related_bet_id, description) values (?, ?, ?, ?, ?, ?, ?) " +
            "RETURNING transaction_id, user_id, transaction_type, amount, balance_before, balance_after, related_bet_id, description, created_at";
    private final String SQL_UPDATE = "update transactions set user_id = ?, transaction_type = ?, amount = ?, balance_before = ?, balance_after = ?, related_bet_id = ?, description = ? where transaction_id = ?";
    private final String SQL_DELETE = "delete from transactions where transaction_id = ?";
    private final String SQL_FIND_BY_ID = "select * from transactions where transaction_id = ?";
    private final String SQL_FIND_ALL = "select * from transactions";
    private final String SQL_FIND_BY_USER_ID = "select * from transactions where user_id = ?";
    private final String SQL_FIND_BY_RELATED_BET_ID = "select * from transactions where related_bet_id = ?";

    public TransactionDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Transaction save(Transaction transaction) {
        try(Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SAVE);
            preparedStatement.setInt(1, transaction.getUserId());
            preparedStatement.setString(2, transaction.getTransactionType());
            preparedStatement.setInt(3, transaction.getAmount());
            preparedStatement.setInt(4, transaction.getBalanceBefore());
            preparedStatement.setInt(5, transaction.getBalanceAfter());
            preparedStatement.setObject(6, transaction.getRelatedBetId() > 0 ? transaction.getRelatedBetId() : null);
            preparedStatement.setString(7, transaction.getDescription());
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToTransaction(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Transaction update(Transaction transaction) {
        try(Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE);
            preparedStatement.setInt(1, transaction.getUserId());
            preparedStatement.setString(2, transaction.getTransactionType());
            preparedStatement.setInt(3, transaction.getAmount());
            preparedStatement.setInt(4, transaction.getBalanceBefore());
            preparedStatement.setInt(5, transaction.getBalanceAfter());
            preparedStatement.setObject(6, transaction.getRelatedBetId() > 0 ? transaction.getRelatedBetId() : null);
            preparedStatement.setString(7, transaction.getDescription());
            preparedStatement.setInt(8, transaction.getId());
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                return findById(transaction.getId());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public int delete(Transaction transaction) {
        try(Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE);
            preparedStatement.setInt(1, transaction.getId());
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                return transaction.getId();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    @Override
    public Transaction findById(int id) {
        try(Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return mapResultSetToTransaction(resultSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public List<Transaction> getAll() {
        List<Transaction> transactions = new ArrayList<>();
        try(Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_ALL);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                transactions.add(mapResultSetToTransaction(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return transactions;
    }

    @Override
    public List<Transaction> findByUserId(int userId) {
        List<Transaction> transactions = new ArrayList<>();
        try(Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_USER_ID);
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                transactions.add(mapResultSetToTransaction(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return transactions;
    }

    @Override
    public List<Transaction> findByRelatedBetId(int betId) {
        List<Transaction> transactions = new ArrayList<>();
        try(Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_RELATED_BET_ID);
            preparedStatement.setInt(1, betId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                transactions.add(mapResultSetToTransaction(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return transactions;
    }

    private Transaction mapResultSetToTransaction(ResultSet resultSet) throws SQLException {
        int relatedBetId = resultSet.getInt("related_bet_id");
        if (resultSet.wasNull()) {
            relatedBetId = 0;
        }
        return new Transaction(
                resultSet.getInt("transaction_id"),
                resultSet.getInt("user_id"),
                relatedBetId,
                resultSet.getString("transaction_type"),
                resultSet.getInt("amount"),
                resultSet.getInt("balance_before"),
                resultSet.getInt("balance_after"),
                resultSet.getString("description"),
                resultSet.getTimestamp("created_at") != null ? resultSet.getTimestamp("created_at").toLocalDateTime() : null
        );
    }
}

