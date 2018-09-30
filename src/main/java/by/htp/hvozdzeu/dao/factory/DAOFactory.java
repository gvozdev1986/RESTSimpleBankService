package by.htp.hvozdzeu.dao.factory;

import by.htp.hvozdzeu.dao.impl.AuthDAOImpl;
import by.htp.hvozdzeu.dao.impl.BalanceBankAccountDAOImpl;

/**
 * Class that provides instances of DAO
 */
public class DAOFactory {

    private static AuthDAOImpl authDAOImpl;
    private static BalanceBankAccountDAOImpl balanceBankAccountDAOImpl;

    static {
        authDAOImpl = new AuthDAOImpl();
        balanceBankAccountDAOImpl = new BalanceBankAccountDAOImpl();
    }

    private DAOFactory() {
    }

    public static AuthDAOImpl getAuthDAOImpl() {
        return authDAOImpl;
    }

    public static BalanceBankAccountDAOImpl getBalanceBankAccountDAOImpl() {
        return balanceBankAccountDAOImpl;
    }

}
