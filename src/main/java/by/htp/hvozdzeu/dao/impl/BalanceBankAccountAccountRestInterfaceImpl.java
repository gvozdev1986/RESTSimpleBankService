package by.htp.hvozdzeu.dao.impl;

import by.htp.hvozdzeu.dao.BalanceBankAccountRestInterface;
import by.htp.hvozdzeu.dao.connection.ConnectionPool;
import by.htp.hvozdzeu.dao.exception.DAOException;
import by.htp.hvozdzeu.dao.factory.DAOFactory;
import by.htp.hvozdzeu.model.report.BalanceAccount;
import by.htp.hvozdzeu.model.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static by.htp.hvozdzeu.dao.util.ConstantColumnHeaderPool.*;
import static by.htp.hvozdzeu.dao.util.ResponseBuilder.buildResponse;
import static by.htp.hvozdzeu.util.ConstantPool.APP_SECRET_CODE;

/**
 * The class for processing SQL queries related to a bank account.
 */
public class BalanceBankAccountAccountRestInterfaceImpl implements BalanceBankAccountRestInterface {

    private static final Logger LOGGER = LoggerFactory.getLogger(BalanceBankAccountAccountRestInterfaceImpl.class);
    private static final String SQL_GET_BALANCE_BY_CARD_NUMBER = "SELECT * FROM `bankaccount` WHERE `bankaccount`.`CardNumber` = ?;";
    private static final String SQL_UPDATE_BALANCE = "UPDATE `bankservice`.`bankaccount` SET `BalanceBankAccount`= ? WHERE `CardNumber`= ? AND `CVCode` = ?;";
    private static final String SQL_CHECK_CV_CODE = "SELECT * FROM bankaccount WHERE bankaccount.CardNumber = ? AND bankaccount.CVCode = ?;";
    private static final String SQL_TRANSFER_MONEY = "UPDATE `bankservice`.`bankaccount` SET `BalanceBankAccount` = CASE " +
            "WHEN `bankaccount`.`CardNumber` = @cardFrom THEN `bankaccount`.`BalanceBankAccount` - @amount " +
            "WHEN `bankaccount`.`CardNumber` = @cardTo THEN `bankaccount`.`BalanceBankAccount` + @amount " +
            "END WHERE `bankaccount`.`CardNumber` IN (@cardFrom, @cardTo);";
    private static final String ERROR_SQL_CHECK_CV_CODE = "Error checking cv-Code.";
    private static final String ERROR_SQL_GET_BALANCE_BY_CARD_NUMBER = "Error getting balance by card number.";
    private static final String ERROR_SQL_UPDATE_BALANCE = "Error write-off balance from credit card.";
    private static final String MIN_CHECK_AMOUNT = "1.00";
    private static final String MSG_STATUS_RESPONSE_CANCEL_TOKEN = "Token hasn't found or canceled.";
    private static final String MSG_STATUS_RESPONSE_SUCCESSFUL_TRANSACTION = "Operation was successful.";
    private static final String MSG_STATUS_RESPONSE_NOT_ENOUGH_MONEY = "To conduct a transaction is impossible, not enough money.";
    /**
     * Instance ConnectionPool for connect with DB in DAO implements
     */
    private ConnectionPool dataBaseConnection = ConnectionPool.getInstance();
    private AuthRestInterfaceImpl authDAO = DAOFactory.getAuthDAOImpl();


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
    public Response writeOffBalanceBankAccount(String tokenRest,
                                               String cardNumber,
                                               BigDecimal amount,
                                               String cvCode,
                                               String appSecretCode) throws DAOException, SQLException {
        boolean status = false;
        String message;

        if(!authDAO.findToken(tokenRest)){
            return buildResponse(false, MSG_STATUS_RESPONSE_CANCEL_TOKEN);
        }

        if (checkCVCode(cardNumber, cvCode) && appSecretCode.equals(APP_SECRET_CODE)) {
            BalanceAccount balanceAccount = balanceBankAccount(tokenRest, cardNumber, appSecretCode);
            BigDecimal currentBalance = balanceAccount.getBalanceBankAccount();
            if (currentBalance.intValue() > amount.intValue()) {
                BigDecimal newAmount = currentBalance.subtract(amount);
                Connection connection = dataBaseConnection.getConnection();
                try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_BALANCE)) {
                    connection.setAutoCommit(false);
                    preparedStatement.setBigDecimal(1, newAmount);
                    preparedStatement.setString(2, cardNumber);
                    preparedStatement.setString(3, cvCode);
                    preparedStatement.executeUpdate();
                    connection.commit();
                    status = true;
                    message = MSG_STATUS_RESPONSE_SUCCESSFUL_TRANSACTION;
                    LOGGER.debug("Amount {} was wrote-off", amount);
                } catch (SQLException e) {
                    LOGGER.debug("Amount {} wasn't wrote-off duo to create rollback.", amount);
                    connection.rollback();
                    throw new DAOException(ERROR_SQL_UPDATE_BALANCE);
                } finally {
                    dataBaseConnection.closeConnection(connection);
                }
            } else {
                message = MSG_STATUS_RESPONSE_NOT_ENOUGH_MONEY;
                LOGGER.debug("Transaction has been canceled duo to not enough money.");
            }

        } else {
            message = MSG_STATUS_RESPONSE_CANCEL_TOKEN;
            LOGGER.debug("Transaction has been canceled duo to not auth.");
        }
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
    public Response refillBalanceBankAccount(String tokenRest,
                                             String cardNumber,
                                             BigDecimal amount,
                                             String cvCode,
                                             String appSecretCode) throws DAOException, SQLException {
        boolean status = false;
        String message;

        if(!authDAO.findToken(tokenRest)){
            return buildResponse(false, MSG_STATUS_RESPONSE_CANCEL_TOKEN);
        }

        if (appSecretCode.equals(APP_SECRET_CODE)) {
            BalanceAccount balanceAccount = balanceBankAccount(tokenRest, cardNumber, appSecretCode);
            BigDecimal currentBalance = balanceAccount.getBalanceBankAccount();
            BigDecimal newAmount = currentBalance.add(amount);
            Connection connection = dataBaseConnection.getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_BALANCE)) {
                connection.setAutoCommit(false);
                preparedStatement.setBigDecimal(1, newAmount);
                preparedStatement.setString(2, cardNumber);
                preparedStatement.setString(3, cvCode);
                preparedStatement.executeUpdate();
                connection.commit();
                status = true;
                message = MSG_STATUS_RESPONSE_SUCCESSFUL_TRANSACTION;
            } catch (SQLException e) {
                connection.rollback();
                throw new DAOException(ERROR_SQL_UPDATE_BALANCE);
            } finally {
                dataBaseConnection.closeConnection(connection);
            }
        } else {
            message = MSG_STATUS_RESPONSE_CANCEL_TOKEN;
        }
        return buildResponse(status, message);
    }


    /**
     * The method for transfer money from between credit cards (bank accounts)
     *
     * @param tokenRest      String current tokenRest
     * @param cardNumberFrom String credit card number which will be write-off money
     * @param cardNumberTo   String credit card number which will be refill (back) money
     * @param amount         BigDecimal amount for write-off or refill (back)
     * @param cvCode         String code for verification credit card
     * @return build answer in JSON format about result transaction
     */
    @Override
    public Response transferBalanceCardBankAccount(String tokenRest,
                                                   String cardNumberFrom,
                                                   String cardNumberTo,
                                                   BigDecimal amount,
                                                   String cvCode,
                                                   String appSecretCode) throws DAOException, SQLException {

        boolean status = false;
        String message;

        if(!authDAO.findToken(tokenRest)){
            return buildResponse(false, MSG_STATUS_RESPONSE_CANCEL_TOKEN);
        }

        if (checkCVCode(cardNumberFrom, cvCode) && appSecretCode.equals(APP_SECRET_CODE)) {
            BalanceAccount balanceAccount = balanceBankAccount(tokenRest, cardNumberFrom, appSecretCode);
            BigDecimal currentBalance = balanceAccount.getBalanceBankAccount();
            if (currentBalance.intValue() > amount.intValue()) {
                Connection connection = dataBaseConnection.getConnection();
                try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_TRANSFER_MONEY)) {
                    connection.setAutoCommit(false);
                    preparedStatement.addBatch("SET @amount = " + amount + ";"); //NOSONAR
                    preparedStatement.addBatch("SET @cardFrom = '" + cardNumberFrom + "';"); //NOSONAR
                    preparedStatement.addBatch("SET @cardTo = '" + cardNumberTo + "';"); //NOSONAR
                    preparedStatement.executeBatch();
                    preparedStatement.executeUpdate();
                    connection.commit();
                    status = true;
                    message = MSG_STATUS_RESPONSE_SUCCESSFUL_TRANSACTION;
                } catch (SQLException e) {
                    connection.rollback();
                    throw new DAOException(e.getMessage());
                } finally {
                    dataBaseConnection.closeConnection(connection);
                }
            } else {
                message = MSG_STATUS_RESPONSE_NOT_ENOUGH_MONEY;
                LOGGER.debug("Transaction has been canceled duo to not enough money.");
            }
        } else {
            message = MSG_STATUS_RESPONSE_CANCEL_TOKEN;
            LOGGER.debug("Transaction has been canceled duo to not auth.");
        }
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
    public Response checkNewCreditCard(String tokenRest,
                                       String cardNumber,
                                       String cvCode,
                                       String appSecretCode) throws DAOException {
        boolean status = false;
        String message;
        LOGGER.info("Start checking credit card.");

        if(!authDAO.findToken(tokenRest)){
            return buildResponse(false, MSG_STATUS_RESPONSE_CANCEL_TOKEN);
        }

        if (checkCVCode(cardNumber, cvCode) && appSecretCode.equals(APP_SECRET_CODE)) {
            try {
                LOGGER.debug("Write-off amount {} from bank account for checking credit card", MIN_CHECK_AMOUNT);
                writeOffBalanceBankAccount(tokenRest, cardNumber, BigDecimal.valueOf(1.00), cvCode, appSecretCode);
                LOGGER.debug("Refill amount {} to bank account for checking credit card", MIN_CHECK_AMOUNT);
                refillBalanceBankAccount(tokenRest, cardNumber, BigDecimal.valueOf(1.00), cvCode, appSecretCode);
                status = true;
                message = "Credit card has been successful checked.";
                LOGGER.info("Checked was successful.");
            } catch (Exception e) {
                status = false;
                message = "Credit card hasn't been checked.";
            }
        } else {
            message = MSG_STATUS_RESPONSE_CANCEL_TOKEN;
        }
        LOGGER.info("Send response.");
        return buildResponse(status, message);
    }


    /**
     * The method for check vcCode for verification credit card
     *
     * @param creditCardNumber String credit card number
     * @param cvCode           String cv-code credit card
     * @return Boolean Status check (true/false)
     * @throws DAOException Exception
     */
    private boolean checkCVCode(String creditCardNumber, String cvCode) throws DAOException {
        boolean result = false;
        Connection connection = dataBaseConnection.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_CHECK_CV_CODE)) {
            preparedStatement.setString(1, creditCardNumber);
            preparedStatement.setString(2, cvCode);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    if (resultSet.getRow() == 1) { //NOSONAR
                        result = true;
                    }
                }
            }
        } catch (SQLException e) {
            throw new DAOException(ERROR_SQL_CHECK_CV_CODE);
        } finally {
            dataBaseConnection.closeConnection(connection);
        }
        return result;
    }

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
        if (appSecretCode.equals(APP_SECRET_CODE)) {
            Connection connection = dataBaseConnection.getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_BALANCE_BY_CARD_NUMBER)) {
                preparedStatement.setString(1, cardNumber);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        balanceAccount = BalanceAccount.getBuilder()
                                .id(resultSet.getLong(BALANCE_ACCOUNT_ID))
                                .cardNumber(resultSet.getString(BALANCE_ACCOUNT_CARD_NUMBER))
                                .balanceBankAccount(resultSet.getBigDecimal(BALANCE_ACCOUNT_BALANCE))
                                .build();
                    }
                }
            } catch (SQLException e) {
                throw new DAOException(ERROR_SQL_GET_BALANCE_BY_CARD_NUMBER);
            } finally {
                dataBaseConnection.closeConnection(connection);
            }
        }
        return balanceAccount;
    }


}
