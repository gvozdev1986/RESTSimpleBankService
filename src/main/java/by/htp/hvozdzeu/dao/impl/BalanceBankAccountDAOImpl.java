package by.htp.hvozdzeu.dao.impl;

import by.htp.hvozdzeu.dao.BalanceBankAccountDAO;
import by.htp.hvozdzeu.dao.InstanceDAO;
import by.htp.hvozdzeu.dao.exception.DAOException;
import by.htp.hvozdzeu.dao.factory.DAOFactory;
import by.htp.hvozdzeu.dao.mapper.BalanceAccountRowMapper;
import by.htp.hvozdzeu.model.report.BalanceAccount;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BalanceBankAccountDAOImpl extends BalanceAccountRowMapper implements BalanceBankAccountDAO, InstanceDAO {

    private static final String SQL_GET_BALANCE_BY_CARD_NUMBER = "SELECT * FROM `bankaccount` WHERE `bankaccount`.`CardNumber` = ?;";
    private static final String SQL_WRITE_OFF_BALANCE = "UPDATE `bankservice`.`bankaccount` SET `BalanceBankAccount`= ? WHERE  `CardNumber`= ?;";
    private static final String ERROR_SQL_GET_BALANCE_BY_CARD_NUMBER = "Error getting balance by card number.";
    private static final String ERROR_SQL_WRITE_OFF_BALANCE = "Error write-off balance from credit card.";
    private AuthDAOImpl authDAO = DAOFactory.getAuthDAOImpl();
    private BalanceBankAccountDAOImpl balanceBankAccountDAO = DAOFactory.getBalanceBankAccountDAOImpl();

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
        return balanceAccount;
    }

    @Override
    public boolean writeOffBalanceBankAccount(String tokenRest, String cardNumber, BigDecimal amount) throws DAOException {
        boolean result = false;
        if (authDAO.findToken(tokenRest)) {
            BalanceAccount balanceAccount = balanceBankAccount(tokenRest, cardNumber);
            BigDecimal currentBalance = balanceAccount.getBalanceBankAccount();
            if (currentBalance.intValue() > amount.intValue()) {
                BigDecimal newAmount = currentBalance.subtract(amount);
                Connection connection = dataBaseConnection.getConnection();
                try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_WRITE_OFF_BALANCE)) {
                    preparedStatement.setBigDecimal(1, newAmount);
                    preparedStatement.setString(2, cardNumber);
                    preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    throw new DAOException(ERROR_SQL_WRITE_OFF_BALANCE);
                } finally {
                    dataBaseConnection.closeConnection(connection);
                }
                result = true;
            } else {
                result = false;
            }
        }
        return result;
    }


}
