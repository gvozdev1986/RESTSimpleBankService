package by.htp.hvozdzeu.service.impl;

import by.htp.hvozdzeu.dao.impl.CreditCardDAOImpl;
import by.htp.hvozdzeu.model.CreditCard;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/cards")
public class CreditCardServiceImpl {

    // URI:
    // /RESTfulCRUD/rest/cards
    @GET
    @Produces({ MediaType.APPLICATION_XML })
    public List<CreditCard> getCreditCards_JSON() {
        List<CreditCard> listOfCreditCards = CreditCardDAOImpl.getAllCreditCard();
        return listOfCreditCards;
    }

    // URI:
    // /RESTfulCRUD/rest/cards/{cardNo}
    @GET
    @Path("/{cardNo}")
    @Produces({ MediaType.APPLICATION_XML})
    public CreditCard getCreditCard(@PathParam("cardNo") String cardNo) {
        return CreditCardDAOImpl.getCreditCard(cardNo);
    }

    // URI:
    // /RESTfulCRUD/rest/cards
    @POST
    @Produces({ MediaType.APPLICATION_XML})
    public CreditCard addCreditCard(CreditCard creditCard) {
        return CreditCardDAOImpl.addCreditCard(creditCard);
    }

    // URI:
    // /RESTfulCRUD/rest/cards
    @PUT
    @Produces({ MediaType.APPLICATION_XML })
    public CreditCard updateCreditCard(CreditCard creditCard) {
        return CreditCardDAOImpl.updateCreditCard(creditCard);
    }

    // URL:
    // /RESTfulCRUD/rest/cards/{cardNo}
    @DELETE
    @Path("/{cardNo}")
    @Produces({ MediaType.APPLICATION_XML })
    public void deleteCreditCard(@PathParam("cardNo") String cardNo) {
        CreditCardDAOImpl.deleteCreditCard(cardNo);
    }

}
