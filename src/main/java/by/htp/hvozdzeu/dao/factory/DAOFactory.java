package by.htp.hvozdzeu.dao.factory;

import by.htp.hvozdzeu.dao.impl.AuthRestInterfaceImpl;
import by.htp.hvozdzeu.dao.impl.BalanceBankAccountAccountRestInterfaceImpl;

/**
 * Class that provides instances of DAO
 */
public class DAOFactory {

    private static AuthRestInterfaceImpl authDAOImpl;
    private static BalanceBankAccountAccountRestInterfaceImpl balanceBankAccountDAOImpl;

    static {
        authDAOImpl = new AuthRestInterfaceImpl();
        balanceBankAccountDAOImpl = new BalanceBankAccountAccountRestInterfaceImpl();
    }

    private DAOFactory() {
    }

    public static AuthRestInterfaceImpl getAuthDAOImpl() {
        return authDAOImpl;
    }

    public static BalanceBankAccountAccountRestInterfaceImpl getBalanceBankAccountDAOImpl() {
        return balanceBankAccountDAOImpl;
    }

}
