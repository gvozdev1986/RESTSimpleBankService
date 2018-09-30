package by.htp.hvozdzeu.dao.impl;

import by.htp.hvozdzeu.dao.BalanceBankAccountDAO;
import by.htp.hvozdzeu.dao.InstanceDAO;
import by.htp.hvozdzeu.dao.exception.DAOException;
import by.htp.hvozdzeu.dao.factory.DAOFactory;
import by.htp.hvozdzeu.dao.mapper.BalanceAccountRowMapper;
import by.htp.hvozdzeu.model.report.BalanceAccount;
import by.htp.hvozdzeu.model.response.Response;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static by.htp.hvozdzeu.dao.util.DeleteToken.deleteToken;
import static by.htp.hvozdzeu.dao.util.ResponseBuilder.buildResponse;

public class BalanceBankAccountDAOImpl extends BalanceAccountRowMapper implements BalanceBankAccountDAO, InstanceDAO {

    private static final String SQL_GET_BALANCE_BY_CARD_NUMBER = "SELECT * FROM `bankaccount` WHERE `bankaccount`.`CardNumber` = ?;";
    private static final String SQL_WRITE_OFF_BALANCE = "UPDATE `bankservice`.`bankaccount` SET `BalanceBankAccount`= ? WHERE  `CardNumber`= ?;";
    private static final String ERROR_SQL_GET_BALANCE_BY_CARD_NUMBER = "Error getting balance by card number.";
    private static final String ERROR_SQL_WRITE_OFF_BALANCE = "Error write-off balance from credit card.";

    private static final String MSG_STATUS_RESPONSE_CANCEL_TOKEN = "Token hasn't found or canceled.";
    private static final String MSG_STATUS_RESPONSE_SUCCESSFUL_TRANSACTION = "Operation was successful.";
    private static final String MSG_STATUS_RESPONSE_UNSUCCESSFUL_TRANSACTION = "Operation wasn't successful.";
    private static final String MSG_STATUS_RESPONSE_NOT_ENOUGH_MONEY = "To conduct a transaction is impossible, not enough money.";

    private AuthDAOImpl authDAO = DAOFactory.getAuthDAOImpl();

    @Override
    public BalanceAccount balanceBankAccount(String tokenRest, String cardNumber) throws DAOException {
        BalanceAccount balanceAccount = null;
        Connection connection = dataBaseConnection.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_BALANCE_BY_CARD_NUMBER)) {
            preparedStatement.setString(1, cardNumber);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    balanceAccount = buildBalanceAccountRowMapper(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new DAOException(ERROR_SQL_GET_BALANCE_BY_CARD_NUMBER);
        } finally {
            dataBaseConnection.closeConnection(connection);
        }
        //deleteToken(tokenRest);
        return balanceAccount;
    }

    @Override
    public Response writeOffBalanceBankAccount(String tokenRest, String cardNumber, BigDecimal amount) throws DAOException, SQLException {
        boolean status = false;
        String message = null;
        if (authDAO.findToken(tokenRest)) {
            BalanceAccount balanceAccount = balanceBankAccount(tokenRest, cardNumber);
            BigDecimal currentBalance = balanceAccount.getBalanceBankAccount();
            if (currentBalance.intValue() > amount.intValue()) {
                BigDecimal newAmount = currentBalance.subtract(amount);
                Connection connection = dataBaseConnection.getConnection();
                try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_WRITE_OFF_BALANCE)) {
                    connection.setAutoCommit(false);
                    preparedStatement.setBigDecimal(1, newAmount);
                    preparedStatement.setString(2, cardNumber);
                    preparedStatement.executeUpdate();
                    connection.commit();
                    status = true;
                    message = MSG_STATUS_RESPONSE_SUCCESSFUL_TRANSACTION;
                } catch (SQLException e) {
                    message = MSG_STATUS_RESPONSE_UNSUCCESSFUL_TRANSACTION;
                    connection.rollback();
                    throw new DAOException(ERROR_SQL_WRITE_OFF_BALANCE);
                } finally {
                    dataBaseConnection.closeConnection(connection);
                }
            } else {
                message = MSG_STATUS_RESPONSE_NOT_ENOUGH_MONEY;
            }
        } else {
            message = MSG_STATUS_RESPONSE_CANCEL_TOKEN;
        }
        //deleteToken(tokenRest);
        return buildResponse(status, message);
    }

    @Override
    public Response refillBalanceBankAccount(String tokenRest, String cardNumber, BigDecimal amount) throws DAOException, SQLException {
        boolean status = false;
        String message = null;
        if (authDAO.findToken(tokenRest)) {
            BalanceAccount balanceAccount = balanceBankAccount(tokenRest, cardNumber);
            BigDecimal currentBalance = balanceAccount.getBalanceBankAccount();
                BigDecimal newAmount = currentBalance.add(amount);
                Connection connection = dataBaseConnection.getConnection();
                try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_WRITE_OFF_BALANCE)) {
                    connection.setAutoCommit(false);
                    preparedStatement.setBigDecimal(1, newAmount);
                    preparedStatement.setString(2, cardNumber);
                    preparedStatement.executeUpdate();
                    connection.commit();
                    status = true;
                    message = MSG_STATUS_RESPONSE_SUCCESSFUL_TRANSACTION;
                } catch (SQLException e) {
                    message = MSG_STATUS_RESPONSE_UNSUCCESSFUL_TRANSACTION;
                    connection.rollback();
                    throw new DAOException(ERROR_SQL_WRITE_OFF_BALANCE);
                } finally {
                    dataBaseConnection.closeConnection(connection);
                }
        } else {
            message = MSG_STATUS_RESPONSE_CANCEL_TOKEN;
        }
        //deleteToken(tokenRest);
        return buildResponse(status, message);
    }


}
