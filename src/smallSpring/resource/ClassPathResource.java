package smallSpring.resource;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class ClassPathResource implements  Resource{
    private final String path;

    private ClassLoader classLoader;

    public ClassPathResource(String path, ClassLoader classLoader) {
        this.path = path;
        this.classLoader = classLoader!=null? classLoader:Thread.currentThread().getContextClassLoader();
    }

    @Override
    public InputStream getInputStream() throws IOException {
        InputStream is;
        is=this.classLoader.getResourceAsStream(this.path);
        if(is==null)
        {
            throw  new FileNotFoundException("file not found");
        }
        return is;
    }
}
