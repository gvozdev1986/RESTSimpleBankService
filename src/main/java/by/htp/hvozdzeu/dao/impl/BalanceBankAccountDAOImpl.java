package by.htp.hvozdzeu.dao.impl;

import by.htp.hvozdzeu.dao.BalanceBankAccountDAO;
import by.htp.hvozdzeu.dao.InstanceDAO;
import by.htp.hvozdzeu.dao.exception.DAOException;
import by.htp.hvozdzeu.dao.mapper.BalanceAccountRowMapper;
import by.htp.hvozdzeu.model.report.BalanceAccount;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BalanceBankAccountDAOImpl extends BalanceAccountRowMapper implements BalanceBankAccountDAO, InstanceDAO {

    private static final String SQL_GET_BALANCE_BY_CARD_NUMBER = "SELECT * FROM `bankaccount` WHERE `bankaccount`.`CardNumber` = ?;";

    private static final String ERROR_SQL_GET_BALANCE_BY_CARD_NUMBER = "Error getting balance by card namber.";

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

}
