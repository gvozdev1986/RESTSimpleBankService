package by.htp.hvozdzeu.service.impl;

import by.htp.hvozdzeu.dao.exception.DAOException;
import by.htp.hvozdzeu.dao.factory.DAOFactory;
import by.htp.hvozdzeu.dao.impl.AuthDAOImpl;
import by.htp.hvozdzeu.model.Account;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/auth")
public class AuthServiceImpl {

    private AuthDAOImpl authDAO = DAOFactory.getAuthDAOImpl();

    // URI:
    // /RESTfulCRUD/rest/auth/{account}
    @POST
    @Produces({ MediaType.APPLICATION_XML})
    public String tokenAccess(Account account) throws DAOException {
        return authDAO.getToken(account);
    }

}
