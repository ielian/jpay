package jpay.service;

import jpay.domain.CreditCardManager;
import jpay.model.CreditCard;
import org.springframework.beans.factory.InitializingBean;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.Collection;


@Path("/")
public class CreditCardServiceImpl implements CreditCardService, InitializingBean
{
    @Inject
    private CreditCardManager creditCardManager;

    @Override
    public void afterPropertiesSet () throws Exception
    {
    }

    @Override
    public Response info ()  {
        return Response.ok("CreditCardServiceImpl").build();
    }

    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @POST
    public CreditCard addCreditCard (CreditCard cc)
    {
        creditCardManager.addCreditCard(cc);
        return null;
    }

    @Override
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/add")
    @POST
    public CreditCard addCreditCardFromParams (@Context final UriInfo uriInfo,
                                     @FormParam("name") final String name,
                                     @FormParam("number") final String number)
    {
        CreditCard cc = new CreditCard(name, number);
        creditCardManager.addCreditCard(cc);
        return null;

    }

    @Override
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/name/{name}")
    @GET
    public CreditCard getCreditCardByNumber (@PathParam("number") final String number)
    {
        return creditCardManager.getCreditCardByNumber(number);
    }

    @Override
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/name/{name}")
    @GET
    public Collection<CreditCard> getCreditCardByName (@PathParam("name") final String name)
    {
        return creditCardManager.getCreditCardByName(name);
    }

    @Override
    @Produces({MediaType.APPLICATION_JSON})
    @GET
    public Collection<CreditCard> getAllCreditCards ()
    {
        return creditCardManager.getAllCreditCards();
    }


    public CreditCardManager getCreditCardManagerManager ()
    {
        return this.creditCardManager;
    }

    public void setCreditCardManager (CreditCardManager m)
    {
        this.creditCardManager = m;
    }

}
