package jpay.service;

import jpay.model.CreditCard;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.Collection;

/**
 * CreditCardService
 */
@Path("/")
public interface CreditCardService
{
    @GET
    @Path("/info")
    @Produces(MediaType.TEXT_PLAIN)
    public Response info ();

    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @POST
    CreditCard addCreditCard (CreditCard cc);

    @Produces({MediaType.APPLICATION_JSON})
    @Path("/add")
    @POST
    CreditCard addCreditCardFromParams (@Context UriInfo uriInfo, @FormParam("name") String name, @FormParam("number") String number);

    @Produces({MediaType.APPLICATION_JSON})
    @Path("/number/{number}")
    @GET
    public CreditCard getCreditCardByNumber (@PathParam("number") String number);

    @Produces({MediaType.APPLICATION_JSON})
    @Path("/name/{name}")
    @GET
    public Collection<CreditCard> getCreditCardByName (@PathParam("name") String name);

    @Produces({MediaType.APPLICATION_JSON})
    @GET
    public Collection<CreditCard> getAllCreditCards ();
}
