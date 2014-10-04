package jpay.servlet;

import jpay.model.CreditCard;
import jpay.service.CreditCardService;
import org.junit.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

//import jpay.service.CreditCardServiceImpl;
//import org.apache.cxf.jaxrs.client.JAXRSClientFactory;

/**
 * EventServiceSpringTest
 */
public class CreditCardServletTest
{
    private static JettyTestServer server = new JettyTestServer();
    private ApplicationContext ctx = new ClassPathXmlApplicationContext(server.CLIENT_TEST_CONTEXT);

    @BeforeClass
    public static void setUp () throws Exception
    {
        //server.startRestService(new CreditCardServiceImpl());
        server.startJettyServer();
        server.startJettyServlet();
    }

    @AfterClass
    public static void tearDown () throws Exception
    {
        //server.stopRestService();
        server.stopJettyServlet();
        server.stopJettyServer();

    }

    @Ignore
    @Test
    public void testAddCreditCardThroughProxyFromSpringConfig ()
    {
        //CreditCardService eventService = JAXRSClientFactory.create(server.SERVICE_REST_URL, CreditCardService.class, "jpay-webservice-test-context.xml");
        CreditCardServlet creditCardServlet = (CreditCardServlet)ctx.getBean("creditCardServlet");
        CreditCardService cardService = creditCardServlet.getCreditCardService();

        //Add through proxy
        CreditCard c1 = cardService.addCreditCard(new CreditCard("name1", "number1"));

        //Get through proxy
        CreditCard c2 = cardService.getCreditCardByNumber("number1");

        assert c2.equals(c1);
    }

}
