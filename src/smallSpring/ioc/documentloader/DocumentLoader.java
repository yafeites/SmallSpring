package smallSpring.ioc.documentloader;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import javax.xml.parsers.ParserConfigurationException;

public interface DocumentLoader {
    Document loadDocument(
            InputSource inputSource
            ) throws ParserConfigurationException;
}
