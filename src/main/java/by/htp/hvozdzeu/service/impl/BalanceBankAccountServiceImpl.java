package by.htp.hvozdzeu.service.impl;

import by.htp.hvozdzeu.dao.exception.DAOException;
import by.htp.hvozdzeu.dao.factory.DAOFactory;
import by.htp.hvozdzeu.dao.impl.BalanceBankAccountDAOImpl;
import by.htp.hvozdzeu.model.report.BalanceAccount;
import by.htp.hvozdzeu.model.response.Response;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.math.BigDecimal;
import java.sql.SQLException;

@Path("/balance")
public class BalanceBankAccountServiceImpl {

    private BalanceBankAccountDAOImpl balanceBankAccountDAO = DAOFactory.getBalanceBankAccountDAOImpl();

    @GET
    @Produces({MediaType.APPLICATION_XML})
    public BalanceAccount tokenAccess(@HeaderParam("tokenRest") String tokenRest,
                                      @HeaderParam("cardNumber") String cardNumber) throws DAOException {
        return balanceBankAccountDAO.balanceBankAccount(tokenRest, cardNumber);
    }

    @PUT
    @Path("/write/")
    @Produces({MediaType.APPLICATION_XML})
    public Response writeOffBalanceByCardNumber(@HeaderParam("tokenRest") String tokenRest,
                                                @HeaderParam("cardNumber") String cardNumber,
                                                @HeaderParam("amount") String amount) throws DAOException, SQLException {
        return balanceBankAccountDAO.writeOffBalanceBankAccount(tokenRest, cardNumber, new BigDecimal(amount));
    }

    @PUT
    @Path("/refill/")
    @Produces({MediaType.APPLICATION_XML})
    public Response refillBalanceByCardNumber(@HeaderParam("tokenRest") String tokenRest,
                                              @HeaderParam("cardNumber") String cardNumber,
                                              @HeaderParam("amount") String amount) throws DAOException, SQLException {
        return balanceBankAccountDAO.refillBalanceBankAccount(tokenRest, cardNumber, new BigDecimal(amount));
    }

    @GET
    @Path("/check/card/")
    @Produces({MediaType.APPLICATION_XML})
    public Response checkCardWriteOffBalance(@HeaderParam("tokenRest") String tokenRest,
                                             @HeaderParam("cardNumber") String cardNumber) throws DAOException {
        return balanceBankAccountDAO.checkCreditCardBankAccount(tokenRest, cardNumber);
    }

}
