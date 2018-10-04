package by.htp.hvozdzeu.dao.impl;

import by.htp.hvozdzeu.dao.AccountRestInterface;
import by.htp.hvozdzeu.dao.connection.ConnectionPool;
import by.htp.hvozdzeu.dao.exception.DAOException;
import by.htp.hvozdzeu.model.CreditCard;
import by.htp.hvozdzeu.model.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import static by.htp.hvozdzeu.dao.util.ConstantColumnHeaderPool.*;
import static by.htp.hvozdzeu.dao.util.CreateToken.saveToken;
import static by.htp.hvozdzeu.dao.util.ResponseBuilder.buildResponse;
import static by.htp.hvozdzeu.util.ConstantPool.APP_SECRET_CODE;
import static by.htp.hvozdzeu.util.CustomEqualsWithNull.customEquals;

/**
 * The class for getting current tokenRest
 */
public class AuthRestInterfaceImpl implements AccountRestInterface {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthRestInterfaceImpl.class);
    private static final String CHECK_CREDIT_CARD = "SELECT * FROM `bankservice`.`bankaccount` WHERE " +
            "`CardNumber` = ? " +
            "AND `CVCode` = ? " +
            "AND `FirstName` = ? " +
            "AND `LastName` = ? " +
            "AND `MonthValid` = ? " +
            "AND `YearValid` = ?;";
    private static final String FIND_TOKEN = "SELECT tokens.Token, tokens.Date, tokens.Time FROM tokens WHERE tokens.Token = ?;";
    private static final String ERROR_SQL_CHECK_CREDIT_CARD = "Error check credit card.";
    private static final String ERROR_FIND_TOKEN = "Error find token.";
    private static final String NOT_CREATE_TOKEN = "Not found account";
    private static final String ERROR_CREATE_TOKEN = "Error create token duo to not correct data.";
    /**
     * Instance ConnectionPool for connect with DB in DAO implements
     */
    private ConnectionPool dataBaseConnection = ConnectionPool.getInstance();

    /**
     * The method for getting tokenRest by parameters and
     * save him to DB.
     *
     * @param cardNumber    String card number
     * @param cvCode        String code for verification credit card
     * @param firstName     String first name owner credit card
     * @param lastName      String last name owner credit card
     * @param monthValid    String month valid credit card in format (xx)
     * @param yearValid     String year valid credit card in format (xx)
     * @param appSecretCode String special code for verify then request went from current client
     * @return String token for next query (request)
     * @throws DAOException Exception
     */
    @Override
    public Response getToken(String cardNumber,
                             String cvCode,
                             String firstName,
                             String lastName,
                             String monthValid,
                             String yearValid,
                             String appSecretCode) throws DAOException {
        String token;
        boolean status = false;
        CreditCard creditCard = null;
        if (customEquals(appSecretCode, APP_SECRET_CODE)) {
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
                        creditCard = CreditCard.getBuilder()
                                .id(resultSet.getLong(BALANCE_ACCOUNT_ID))
                                .creditCardNumber(resultSet.getString(BALANCE_ACCOUNT_CARD_NUMBER))
                                .cvCode(resultSet.getString(BALANCE_ACCOUNT_CV_CODE))
                                .creditCardBalance(resultSet.getBigDecimal(BALANCE_ACCOUNT_BALANCE))
                                .firstName(resultSet.getString(BALANCE_ACCOUNT_FIRST_NAME))
                                .lastName(resultSet.getString(BALANCE_ACCOUNT_LAST_NAME))
                                .monthValid(resultSet.getString(BALANCE_ACCOUNT_MONTH_VALID))
                                .yearValid(resultSet.getString(BALANCE_ACCOUNT_YEAR_VALID))
                                .build();
                    }
                }
            } catch (SQLException e) {
                throw new DAOException(ERROR_SQL_CHECK_CREDIT_CARD);
            } finally {
                dataBaseConnection.closeConnection(connection);
            }
            if (creditCard != null) {
                token = UUID.randomUUID().toString();
                status = true;
                saveToken(token);
                LOGGER.info("Generate token {}", token);
            } else {
                token = NOT_CREATE_TOKEN;
                LOGGER.info("Error generate token. {}", NOT_CREATE_TOKEN);
            }
        } else {
            token = ERROR_CREATE_TOKEN;
        }
        return buildResponse(status, token);
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
        boolean result = false;
        Connection connection = dataBaseConnection.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_TOKEN)) {
            preparedStatement.setString(1, restToken);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    String token = resultSet.getString("Token");
                    if (!token.isEmpty()) { //NOSONAR
                        result = true;
                    }
                }
            }
        } catch (SQLException e) {
            throw new DAOException(ERROR_FIND_TOKEN);
        } finally {
            dataBaseConnection.closeConnection(connection);
        }
        return result;
    }

    /**
     * The method for check status of server
     *
     * @param appSecretCode String special code for verify then request went from current client
     * @return String token for next query (request)
     */
    @Override
    public Response getStatusServer(String appSecretCode) {
        if(APP_SECRET_CODE.equals(appSecretCode)){
            return buildResponse(true, "online");
        } else {
            return buildResponse(false, "offline");
        }
    }

}
