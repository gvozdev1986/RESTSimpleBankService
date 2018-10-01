package by.htp.hvozdzeu.dao.mapper;

import by.htp.hvozdzeu.model.CreditCard;
import by.htp.hvozdzeu.model.report.BalanceAccount;
import by.htp.hvozdzeu.util.Decoder;

import java.sql.ResultSet;
import java.sql.SQLException;

import static by.htp.hvozdzeu.util.Decoder.encrypt;
import static by.htp.hvozdzeu.util.DecoderProperties.getSecretKey;

/**
 * Class for build entity from ResultSet query use Builder pattern
 */
public class BalanceAccountRowMapper {

    private static final String BALANCE_ACCOUNT_ID = "Id";
    private static final String BALANCE_ACCOUNT_CARD_NUMBER = "CardNumber";
    private static final String BALANCE_ACCOUNT_CV_CODE = "CVCode";
    private static final String BALANCE_ACCOUNT_BALANCE = "BalanceBankAccount";
    private static final String BALANCE_ACCOUNT_FIRST_NAME = "FirstName";
    private static final String BALANCE_ACCOUNT_LAST_NAME = "LastName";
    private static final String BALANCE_ACCOUNT_MONTH_VALID = "MonthValid";
    private static final String BALANCE_ACCOUNT_YEAR_VALID = "YearValid";

    protected BalanceAccount buildBalanceAccountRowMapper(ResultSet resultSet) throws SQLException {

        /*
        Get secret key for decrypt data from request
         */
        String secretKey = getSecretKey();

        return BalanceAccount.getBuilder()
                .id(resultSet.getLong(BALANCE_ACCOUNT_ID))
                .cardNumber(encrypt(resultSet.getString(BALANCE_ACCOUNT_CARD_NUMBER), secretKey))
                .balanceBankAccount(resultSet.getBigDecimal(BALANCE_ACCOUNT_BALANCE))
                .build();
    }

    protected CreditCard buildCreditCardRowMapper(ResultSet resultSet) throws SQLException {
        return CreditCard.getBuilder()
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
