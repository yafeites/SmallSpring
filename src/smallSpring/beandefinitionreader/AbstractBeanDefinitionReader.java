package smallSpring.beandefinitionreader;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import smallSpring.documentloader.DefaultDocumentLoader;
import smallSpring.documentloader.DocumentLoader;
import smallSpring.registry.BeanDefinitionRegistry;
import smallSpring.resource.Resource;
import smallSpring.resourceloader.ResourceLoader;

import java.io.IOException;
import java.io.InputStream;

public  abstract  class AbstractBeanDefinitionReader implements  BeanDefinitionReader {
    private final BeanDefinitionRegistry registry;
    private ResourceLoader resourceLoader;
    private DocumentLoader documentLoader = new DefaultDocumentLoader();
    AbstractBeanDefinitionReader(BeanDefinitionRegistry registry) {
        this.registry=registry;
        if (this.registry instanceof ResourceLoader) {
            this.resourceLoader = (ResourceLoader) this.registry;
        }
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
