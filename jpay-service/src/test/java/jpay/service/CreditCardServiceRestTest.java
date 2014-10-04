package jpay.service;

import jpay.model.CreditCard;
import org.apache.cxf.jaxrs.client.WebClient;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.ws.rs.core.MediaType;
import java.util.Collection;
import java.util.Collections;

public class CreditCardServiceRestTest
{

    private static JettyTestServer server = new JettyTestServer();

    @BeforeClass
    public static void setUp ()
    {
        server.startRestService(new CreditCardServiceImpl());
    }

    @AfterClass
    public static void tearDown ()
    {
        server.stopRestService();

    }

    @Test
    public void testAddCreditCardThroughRestProxy ()
    {
        System.out.println("testAddCreditCardThroughRestProxy...");
        CreditCardService creditCardServiceProxy = server.getRestServiceProxy();

        //Add through proxy
        creditCardServiceProxy.addCreditCard(new CreditCard("name3", "number3"));

        //Get through proxy
        Collection<CreditCard> cards = creditCardServiceProxy.getCreditCardByName("name1");

        for (CreditCard cc : cards) {
            System.out.println(cc);
        }

    }

    @Test
    public void testAddCreditCardThroughWebClient ()
    {
        System.out.println("testAddCreditCardThroughWebClient...");
        WebClient webClient = WebClient.create(JettyTestServer.SERVICE_REST_URL, Collections.singletonList(new JacksonJsonProvider()));
        webClient.accept(MediaType.APPLICATION_JSON).type(MediaType.APPLICATION_JSON);

        CreditCard c1 = webClient.post(new CreditCard("name4", "number4"), CreditCard.class);

        Collection<? extends CreditCard> cards = webClient.getCollection(CreditCard.class);
        for (CreditCard cc : cards) {
            System.out.println(cc);
        }
        webClient = WebClient.create(JettyTestServer.SERVICE_REST_URL + "/name/name4", Collections.singletonList(new JacksonJsonProvider()));
        webClient.accept(MediaType.APPLICATION_JSON).type(MediaType.APPLICATION_JSON);
        //CreditCard c2 = webClient.get(CreditCard.class);


    }

}
