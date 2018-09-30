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
import static by.htp.hvozdzeu.util.Decoder.decrypt;
import static by.htp.hvozdzeu.util.DecoderProperties.getSecretKey;

/**
 * The class for getting current tokenRest
 */
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

    /**
     * The method for getting tokenRest by parameters and
     * save him to DB.
     *
     * @param cardNumber String card number
     * @param cvCode     String code for verification credit card
     * @param firstName  String first name owner credit card
     * @param lastName   String last name owner credit card
     * @param monthValid String month valid credit card in format (xx)
     * @param yearValid  String year valid credit card in format (xx)
     * @return String token for next query (request)
     * @throws DAOException Exception
     */
    @Override
    public String getToken(String cardNumber,
                           String cvCode,
                           String firstName,
                           String lastName,
                           String monthValid,
                           String yearValid) throws DAOException {

        /*
        Get secret key for decrypt data from request
         */
        String secretKey = getSecretKey();

        String token;
        CreditCard creditCard = null;
        Connection connection = dataBaseConnection.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(CHECK_CREDIT_CARD)) {
            preparedStatement.setString(1, decrypt(cardNumber, secretKey));
            preparedStatement.setString(2, decrypt(cvCode, secretKey));
            preparedStatement.setString(3, decrypt(firstName, secretKey));
            preparedStatement.setString(4, decrypt(lastName, secretKey));
            preparedStatement.setString(5, decrypt(monthValid, secretKey));
            preparedStatement.setString(6, decrypt(yearValid, secretKey));
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

        /*
        Generate random tokenRest
         */
        if (creditCard != null) {
            token = UUID.randomUUID().toString();

            /*
            Save tokenRest to DB
             */
            saveToken(token);
        } else {
            token = NOT_CREATE_TOKEN;
        }

        /*
        Return new tokenRest
         */
        return token;
    }

    /**
     * Method for find token for check before do methods
     *
     * @param restToken String tokenRest from request
     * @return Boolean (true/false) result query
     * @throws DAOException Exception
     */
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
