package by.htp.hvozdzeu.dao;

import by.htp.hvozdzeu.dao.exception.DAOException;
import by.htp.hvozdzeu.model.report.BalanceAccount;
import by.htp.hvozdzeu.model.response.Response;

import java.math.BigDecimal;
import java.sql.SQLException;

/**
 * The interface for BalanceBankAccountDAOImpl
 */
public interface BalanceBankAccountDAO {

    /**
     * The method for getting balance bank account
     *
     * @param tokenRest  String current tokenRest
     * @param cardNumber String credit card number
     * @return BalanceAccount
     * @throws DAOException Exception
     */
    BalanceAccount balanceBankAccount(String tokenRest, String cardNumber) throws DAOException;

    /**
     * The method for write-off money from bank account
     *
     * @param tokenRest  String current tokenRest
     * @param cardNumber String credit card number
     * @param amount     BigDecimal amount for write-off
     * @param cvCode     String code for verification credit card
     * @return Response build answer in XML format
     * @throws DAOException Exception
     * @throws SQLException Exception
     */
    Response writeOffBalanceBankAccount(String tokenRest, String cardNumber, BigDecimal amount, String cvCode) throws DAOException, SQLException;

    /**
     * The method for refill (back) money to bank account
     *
     * @param tokenRest  String current tokenRest
     * @param cardNumber String credit card number
     * @param amount     BigDecimal amount for write-off
     * @param cvCode     String code for verification credit card
     * @return Response build answer in XML format
     * @throws DAOException Exception
     * @throws SQLException Exception
     */
    Response refillBalanceBankAccount(String tokenRest, String cardNumber, BigDecimal amount, String cvCode) throws DAOException, SQLException;

    /**
     * The method to check credit card validity
     *
     * @param tokenRest  String current tokenRest
     * @param cardNumber String credit card number
     * @param cvCode     String code for verification credit card
     * @return Response build answer in XML format
     * @throws DAOException Exception
     */
    Response checkCreditCardBankAccount(String tokenRest, String cardNumber, String cvCode) throws DAOException;

}
