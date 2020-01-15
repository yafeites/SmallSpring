package smallSpring.resourceloader;


import org.omg.Messaging.SYNC_WITH_TRANSPORT;
import smallSpring.resource.ClassPathResource;
import smallSpring.resource.Resource;

public class DefaultResourceLoader implements ResourceLoader {

    private ClassLoader classLoader;

    @Override
    public Resource getResource(String location) {
        if (location.startsWith("/")) {
            return getResourceByPath(location);
        }
        else if(location.startsWith(CLASSPATH_URL_PREFIX))
        {
                return new ClassPathResource(location.substring(CLASSPATH_URL_PREFIX.length()),getClassLoader());
        }
        else  return null;
    }

    protected Resource getResourceByPath(String location) {
        return  null;

    }

    @Override
    public ClassLoader getClassLoader() {
        return this.classLoader!=null? classLoader:Thread.currentThread().getContextClassLoader();
    }

    public DefaultResourceLoader() {

    }
}
