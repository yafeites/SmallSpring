package smallSpring.beandefinitionreader;

import smallSpring.resource.Resource;

public interface BeanDefinitionReader {
    int loadBeanDefinitions(Resource resource);
    int loadBeanDefinitions(String location);
}
