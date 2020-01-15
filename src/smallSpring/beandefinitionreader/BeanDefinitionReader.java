package smallSpring.beandefinitionreader;

import smallSpring.registry.BeanDefinitionRegistry;
import smallSpring.resource.Resource;
import smallSpring.resourceloader.ResourceLoader;

public interface BeanDefinitionReader {
    int loadBeanDefinitions(Resource resource);
    int loadBeanDefinitions(String location);
    BeanDefinitionRegistry getRegistry();
    ResourceLoader getResourceLoader();
}
