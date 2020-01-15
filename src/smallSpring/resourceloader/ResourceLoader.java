package smallSpring.resourceloader;

import smallSpring.resource.Resource;

public interface ResourceLoader {
    String CLASSPATH_URL_PREFIX="classpath:";
    Resource getResource(String location);

    ClassLoader getClassLoader();
}
