package by.htp.hvozdzeu.dao;

import by.htp.hvozdzeu.dao.exception.DAOException;
import by.htp.hvozdzeu.model.report.BalanceAccount;

public interface BalanceBankAccountDAO {

    BalanceAccount balanceBankAccount(String tokenRest, String cardNumber) throws DAOException;

}
