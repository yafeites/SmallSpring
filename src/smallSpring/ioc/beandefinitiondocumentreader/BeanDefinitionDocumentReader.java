package smallSpring.ioc.beandefinitiondocumentreader;

import org.w3c.dom.Document;
import smallSpring.ioc.beandefinitionreader.BeanDefinitionReader;
import smallSpring.ioc.beandefiniton.BeanDefinition;

public interface BeanDefinitionDocumentReader {
    void registerBeanDefinitions(Document doc, BeanDefinitionReader beanDefinitionReader);
    // TODO with Exception
}
