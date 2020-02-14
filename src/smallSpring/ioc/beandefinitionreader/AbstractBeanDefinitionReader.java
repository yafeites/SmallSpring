package smallSpring.ioc.beandefinitionreader;

import smallSpring.ioc.documentloader.DefaultDocumentLoader;
import smallSpring.ioc.documentloader.DocumentLoader;
import smallSpring.ioc.registry.BeanDefinitionRegistry;
import smallSpring.ioc.resource.Resource;
import smallSpring.ioc.resourceloader.ResourceLoader;

public  abstract  class AbstractBeanDefinitionReader implements  BeanDefinitionReader {
    private final BeanDefinitionRegistry registry;
    private ResourceLoader resourceLoader;
    private DocumentLoader documentLoader = new DefaultDocumentLoader();
    AbstractBeanDefinitionReader(BeanDefinitionRegistry registry) {
        this.registry=registry;
    }


    public  void setResourceLoader(ResourceLoader  resourceLoader)
    {
         this.resourceLoader=resourceLoader;
    }
    @Override
    public ResourceLoader getResourceLoader() {
        return this.resourceLoader;
    }
    @Override
    public final BeanDefinitionRegistry getRegistry() {
        return this.registry;
    }
    @Override
    public int loadBeanDefinitions(String location) {
      Resource resource=getResourceLoader().getResource(location);
      int count=loadBeanDefinitions(resource);
      return count;
    }




}
