package by.zhakov.astro.xml;

import com.sun.org.apache.xerces.internal.jaxp.SAXParserFactoryImpl;
import org.apache.log4j.Logger;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;

public class XMLController{
    private static Logger log = Logger.getRootLogger();
    /**
     * This method parses xml file from source, restructures it and writes to the
     * destination path
     * @param source input path
     * @param destination output path
     */
	public static void restructureXML(String source, String destination){
        String pathSAX = destination.replace(".", "SAX.");
        String pathStAX = destination.replace(".", "StAX.");
        String pathDOM = destination.replace(".", "DOM.");
        SAXAstroParser astroParser = new SAXAstroParser();
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            parser.parse(new File(source), astroParser);
        } catch (Exception e){
            log.error("SAX exception", e);
        }

        StAXAstroParser stax = new StAXAstroParser();

        DOMAstroParser parser = new DOMAstroParser();
		XMLAstroWriter writer = new XMLAstroWriter();
		try{
            writer.writeSystems(parser.getSystems(source), pathSAX);
			writer.writeSystems(stax.getSystems(source), pathStAX);
            writer.writeSystems(parser.getSystems(source), pathDOM);
            log.debug("restructuring ended");
		} catch (Exception e) {
            log.error("exception while restructuring", e);
		}
	}
}