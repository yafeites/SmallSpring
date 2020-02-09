package smallSpring.beans.delegate;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import smallSpring.beandefiniton.AbstractBeanDefinition;
import smallSpring.beandefiniton.RootBeanDefinition;
import smallSpring.beans.beandefinitionholder.BeanDefinitionHolder;
import smallSpring.beans.propertyvalue.PropertyValue;
import smallSpring.beans.springtype.Impl.RuntimeBeanReference;
import smallSpring.beans.springtype.Impl.TypedStringValue;

import javax.management.modelmbean.XMLParseException;

public class BeanDefinitionParserDelegate {

    private static final String ID_ATTRIBUTE ="id" ;
    private static final String NAME_ATTRIBUTE="name";
    private static final String CLASS_ATTRIBUTE ="class" ;
    private static final String SCOPE_ATTRIBUTE ="scope";
    private static final String AUTOWIRE_ATTRIBUTE ="autowire";
    private static final String AUTOWIRE_BY_NAME_VALUE = "byName";
    private static final String AUTOWIRE_BY_TYPE_VALUE ="byType";
    public static final String PROPERTY_ELEMENT = "property";
    private static final String REF_ATTRIBUTE ="ref";
    private static final String VALUE_ATTRIBUTE ="value";

    public boolean nodeNameEquals(Node node, String name) {
        return node.getNodeName()==name;
    }

    public BeanDefinitionHolder parseBeanDefinitionElement(Element ele) {
        String id= ele.getAttribute(ID_ATTRIBUTE);
        String beanName=id;

        try {
            RootBeanDefinition beanDefiniton=parseBeanDefinitionElement(ele,beanName);
            return  new BeanDefinitionHolder(beanDefiniton,beanName);
        } catch (XMLParseException e) {
            e.getCause();
        }
       return null;
    }

    private RootBeanDefinition parseBeanDefinitionElement(Element ele, String beanName)throws XMLParseException{
        if(!ele.hasAttribute(CLASS_ATTRIBUTE))
        {
            throw new XMLParseException("not found bean's class");
        }
        String className=ele.getAttribute(CLASS_ATTRIBUTE);
        RootBeanDefinition bd = createBeanDefinition(className);
//        解析属性
        parseBeanDefinitionAttributes(ele, beanName,bd);
//        解析参数元素
        parsePropertyElements(ele, bd);
        return bd;
    }

    private void parsePropertyElements(Element ele, AbstractBeanDefinition bd) {
        NodeList nodeList =ele.getChildNodes();
        for(int i=0;i<nodeList.getLength();i++)
        {
            Node node=nodeList.item(i);
            if(nodeNameEquals(node,PROPERTY_ELEMENT))
            {
                parsePropertyElement((Element) node, bd);
            }
        }
    }

    private void parsePropertyElement(Element ele, AbstractBeanDefinition bd) {
        String propertyName=ele.getAttribute(NAME_ATTRIBUTE);
        Object val = parsePropertyValue(ele, bd, propertyName);
        PropertyValue pv = new PropertyValue(propertyName, val);
        bd.getPropertyValues().add(propertyName,val);

    }

    private Object parsePropertyValue(Element ele, AbstractBeanDefinition bd, String propertyName) {
        boolean hasRefAttribute = ele.hasAttribute(REF_ATTRIBUTE);
        boolean hasValueAttribute = ele.hasAttribute(VALUE_ATTRIBUTE);

        if (hasRefAttribute) {
            String refName = ele.getAttribute(REF_ATTRIBUTE);
            RuntimeBeanReference ref = new RuntimeBeanReference(refName);
            return ref;
        }
        else if (hasValueAttribute) {
            TypedStringValue valueHolder = new TypedStringValue(ele.getAttribute(VALUE_ATTRIBUTE));
            return  valueHolder;
        }
        else  return null;
    }

    private void parseBeanDefinitionAttributes(Element ele, String beanName, AbstractBeanDefinition bd) {
             if (ele.hasAttribute(SCOPE_ATTRIBUTE)) {
            bd.setScope(ele.getAttribute(SCOPE_ATTRIBUTE));
        }
             String autowire=ele.getAttribute(AUTOWIRE_ATTRIBUTE);
                 bd.setAutowireMode(getAutowireMode(autowire));
    }

    private int getAutowireMode(String att) {
        int autowire = AbstractBeanDefinition.AUTOWIRE_NO;
        if (AUTOWIRE_BY_NAME_VALUE.equals(att)) {
            autowire = AbstractBeanDefinition.AUTOWIRE_BY_NAME;
        }
        else if (AUTOWIRE_BY_TYPE_VALUE.equals(att)) {
            autowire = AbstractBeanDefinition.AUTOWIRE_BY_TYPE;
        }
        return autowire;
    }

    private RootBeanDefinition createBeanDefinition(String className) {
        RootBeanDefinition beanDefiniton=new RootBeanDefinition();
        beanDefiniton.setBeanClassName(className);
        return  beanDefiniton;
    }
}
