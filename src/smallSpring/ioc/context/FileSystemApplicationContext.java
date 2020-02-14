package smallSpring.ioc.context;

import smallSpring.ioc.factory.ConfigurableListableBeanFactory;
import smallSpring.ioc.resource.FileSystemResource;
import smallSpring.ioc.resource.Resource;
import smallSpring.test.Boss;

public class FileSystemApplicationContext extends AbstractXmlApplicationContext {
    public static void main(String[] args) {
        FileSystemApplicationContext applicationContext=new FileSystemApplicationContext("classpath:application.xml");
        Boss boss=(Boss)applicationContext.getBean("boss");
        boss.say();

    }
    @Override
    protected Resource getResourceByPath(String path) {
        if (path != null && path.startsWith("/")) {
            path = path.substring(1);
        }
        return new FileSystemResource(path);
    }
    public FileSystemApplicationContext(String configLocations)
    {
        setConfigLocations(configLocations);
        refresh();
    }
}
