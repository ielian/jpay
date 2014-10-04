package jpay.service;

import jpay.service.CreditCardService;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.client.JAXRSClientFactory;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.jaxws.JaxWsServerFactoryBean;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.ContextLoaderListener;

import java.util.Collections;

/**
 * JettyTestServer
 */
public class JettyTestServer
{
    public static final int SERVER_PORT = 8123;
    public static final String SERVER_HOST = "localhost";
    public static final String SERVER_ADDRESS = "http://" + SERVER_HOST + ":" + SERVER_PORT;
    public static final String SERVICE_REST_CONTEXT = "/cxf/cards";
    public static final String SERVICE_SOAP_CONTEXT = "/cxf/cards";
    public static final String SERVICE_REST_URL = SERVER_ADDRESS + SERVICE_REST_CONTEXT;
    public static final String SERVICE_SOAP_URL = SERVER_ADDRESS + SERVICE_SOAP_CONTEXT;
    public static final String SERVICE_TEST_CONTEXT = "classpath:jpay-service-test-context.xml";

    private Server jettyServer = new Server(SERVER_PORT);
    private org.apache.cxf.endpoint.Server cxfServer;
    private CreditCardService creditCardService;

    public void startJettyServer () throws Exception
    {
        final ServletHolder servletHolder = new ServletHolder(new CXFServlet());
        final ServletContextHandler context = new ServletContextHandler();
        context.setContextPath("/");
        context.addServlet(servletHolder, "/cxf/*");
        //context.addServlet(new ServletHolder(new BrokerTestServlet()), "/webui/*");
        context.addEventListener(new ContextLoaderListener());
        //Config from AppConfig instead of xml
        //context.setInitParameter("contextClass", AnnotationConfigWebApplicationContext.class.getTag());
        //context.setInitParameter( "contextConfigLocation", AppConfig.class.getTag() );
        context.setInitParameter("contextConfigLocation", SERVICE_TEST_CONTEXT);

        jettyServer.setHandler(context);
        jettyServer.start();
    }

    public void startRestService (CreditCardService creditCardService)
    {
        this.creditCardService = creditCardService;
        ApplicationContext ctx = new ClassPathXmlApplicationContext(SERVICE_TEST_CONTEXT);
        JAXRSServerFactoryBean factory = (JAXRSServerFactoryBean)ctx.getBean("cards"); //new JAXRSServerFactoryBean();
        factory.setAddress(SERVICE_REST_URL);
        factory.setServiceBean(creditCardService);
        //factory.setResourceClasses(CreditCardServiceImpl.class);
        //factory.setResourceProvider(CreditCardServiceImpl.class, new SingletonResourceProvider(creditCardService));
        cxfServer = factory.create();
        cxfServer.start();
    }

    public void startSoapService (CreditCardService creditCardService)
    {
        this.creditCardService = creditCardService;
        JaxWsServerFactoryBean factory = new JaxWsServerFactoryBean();
        factory.setAddress(SERVICE_SOAP_URL);
        factory.setServiceBean(creditCardService);
        cxfServer = factory.create();
        cxfServer.start();
    }

    public CreditCardService getRestServiceProxy ()
    {
        CreditCardService creditCardService = JAXRSClientFactory.create(SERVICE_REST_URL, CreditCardService.class, Collections.singletonList(new JacksonJsonProvider()));
        return creditCardService;
    }

    public CreditCardService getSoapServiceProxy ()
    {
        JaxWsProxyFactoryBean proxy = new JaxWsProxyFactoryBean();
        proxy.setServiceClass(CreditCardService.class);
        proxy.setAddress(SERVICE_SOAP_URL);
        return (CreditCardService)proxy.create();

    }

    public CreditCardService getCreditCardService ()
    {
        return creditCardService;
    }

    public void startCxfServer () throws Exception
    {
        cxfServer.start();

    }

    public void stopJettyServer () throws Exception
    {
        jettyServer.stop();
    }

    public void stopRestService ()
    {
        cxfServer.stop();
    }

    public void stopSoapService ()
    {
        cxfServer.stop();
    }

    public static void main (final String[] args) throws Exception
    {
        JettyTestServer s = new JettyTestServer();
        s.startJettyServer();
        System.out.println("Server ready...");
        Thread.sleep(5 * 60 * 1000);
        System.out.println("Server exiting");
        s.stopJettyServer();
        System.exit(0);
    }

}
