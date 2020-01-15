package smallSpring.documentloader;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class DefaultDocumentLoader implements  DocumentLoader {
    @Override
    public Document loadDocument(InputSource inputSource)  {
        DocumentBuilderFactory factory = createDocumentBuilderFactory();
        try {
            DocumentBuilder builder = createDocumentBuilder(factory);
                return  builder.parse(inputSource);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private DocumentBuilderFactory createDocumentBuilderFactory()  {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        return factory;
    }

    private DocumentBuilder createDocumentBuilder(DocumentBuilderFactory factory) throws ParserConfigurationException {
        return  factory.newDocumentBuilder();
    }
}
