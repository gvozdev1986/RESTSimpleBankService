package by.htp.hvozdzeu.dao;

import by.htp.hvozdzeu.dao.exception.DAOException;
import by.htp.hvozdzeu.model.report.BalanceAccount;
import javax.ws.rs.core.Response;

import java.math.BigDecimal;
import java.sql.SQLException;

/**
 * The interface for BalanceBankAccountAccountRestInterfaceImpl
 */
public interface BalanceBankAccountRestInterface {

    /**
     * The method for getting balance bank account
     *
     * @param cardNumber    String credit card number
     * @param appSecretCode String special code for verify then request went from current client
     * @return BalanceAccount
     * @throws DAOException Exception
     */
    BalanceAccount balanceBankAccount(String cardNumber, String appSecretCode) throws DAOException;


    /**
     * The method for write-off money from bank account
     *
     * @param cardNumber    String credit card number
     * @param amount        BigDecimal amount for write-off
     * @param cvCode        String code for verification credit card
     * @param appSecretCode String special code for verify then request went from current client
     * @return ResponseEntity build answer in JSON format
     * @throws DAOException Exception
     * @throws SQLException Exception
     */
    Response writeOffBalanceBankAccount(String cardNumber,
                                        BigDecimal amount,
                                        String cvCode,
                                        String appSecretCode) throws DAOException, SQLException;

    /**
     * The method for refill (back) money to bank account
     *
     * @param cardNumber    String credit card number
     * @param amount        BigDecimal amount for write-off
     * @param cvCode        String code for verification credit card
     * @param appSecretCode String special code for verify then request went from current client
     * @return ResponseEntity build answer in XML format
     * @throws DAOException Exception
     * @throws SQLException Exception
     */
    Response refillBalanceBankAccount(String cardNumber,
                                      BigDecimal amount,
                                      String cvCode,
                                      String appSecretCode) throws DAOException, SQLException;


    /**
     * The method for transfer money from between credit cards (bank accounts)
     *
     * @param cardNumberFrom String credit card number which will be write-off money
     * @param cardNumberTo   String credit card number which will be refill (back) money
     * @param amount         BigDecimal amount for write-off or refill (back)
     * @param cvCode         String code for verification credit card
     * @return build answer in JSON format about result transaction
     */
    Response transferBalanceCardBankAccount(String cardNumberFrom,
                                            String cardNumberTo,
                                            BigDecimal amount,
                                            String cvCode,
                                            String appSecretCode) throws DAOException, SQLException;

    /**
     * The method to check credit card validity
     *
     * @param cardNumber    String credit card number
     * @param cvCode        String code for verification credit card
     * @param appSecretCode String special code for verify then request went from current client
     * @return ResponseEntity build answer in JSON format
     * @throws DAOException Exception
     */
    Response checkNewCreditCard(String cardNumber, String cvCode, String appSecretCode) throws DAOException, SQLException;

}
