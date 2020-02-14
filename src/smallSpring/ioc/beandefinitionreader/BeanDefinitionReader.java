package smallSpring.ioc.beandefinitionreader;

import smallSpring.ioc.registry.BeanDefinitionRegistry;
import smallSpring.ioc.resource.Resource;
import smallSpring.ioc.resourceloader.ResourceLoader;

public interface BeanDefinitionReader {
    int loadBeanDefinitions(Resource resource);
    int loadBeanDefinitions(String location);
    BeanDefinitionRegistry getRegistry();
    ResourceLoader getResourceLoader();
}
