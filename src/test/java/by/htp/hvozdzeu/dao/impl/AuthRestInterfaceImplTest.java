package by.htp.hvozdzeu.dao.impl;

import by.htp.hvozdzeu.dao.exception.DAOException;
import by.htp.hvozdzeu.dao.factory.DAOFactory;
import org.junit.Test;

import static org.junit.Assert.*;

public class AuthRestInterfaceImplTest {

    private AuthRestInterfaceImpl authRestInterface = DAOFactory.getAuthDAOImpl();

    @Test
    public void findToken() throws DAOException {
        authRestInterface.findToken("b3ac0c34-27f8-4663-9094-c38718b28f37");
    }
}