package smallSpring.ioc.context;

import smallSpring.ioc.beandefinitionreader.XmlBeanDefinitionReader;
import smallSpring.ioc.factory.ConfigurableListableBeanFactory;
import smallSpring.ioc.factory.DefaultListableBeanFactory;

public class AbstractXmlApplicationContext extends AbstractRefreshableApplicationContext {
    @Override
    protected void loadBeanDefinitions(DefaultListableBeanFactory beanFactory) {
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);

        beanDefinitionReader.setResourceLoader(this);
        loadBeanDefinitions(beanDefinitionReader);
    }

    private void loadBeanDefinitions(XmlBeanDefinitionReader beanDefinitionReader) {
            String configLocation=getConfigLocation();
            beanDefinitionReader.loadBeanDefinitions(configLocation);
    }


}
