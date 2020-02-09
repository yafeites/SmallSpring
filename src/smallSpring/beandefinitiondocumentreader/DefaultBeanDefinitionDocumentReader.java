package smallSpring.beandefinitiondocumentreader;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import smallSpring.beandefinitionreader.BeanDefinitionReader;
import smallSpring.beans.beandefinitionholder.BeanDefinitionHolder;
import smallSpring.beans.delegate.BeanDefinitionParserDelegate;
import smallSpring.registry.BeanDefinitionRegistry;

public class DefaultBeanDefinitionDocumentReader implements  BeanDefinitionDocumentReader {
    private static final String BEAN_ELEMENT ="bean";
    BeanDefinitionParserDelegate delegate=new BeanDefinitionParserDelegate();
    private BeanDefinitionReader beanDefinitionReader;

    @Override
    public void registerBeanDefinitions(Document doc, BeanDefinitionReader beanDefinitionReader) {
        this.beanDefinitionReader=beanDefinitionReader;
        Element root = doc.getDocumentElement();
        doRegisterBeanDefinitions(root);
    }

    private void doRegisterBeanDefinitions(Element root) {
        parseBeanDefinitions(root, this.delegate);
    }

    private void parseBeanDefinitions(Element root, BeanDefinitionParserDelegate delegate) {
        NodeList nl = root.getChildNodes();
        for (int i = 0; i < nl.getLength(); i++) {
            Node node = nl.item(i);
            if (node instanceof Element) {
                Element ele = (Element) node;
                    parseElement(ele, delegate);
                    
            }
        }
    }

    private void parseElement(Element ele, BeanDefinitionParserDelegate delegate) {
        if (delegate.nodeNameEquals(ele, BEAN_ELEMENT)) {
            processBeanDefinition(ele, delegate);
        }
    }

    private void processBeanDefinition(Element ele, BeanDefinitionParserDelegate delegate) {
        BeanDefinitionHolder bdHolder= delegate.parseBeanDefinitionElement(ele);
        registerBeanDefinition(bdHolder,beanDefinitionReader.getRegistry());
    }

    private void registerBeanDefinition(BeanDefinitionHolder bdHolder, BeanDefinitionRegistry registry) {
        String beanName = bdHolder.getBeanName();
        registry.registerBeanDefinition(beanName,bdHolder.getBeanDefinition());
//        注册BeanDefinition和名称的映射关系
        Class c=bdHolder.getBeanDefinition().getBeanClass();
//        注册名称和类别的映射关系map
        registry.setBeanDefintionByType(beanName,c);

    }
}
