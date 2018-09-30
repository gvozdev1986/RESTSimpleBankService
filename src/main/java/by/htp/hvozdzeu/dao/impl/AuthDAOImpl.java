package by.htp.hvozdzeu.dao.impl;

import by.htp.hvozdzeu.dao.AccountDAO;
import by.htp.hvozdzeu.dao.InstanceDAO;
import by.htp.hvozdzeu.dao.exception.DAOException;
import by.htp.hvozdzeu.dao.mapper.BalanceAccountRowMapper;
import by.htp.hvozdzeu.model.CreditCard;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import static by.htp.hvozdzeu.dao.util.CreateToken.saveToken;

public class AuthDAOImpl extends BalanceAccountRowMapper implements InstanceDAO, AccountDAO {

    private static final String CHECK_CREDIT_CARD = "SELECT * FROM `bankservice`.`bankaccount` WHERE " +
            "`CardNumber` = ? " +
            "AND `CVCode` = ? " +
            "AND `FirstName` = ? " +
            "AND `LastName` = ? " +
            "AND `MonthValid` = ? " +
            "AND `YearValid` = ?;";
    private static final String FIND_TOKEN = "SELECT * FROM `tokens` WHERE `tokens`.`Token` = ?;";

    private static final String ERROR_SQL_CHECK_CREDIT_CARD = "Error check credit card.";
    private static final String ERROR_FIND_TOKEN = "Error find token.";

    private static final String NOT_CREATE_TOKEN = "not found account";

    @Override
    public String getToken(String cardNumber,
                           String cvCode,
                           String firstName,
                           String lastName,
                           String monthValid,
                           String yearValid) throws DAOException {
        String token;
        CreditCard creditCard = null;
        Connection connection = dataBaseConnection.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(CHECK_CREDIT_CARD)) {
            preparedStatement.setString(1, cardNumber);
            preparedStatement.setString(2, cvCode);
            preparedStatement.setString(3, firstName);
            preparedStatement.setString(4, lastName);
            preparedStatement.setString(5, monthValid);
            preparedStatement.setString(6, yearValid);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    creditCard = buildCreditCardRowMapper(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new DAOException(ERROR_SQL_CHECK_CREDIT_CARD);
        } finally {
            dataBaseConnection.closeConnection(connection);
        }
        if(creditCard != null){
            token = UUID.randomUUID().toString();
            saveToken(token);
        } else {
            token = NOT_CREATE_TOKEN;
        }
        return token;
    }

    @Override
    public boolean findToken(String restToken) throws DAOException {
        boolean result;
        Connection connection = dataBaseConnection.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_TOKEN)) {
            preparedStatement.setString(1, restToken);
            preparedStatement.executeQuery();
            result = true;
        } catch (SQLException e) {
            throw new DAOException(ERROR_FIND_TOKEN);
        } finally {
            dataBaseConnection.closeConnection(connection);
        }
        return result;
    }

}
