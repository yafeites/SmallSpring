package smallSpring.context;

import smallSpring.factory.ConfigurableListableBeanFactory;
import smallSpring.resource.FileSystemResource;
import smallSpring.resource.Resource;
import smallSpring.test.Boss;
import smallSpring.test.Person;
import java.nio.file.FileSystem;

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
