package by.htp.hvozdzeu.service.impl;

import by.htp.hvozdzeu.dao.exception.DAOException;
import by.htp.hvozdzeu.dao.factory.DAOFactory;
import by.htp.hvozdzeu.dao.impl.BalanceBankAccountAccountRestInterfaceImpl;
import by.htp.hvozdzeu.model.dto.CheckNewCreditCardDto;
import by.htp.hvozdzeu.model.dto.TransactionDto;
import by.htp.hvozdzeu.model.dto.WriteRefillDto;
import by.htp.hvozdzeu.model.report.BalanceAccount;
import by.htp.hvozdzeu.model.response.Response;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.math.BigDecimal;
import java.sql.SQLException;

import static by.htp.hvozdzeu.util.Decoder.decrypt;
import static by.htp.hvozdzeu.util.DecoderProperties.getSecretKey;

/**
 * The class for mapping request from client
 */
@Path("/balance")
public class BalanceBankAccountServiceRestImpl {

    /**
     * Get BalanceBankAccountAccountRestInterfaceImpl methods over factory
     */
    private BalanceBankAccountAccountRestInterfaceImpl balanceBankAccountDAO = DAOFactory.getBalanceBankAccountDAOImpl();


    /**
     * The method for mapping GET query for getting current bank account balance
     *
     * @param tokenRest     String current tokenRest
     * @param cardNumber    String credit card number
     * @param appSecretCode String special code for verify then request went from current client
     * @return BalanceAccount
     * @throws DAOException Exception
     */
    @GET
    @Consumes("application/json")
    @Produces({MediaType.APPLICATION_JSON})
    public BalanceAccount balance(@HeaderParam("tokenRest") String tokenRest,
                                  @HeaderParam("cardNumber") String cardNumber,
                                  @HeaderParam("appSecretCode") String appSecretCode) throws DAOException {
        return balanceBankAccountDAO.balanceBankAccount(tokenRest, cardNumber, appSecretCode);
    }

    /**
     * The method for mapping PUT query for write-off money to bank account balance
     *
     * @param input WriteRefillDto entity
     * @return Response build answer in JSON format
     * @throws DAOException Exception
     * @throws SQLException Exception
     */
    @PUT
    @Path("/write/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response writeOffBalanceByCardNumber(WriteRefillDto input) throws DAOException, SQLException {
        return balanceBankAccountDAO.writeOffBalanceBankAccount(
                input.getTokenRest(),
                decrypt(input.getCardNumber(), getSecretKey()),
                new BigDecimal(String.valueOf(input.getAmount())),
                decrypt(input.getCvCode(), getSecretKey()),
                input.getAppSecretCode()
        );
    }


    /**
     * The method for mapping PUT query for refill (back) money to bank account balance
     *
     * @param input WriteRefillDto entity
     * @return Response build answer in JSON format
     * @throws DAOException Exception
     * @throws SQLException Exception
     */
    @PUT
    @Path("/refill/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response refillBalanceByCardNumber(WriteRefillDto input) throws DAOException, SQLException {
        return balanceBankAccountDAO.refillBalanceBankAccount(
                input.getTokenRest(),
                decrypt(input.getCardNumber(), getSecretKey()),
                new BigDecimal(String.valueOf(input.getAmount())),
                decrypt(input.getCvCode(), getSecretKey()),
                input.getAppSecretCode()
        );
    }


    /**
     * The method for mapping GET query for for verification credit card
     *
     * @param input CheckNewCreditCardDto entity
     * @return Response build answer in XML format
     * @throws DAOException Exception
     */
    @POST
    @Path("/check/card/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response checkNewCreditCard(CheckNewCreditCardDto input) throws DAOException, SQLException {
        return balanceBankAccountDAO.checkNewCreditCard(
                input.getTokenRest(),
                decrypt(input.getCardNumber(), getSecretKey()),
                decrypt(input.getCvCode(), getSecretKey()),
                input.getAppSecretCode()
        );
    }

    /**
     * The method for transfer money from between credit cards (bank accounts)
     *
     * @param input TransactionDto entity
     * @return Response build answer in JSON format
     * @throws DAOException Exception
     * @throws SQLException Exception
     */
    @PUT
    @Path("/transfer/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response transferBalanceCardBankAccount(TransactionDto input) throws DAOException, SQLException {
        return balanceBankAccountDAO.transferBalanceCardBankAccount(
                input.getTokenRest(),
                decrypt(input.getCardNumberFrom(), getSecretKey()),
                decrypt(input.getCardNumberTo(), getSecretKey()),
                input.getAmount(),
                decrypt(input.getCvCode(), getSecretKey()),
                input.getAppSecretCode()
        );
    }

}