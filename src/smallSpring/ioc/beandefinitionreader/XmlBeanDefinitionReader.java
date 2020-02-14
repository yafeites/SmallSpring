package smallSpring.ioc.beandefinitionreader;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import smallSpring.ioc.beandefinitiondocumentreader.BeanDefinitionDocumentReader;
import smallSpring.ioc.beandefinitiondocumentreader.DefaultBeanDefinitionDocumentReader;
import smallSpring.ioc.documentloader.DefaultDocumentLoader;
import smallSpring.ioc.documentloader.DocumentLoader;
import smallSpring.ioc.registry.BeanDefinitionRegistry;
import smallSpring.ioc.resource.Resource;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;

public class XmlBeanDefinitionReader extends  AbstractBeanDefinitionReader{
    private DocumentLoader documentLoader = new DefaultDocumentLoader();
    private BeanDefinitionDocumentReader beanDefinitionDocumentReader=new DefaultBeanDefinitionDocumentReader();
    public  XmlBeanDefinitionReader(BeanDefinitionRegistry registry) {
        super(registry);
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



    int doLoadBeanDefinitions(InputSource inputSource, Resource resource)
    {
        Document doc = null;
        try {
            doc = doLoadDocument(inputSource, resource);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        return registerBeanDefinitions(doc, resource);
    }
    public  int registerBeanDefinitions(Document doc, Resource resource) {
        BeanDefinitionDocumentReader documentReader = createBeanDefinitionDocumentReader();
        int countBefore = getRegistry().getBeanDefinitionCount();
        documentReader.registerBeanDefinitions(doc,this);
        return getRegistry().getBeanDefinitionCount() - countBefore;

    }
    BeanDefinitionDocumentReader createBeanDefinitionDocumentReader()
    {
        return  this.beanDefinitionDocumentReader;
    }
     Document doLoadDocument(InputSource inputSource, Resource resource) throws ParserConfigurationException {
        return this.documentLoader.loadDocument(inputSource);
    }


}
