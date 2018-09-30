package by.htp.hvozdzeu.dao.mapper;

import by.htp.hvozdzeu.model.CreditCard;
import by.htp.hvozdzeu.model.report.BalanceAccount;

import java.sql.ResultSet;
import java.sql.SQLException;

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
        return BalanceAccount.getBuilder()
                .id(resultSet.getLong(BALANCE_ACCOUNT_ID))
                .cardNumber(resultSet.getString(BALANCE_ACCOUNT_CARD_NUMBER))
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
