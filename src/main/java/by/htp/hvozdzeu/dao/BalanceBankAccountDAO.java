package by.htp.hvozdzeu.dao;

import by.htp.hvozdzeu.dao.exception.DAOException;
import by.htp.hvozdzeu.model.report.BalanceAccount;
import by.htp.hvozdzeu.model.response.Response;

import java.math.BigDecimal;
import java.sql.SQLException;

public interface BalanceBankAccountDAO {

    BalanceAccount balanceBankAccount(String tokenRest, String cardNumber) throws DAOException;
    Response writeOffBalanceBankAccount(String tokenRest, String cardNumber, BigDecimal amount) throws DAOException, SQLException;
    Response refillBalanceBankAccount(String tokenRest, String cardNumber, BigDecimal amount) throws DAOException, SQLException;
}
