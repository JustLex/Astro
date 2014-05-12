package by.zhakov.astro.xml;

import java.io.File;

import by.zhakov.astro.util.SystemsMap;
import org.apache.log4j.Logger;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

class XMLAstroWriter{
    private static Logger log = Logger.getRootLogger();

	public void writeSystems(SystemsMap systems, String output){
        try{
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource dom = new DOMSource(new DOMAstroCreator().systemsMapToXML(systems));

            StreamResult result = new StreamResult(new File(output));
            transformer.transform(dom, result);
        } catch (ParserConfigurationException e){
            log.error("exception while constructing XML", e);
        } catch (TransformerException e1){
            log.error("exception while transforming XML", e1);
        }
	}

}