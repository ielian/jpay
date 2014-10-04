package jpay.mocks;

import jpay.osgi.IServiceManagerService;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;

import java.util.Map;

/**
 * ServiceManagerServiceMockImpl
 */
public class MockServiceManagerService implements IServiceManagerService
{
    JAXRSServerFactoryBean jaxrsServerFactoryBean;

    public void createRestWebService (Object o, Map<String, String> stringStringMap)
    {
/*
        //JAXRSServerFactoryBean jaxrsServerFactoryBean = new JAXRSServerFactoryBean();
        //jaxrsServerFactoryBean.setServiceClass(o.getClass());
        jaxrsServerFactoryBean.setServiceBean(o);
        String address = o.getClass().getAnnotation(RestfulServiceAddress.class).value();
        //if (!address.startsWith("/")) address = "/" + address;
        jaxrsServerFactoryBean.setAddress(address);
        jaxrsServerFactoryBean.create();
*/
    }

}
