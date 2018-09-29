package by.htp.hvozdzeu.dao.factory;

import by.htp.hvozdzeu.dao.impl.AuthDAOImpl;
import by.htp.hvozdzeu.dao.impl.BalanceBankAccountDAOImpl;
import by.htp.hvozdzeu.dao.impl.CreditCardDAOImpl;

/**
 * Class that provides instances of DAO
 */
public class DAOFactory {

    private static AuthDAOImpl authDAOImpl;
    private static CreditCardDAOImpl creditCardDAOImpl;
    private static BalanceBankAccountDAOImpl balanceBankAccountDAOImpl;

    static {
        authDAOImpl = new AuthDAOImpl();
        creditCardDAOImpl = new CreditCardDAOImpl();
        balanceBankAccountDAOImpl = new BalanceBankAccountDAOImpl();
    }


    private DAOFactory() {
    }

    public static AuthDAOImpl getAuthDAOImpl() {
        return authDAOImpl;
    }

    public static CreditCardDAOImpl getCreditCardDAOImpl() {
        return creditCardDAOImpl;
    }

    public static BalanceBankAccountDAOImpl getBalanceBankAccountDAOImpl() {
        return balanceBankAccountDAOImpl;
    }

}
