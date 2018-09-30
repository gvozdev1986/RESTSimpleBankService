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

/**
 * The class for mapping request from client
 */
@Path("/balance")
public class BalanceBankAccountServiceImpl {

    /**
     * Get BalanceBankAccountDAOImpl methods over factory
     */
    private BalanceBankAccountDAOImpl balanceBankAccountDAO = DAOFactory.getBalanceBankAccountDAOImpl();

    /**
     * The method for mapping GET query for getting current bank account balance
     *
     * @param tokenRest  String current tokenRest
     * @param cardNumber String credit card number
     * @return BalanceAccount
     * @throws DAOException Exception
     */
    @GET
    @Produces({MediaType.APPLICATION_XML})
    public BalanceAccount balance(@HeaderParam("tokenRest") String tokenRest,
                                  @HeaderParam("cardNumber") String cardNumber) throws DAOException {
        return balanceBankAccountDAO.balanceBankAccount(tokenRest, cardNumber);
    }

    /**
     * The method for mapping PUT query for write-off money from bank account balance
     *
     * @param tokenRest  String current tokenRest
     * @param cardNumber String credit card number
     * @param amount     BigDecimal amount for write-off
     * @param cvCode     String code for verification credit card
     * @return Response build answer in XML format
     * @throws DAOException Exception
     * @throws SQLException Exception
     */
    @PUT
    @Path("/write/")
    @Produces({MediaType.APPLICATION_XML})
    public Response writeOffBalanceByCardNumber(@HeaderParam("tokenRest") String tokenRest,
                                                @HeaderParam("cardNumber") String cardNumber,
                                                @HeaderParam("amount") String amount,
                                                @HeaderParam("cvCode") String cvCode) throws DAOException, SQLException {
        return balanceBankAccountDAO.writeOffBalanceBankAccount(tokenRest, cardNumber, new BigDecimal(amount), cvCode);
    }

    /**
     * The method for mapping PUT query for refill (back) money to bank account balance
     *
     * @param tokenRest  String current tokenRest
     * @param cardNumber String credit card number
     * @param amount     BigDecimal amount for write-off
     * @param cvCode     String code for verification credit card
     * @return Response build answer in XML format
     * @throws DAOException Exception
     * @throws SQLException Exception
     */
    @PUT
    @Path("/refill/")
    @Produces({MediaType.APPLICATION_XML})
    public Response refillBalanceByCardNumber(@HeaderParam("tokenRest") String tokenRest,
                                              @HeaderParam("cardNumber") String cardNumber,
                                              @HeaderParam("amount") String amount,
                                              @HeaderParam("cvCode") String cvCode) throws DAOException, SQLException {
        return balanceBankAccountDAO.refillBalanceBankAccount(tokenRest, cardNumber, new BigDecimal(amount), cvCode);
    }

    /**
     * The method for mapping GET query for for verification credit card
     *
     * @param tokenRest  String current tokenRest
     * @param cardNumber String credit card number
     * @param cvCode     String code for verification credit card
     * @return Response build answer in XML format
     * @throws DAOException Exception
     */
    @GET
    @Path("/check/card/")
    @Produces({MediaType.APPLICATION_XML})
    public Response checkCardWriteOffBalance(@HeaderParam("tokenRest") String tokenRest,
                                             @HeaderParam("cardNumber") String cardNumber,
                                             @HeaderParam("cvCode") String cvCode) throws DAOException {
        return balanceBankAccountDAO.checkCreditCardBankAccount(tokenRest, cardNumber, cvCode);
    }

}
