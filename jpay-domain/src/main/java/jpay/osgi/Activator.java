package jpay.osgi;

import org.hibernate.ejb.HibernatePersistence;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import javax.persistence.spi.PersistenceProvider;
import java.util.Dictionary;
import java.util.Hashtable;

/**
 * Activator
 */
public class Activator implements BundleActivator
{
    private ServiceRegistration serviceRegistration;

    @Override
    public void start (BundleContext context) throws Exception
    {
        HibernatePersistence persistence = new HibernatePersistence();
        Dictionary<String, String> props = new Hashtable<String, String>();
        props.put("javax.persistence.provider", persistence.getClass().getName());
        serviceRegistration = context.registerService(PersistenceProvider.class.getName(), persistence, props);
    }

    @Override
    public void stop (BundleContext bundleContext) throws Exception
    {
        serviceRegistration.unregister();
    }
}
