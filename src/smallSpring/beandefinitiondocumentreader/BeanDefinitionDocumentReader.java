package smallSpring.beandefinitiondocumentreader;

import org.w3c.dom.Document;
import smallSpring.beandefinitionreader.BeanDefinitionReader;
import smallSpring.beandefiniton.BeanDefinition;

public interface BeanDefinitionDocumentReader {
    void registerBeanDefinitions(Document doc, BeanDefinitionReader beanDefinitionReader);
    // TODO with Exception
}
