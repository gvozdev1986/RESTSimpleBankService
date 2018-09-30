package by.htp.hvozdzeu.service.impl;

import by.htp.hvozdzeu.dao.exception.DAOException;
import by.htp.hvozdzeu.dao.factory.DAOFactory;
import by.htp.hvozdzeu.dao.impl.BalanceBankAccountDAOImpl;
import by.htp.hvozdzeu.model.report.BalanceAccount;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.math.BigDecimal;

@Path("/balance")
public class BalanceBankAccountServiceImpl {

    private BalanceBankAccountDAOImpl balanceBankAccountDAO = DAOFactory.getBalanceBankAccountDAOImpl();

    // URI:
    // /RESTfulCRUD/rest/balance/{parameters}
    @GET
    @Produces({MediaType.APPLICATION_XML})
    public BalanceAccount tokenAccess(@HeaderParam("tokenRest") String tokenRest,
                                      @HeaderParam("cardNumber") String cardNumber) throws DAOException {
        return balanceBankAccountDAO.balanceBankAccount(tokenRest, cardNumber);
    }

    // URI:
    // /RESTfulCRUD/rest/balance/{parameters}
    @GET
    @Path("/write/")
    @Produces({MediaType.APPLICATION_XML})
    public boolean writeOffBalanceByCardNumber(@HeaderParam("tokenRest") String tokenRest,
                                               @HeaderParam("cardNumber") String cardNumber,
                                               @HeaderParam("amount") String amount) throws DAOException {
        return balanceBankAccountDAO.writeOffBalanceBankAccount(tokenRest, cardNumber, new BigDecimal(amount));
    }


}
