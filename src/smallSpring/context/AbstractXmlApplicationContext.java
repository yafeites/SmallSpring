package smallSpring.context;

import smallSpring.beandefinitionreader.XmlBeanDefinitionReader;
import smallSpring.factory.ConfigurableListableBeanFactory;
import smallSpring.factory.DefaultListableBeanFactory;

public class AbstractXmlApplicationContext extends AbstractRefreshableApplicationContext {
    @Override
    protected void loadBeanDefinitions(DefaultListableBeanFactory beanFactory) {
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        loadBeanDefinitions(beanDefinitionReader);
    }

    private void loadBeanDefinitions(XmlBeanDefinitionReader beanDefinitionReader) {
            String configLocation=getConfigLocation();
            beanDefinitionReader.loadBeanDefinitions(configLocation);
    }


}
