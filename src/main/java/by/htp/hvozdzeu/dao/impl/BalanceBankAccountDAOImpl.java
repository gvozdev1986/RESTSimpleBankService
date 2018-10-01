package by.htp.hvozdzeu.dao.impl;

import by.htp.hvozdzeu.dao.BalanceBankAccountDAO;
import by.htp.hvozdzeu.dao.InstanceDAO;
import by.htp.hvozdzeu.dao.exception.DAOException;
import by.htp.hvozdzeu.dao.factory.DAOFactory;
import by.htp.hvozdzeu.dao.mapper.BalanceAccountRowMapper;
import by.htp.hvozdzeu.model.report.BalanceAccount;
import by.htp.hvozdzeu.model.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static by.htp.hvozdzeu.dao.util.DeleteToken.deleteToken;
import static by.htp.hvozdzeu.dao.util.ResponseBuilder.buildResponse;
import static by.htp.hvozdzeu.util.ConstantRool.APP_SECRET_CODE;
import static by.htp.hvozdzeu.util.Decoder.decrypt;
import static by.htp.hvozdzeu.util.DecoderProperties.getSecretKey;

/**
 * The class for processing SQL queries related to a bank account.
 */
public class BalanceBankAccountDAOImpl extends BalanceAccountRowMapper implements BalanceBankAccountDAO, InstanceDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(BalanceBankAccountDAOImpl.class);

    private static final String SQL_GET_BALANCE_BY_CARD_NUMBER = "SELECT * FROM `bankaccount` WHERE `bankaccount`.`CardNumber` = ?;";
    private static final String SQL_UPDATE_BALANCE = "UPDATE `bankservice`.`bankaccount` SET `BalanceBankAccount`= ? WHERE `CardNumber`= ? AND `CVCode` = ?;";
    private static final String ERROR_SQL_GET_BALANCE_BY_CARD_NUMBER = "Error getting balance by card number.";
    private static final String ERROR_SQL_UPDATE_BALANCE = "Error write-off balance from credit card.";
    private static final String MIN_CHECK_AMOUNT = "1.00";
    private static final String MSG_STATUS_RESPONSE_CANCEL_TOKEN = "Token hasn't found or canceled.";
    private static final String MSG_STATUS_RESPONSE_SUCCESSFUL_TRANSACTION = "Operation was successful.";
    private static final String MSG_STATUS_RESPONSE_UNSUCCESSFUL_TRANSACTION = "Operation wasn't successful.";
    private static final String MSG_STATUS_RESPONSE_NOT_ENOUGH_MONEY = "To conduct a transaction is impossible, not enough money.";

    private AuthDAOImpl authDAO = DAOFactory.getAuthDAOImpl();

    /**
     * The method for getting balance bank account
     *
     * @param tokenRest     String current tokenRest
     * @param cardNumber    String credit card number
     * @param appSecretCode String special code for verify then request went from current client
     * @return BalanceAccount
     * @throws DAOException Exception
     */
    @Override
    public BalanceAccount balanceBankAccount(String tokenRest, String cardNumber, String appSecretCode) throws DAOException {
        BalanceAccount balanceAccount = null;
        String secretKey = getSecretKey();
        if (authDAO.findToken(tokenRest) && appSecretCode.equals(APP_SECRET_CODE)) {
            Connection connection = dataBaseConnection.getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_BALANCE_BY_CARD_NUMBER)) {
                preparedStatement.setString(1, decrypt(cardNumber, secretKey));
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
        }
        deleteToken(tokenRest);
        return balanceAccount;
    }

    /**
     * The method for write-off money from bank account
     *
     * @param tokenRest     String current tokenRest
     * @param cardNumber    String credit card number
     * @param amount        BigDecimal amount for write-off
     * @param cvCode        String code for verification credit card
     * @param appSecretCode String special code for verify then request went from current client
     * @return Response build answer in XML format
     * @throws DAOException Exception
     * @throws SQLException Exception
     */
    @Override
    public Response writeOffBalanceBankAccount(String tokenRest, String cardNumber, BigDecimal amount,
                                               String cvCode, String appSecretCode) throws DAOException, SQLException {
        boolean status = false;
        String message = null;
        String secretKey = getSecretKey();
        if (authDAO.findToken(tokenRest) && appSecretCode.equals(APP_SECRET_CODE)) {
            BalanceAccount balanceAccount = balanceBankAccount(tokenRest, cardNumber, appSecretCode);
            BigDecimal currentBalance = balanceAccount.getBalanceBankAccount();
            if (currentBalance.intValue() > amount.intValue()) {
                BigDecimal newAmount = currentBalance.subtract(amount);
                Connection connection = dataBaseConnection.getConnection();
                try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_BALANCE)) {
                    connection.setAutoCommit(false);
                    preparedStatement.setBigDecimal(1, newAmount);
                    preparedStatement.setString(2, decrypt(cardNumber, secretKey));
                    preparedStatement.setString(3, decrypt(cvCode, secretKey));
                    preparedStatement.executeUpdate();
                    connection.commit();
                    status = true;
                    message = MSG_STATUS_RESPONSE_SUCCESSFUL_TRANSACTION;
                } catch (SQLException e) {
                    message = MSG_STATUS_RESPONSE_UNSUCCESSFUL_TRANSACTION;
                    connection.rollback();
                    throw new DAOException(ERROR_SQL_UPDATE_BALANCE);
                } finally {
                    dataBaseConnection.closeConnection(connection);
                }
            } else {
                message = MSG_STATUS_RESPONSE_NOT_ENOUGH_MONEY;
            }
        } else {
            message = MSG_STATUS_RESPONSE_CANCEL_TOKEN;
        }
        deleteToken(tokenRest);
        return buildResponse(status, message);
    }

    /**
     * The method for refill (back) money to bank account
     *
     * @param tokenRest     String current tokenRest
     * @param cardNumber    String credit card number
     * @param amount        BigDecimal amount for write-off
     * @param cvCode        String code for verification credit card
     * @param appSecretCode String special code for verify then request went from current client
     * @return Response build answer in XML format
     * @throws DAOException Exception
     * @throws SQLException Exception
     */
    @Override
    public Response refillBalanceBankAccount(String tokenRest, String cardNumber, BigDecimal amount,
                                             String cvCode, String appSecretCode) throws DAOException, SQLException {
        boolean status = false;
        String message = null;
        String secretKey = getSecretKey();
        if (authDAO.findToken(tokenRest) && appSecretCode.equals(APP_SECRET_CODE)) {
            BalanceAccount balanceAccount = balanceBankAccount(tokenRest, cardNumber, appSecretCode);
            BigDecimal currentBalance = balanceAccount.getBalanceBankAccount();
            BigDecimal newAmount = currentBalance.add(amount);
            Connection connection = dataBaseConnection.getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_BALANCE)) {
                connection.setAutoCommit(false);
                preparedStatement.setBigDecimal(1, newAmount);
                preparedStatement.setString(2, decrypt(cardNumber, secretKey));
                preparedStatement.setString(3, decrypt(cvCode, secretKey));
                preparedStatement.executeUpdate();
                connection.commit();
                status = true;
                message = MSG_STATUS_RESPONSE_SUCCESSFUL_TRANSACTION;
            } catch (SQLException e) {
                message = MSG_STATUS_RESPONSE_UNSUCCESSFUL_TRANSACTION;
                connection.rollback();
                throw new DAOException(ERROR_SQL_UPDATE_BALANCE);
            } finally {
                dataBaseConnection.closeConnection(connection);
            }
        } else {
            message = MSG_STATUS_RESPONSE_CANCEL_TOKEN;
        }
        deleteToken(tokenRest);
        return buildResponse(status, message);
    }

    /**
     * The method to check credit card validity
     *
     * @param tokenRest     String current tokenRest
     * @param cardNumber    String credit card number
     * @param cvCode        String code for verification credit card
     * @param appSecretCode String special code for verify then request went from current client
     * @return Response build answer in XML format
     * @throws DAOException Exception
     */
    @Override
    public Response checkCreditCardBankAccount(String tokenRest, String cardNumber,
                                               String cvCode, String appSecretCode) throws DAOException {
        boolean status = false;
        String message = null;
        if (authDAO.findToken(tokenRest) && appSecretCode.equals(APP_SECRET_CODE)) {
            try {
                LOGGER.debug("Write-off amount {} from bank account for checking credit card", MIN_CHECK_AMOUNT);
                writeOffBalanceBankAccount(tokenRest, cardNumber, BigDecimal.valueOf(1.00), cardNumber, appSecretCode);
                LOGGER.debug("Refill amount {} to bank account for checking credit card", MIN_CHECK_AMOUNT);
                refillBalanceBankAccount(tokenRest, cardNumber, BigDecimal.valueOf(1.00), cardNumber, appSecretCode);
                status = true;
                message = "Credit card has been successful checked.";
            } catch (Exception e) {
                status = false;
                message = "Credit card hasn't been checked.";
            }
        } else {
            message = MSG_STATUS_RESPONSE_CANCEL_TOKEN;
        }
        deleteToken(tokenRest);
        return buildResponse(status, message);
    }


}
