package smallSpring.beandefinitionreader;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import smallSpring.documentloader.DefaultDocumentLoader;
import smallSpring.documentloader.DocumentLoader;
import smallSpring.registry.BeanDefinitionRegistry;
import smallSpring.resource.Resource;

import java.io.IOException;
import java.io.InputStream;

public  abstract  class AbstractBeanDefinitionReader implements  BeanDefinitionReader {
    private final BeanDefinitionRegistry registry;
    private DocumentLoader documentLoader = new DefaultDocumentLoader();
    AbstractBeanDefinitionReader(BeanDefinitionRegistry registry) {
        this.registry=registry;
    }
    @Override
    public int loadBeanDefinitions(Resource resource){
        InputStream inputStream = null;
        try {
            inputStream = resource.getInputStream();
            try {
                InputSource inputSource = new InputSource(inputStream);
                return doLoadBeanDefinitions(inputSource, resource);
            }
            finally {
                inputStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

      return  0;
    }

    protected  int doLoadBeanDefinitions(InputSource inputSource, Resource resource)
    {
        Document doc = doLoadDocument(inputSource, resource);
        return registerBeanDefinitions(doc, resource);
    }

    private int registerBeanDefinitions(Document doc, Resource resource) {
    }

    private Document doLoadDocument(InputSource inputSource, Resource resource) {
    }
}
