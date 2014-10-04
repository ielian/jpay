package jpay.service;

import jpay.service.JettyTestServer;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.*;
import org.apache.cxf.helpers.IOUtils;
import org.apache.cxf.io.CachedOutputStream;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.ws.rs.core.MediaType;
import java.io.InputStream;


/**
 * EventServiceTest
 */
public class CreditCardServiceHttpTest
{
    private static JettyTestServer server = new JettyTestServer();

    @BeforeClass
    public static void setUp () throws Exception
    {
        server.startJettyServer();
    }

    @AfterClass
    public static void tearDown () throws Exception
    {
        server.stopJettyServer();

    }

    @Test
    public void testGetCreditCard () throws Exception
    {
        System.out.println("testGetCreditCard...");

        GetMethod get = new GetMethod(JettyTestServer.SERVICE_REST_URL);
        HttpClient httpclient = new HttpClient();

        try {
            int result = httpclient.executeMethod(get);
            String s = get.getResponseBodyAsString();
            System.out.println("Response status code: " + result);
            System.out.println("Response body: " + s);
        } finally {
            get.releaseConnection();
        }

    }

    @Test
    public void testAddCreditCard () throws Exception
    {
        System.out.println("testAddCreditCard...");

        String content = "{\"name\":\"name1\",\"number\":\"number1\"}";
        RequestEntity entity = new StringRequestEntity(content, MediaType.APPLICATION_JSON, "UTF-8");
        PostMethod post = new PostMethod(JettyTestServer.SERVICE_REST_URL);
        post.setRequestEntity(entity);
        HttpClient httpclient = new HttpClient();

        try {
            int result = httpclient.executeMethod(post);
            String s = post.getResponseBodyAsString();
            System.out.println("Response status code: " + result);
            System.out.println("Response body: " + s);
        } finally {
            post.releaseConnection();
        }

    }

    @Test
    public void testAddEventFromParams () throws Exception
    {
        System.out.println("testAddEventFromParams...");

        PostMethod post = new PostMethod(JettyTestServer.SERVICE_REST_URL + "/add");
        post.addParameter("name", "name2");
        post.addParameter("number", "number2");
        HttpClient httpclient = new HttpClient();

        try {
            int result = httpclient.executeMethod(post);
            String s = post.getResponseBodyAsString();
            System.out.println("Response status code: " + result);
            System.out.println("Response body: " + s);
        } finally {
            post.releaseConnection();
        }

    }

    private static String getStringFromInputStream (InputStream in) throws Exception
    {
        CachedOutputStream bos = new CachedOutputStream();
        IOUtils.copy(in, bos);
        in.close();
        bos.close();
        return bos.getOut().toString();
    }

}

