package jpay.osgi;

import javassist.util.proxy.ProxyFactory;
import javassist.util.proxy.ProxyFactory.ClassLoaderProvider;

/**
 * JavaAssistHelper
 * This is needed because model classes are in a different bundle classloader in osgi
 */
public class JavaAssistHelper
{
    public static ClassLoaderProvider createJavaAssistClassLoader ()
    {
        ProxyFactory.classLoaderProvider = new ProxyFactory.ClassLoaderProvider()
        {
            public ClassLoader get (ProxyFactory pf)
            {
                return Thread.currentThread().getContextClassLoader();
            }
        };
        return ProxyFactory.classLoaderProvider;
    }
}

