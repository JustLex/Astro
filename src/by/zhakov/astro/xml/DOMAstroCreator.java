package by.zhakov.astro.xml;

import by.zhakov.astro.containers.StarSystem;
import by.zhakov.astro.model.AstroBody;
import by.zhakov.astro.model.Star;
import by.zhakov.astro.model.planets.GasGiant;
import by.zhakov.astro.model.planets.Planet;
import by.zhakov.astro.model.planets.terrestrial.HabitablePlanet;
import by.zhakov.astro.model.planets.terrestrial.TerrestrialPlanet;
import by.zhakov.astro.model.satellites.Satellite;
import by.zhakov.astro.model.satellites.artificial.ArtificialSatellite;
import by.zhakov.astro.model.satellites.artificial.IonCannon;
import by.zhakov.astro.model.satellites.artificial.SpaceStation;
import by.zhakov.astro.bl.SystemAnalyzer;
import by.zhakov.astro.util.SystemsMap;
import org.apache.log4j.Logger;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.util.List;
import java.util.Map;

/**
 * This class contains method that returns XML representation of
 * SystemsMap Class as Document
 */
public class DOMAstroCreator {
    private static Logger log = Logger.getRootLogger();
/**
 * This method returns XML representation of
 * SystemsMap Class as Document
 * @param map SystemMap, which will be represented in XML
 * @return  Document, that contains XML representation of SystemMap
 * @throws ParserConfigurationException
*/
    public Document systemsMapToXML(SystemsMap map) throws ParserConfigurationException {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

        Document doc = docBuilder.newDocument();

        Element rootElem = doc.createElement("systems");
        doc.appendChild(rootElem);

        for(Map.Entry<String, StarSystem> entry : map.entrySet()) {
            String name = entry.getKey();
            StarSystem starSystem = entry.getValue();

            rootElem.appendChild(createSystemNode(doc, name, starSystem));
        }

        return doc;
    }

    private Element createSystemNode(Document doc, String name, StarSystem starSystem){
        Element system = doc.createElement(name.replace(' ', '_'));

        system.appendChild(doc.createElement("suns"));
        system.appendChild(doc.createElement("planets"));

        for(AstroBody body : starSystem.getBodiesList()){
            appendElement(doc, system, body);
            log.debug(body);
        }

        int count = SystemAnalyzer.getCount(starSystem);
        int weight = SystemAnalyzer.getWeight(starSystem);

        appendAttribute(system, doc, "bodies_count", count + "");
        appendAttribute(system, doc, "weight", weight + "");

        return system;
    }
    
    private void appendElement(Document doc, Element parent, AstroBody body){
        Element element = null;
        if (body instanceof Star){
            element = createStarElement(doc, (Star) body);
            parent = (Element)parent.getElementsByTagName("suns").item(0);
        }  else if (body instanceof Planet) {
            element = createPlanetElement(doc, (Planet) body);
            parent = (Element)parent.getElementsByTagName("planets").item(0);
        }
        appendAttribute(element, doc, "name", body.getName());
        appendAttribute(element, doc, "weight", body.getWeight() + "");

        parent.appendChild(element);
    }
    
    private Element createStarElement(Document doc, Star star){
        Element newStar = doc.createElement("star");
        appendAttribute(newStar, doc, "type", star.getType());
        return newStar;
    }

    private Element createPlanetElement(Document doc, Planet planet){
        Element element = null;
        if (planet instanceof TerrestrialPlanet){
            element = createTerrestrialElement(doc, (TerrestrialPlanet) planet);
        } else if (planet instanceof GasGiant){
            element = createGasGiantElement(doc, (GasGiant) planet);
        }

        if (planet.getSatellites() != null){
            Element satellites = doc.createElement("satellites");
            appendSatellites(doc, satellites, planet.getSatellites());
            element.appendChild(satellites);
        }

        return element;
    }

    private void appendSatellites(Document doc, Element parent, List<Satellite> satellites){
        for (Satellite satellite : satellites){
            Element satelliteElement;
            if (satellite instanceof ArtificialSatellite){
                satelliteElement = createArtificialElement(doc, (ArtificialSatellite) satellite);
            } else {
                satelliteElement = doc.createElement("satellite");
            }

            appendAttribute(satelliteElement, doc, "name", satellite.getName());
            appendAttribute(satelliteElement, doc, "weight", satellite.getWeight() + "");

            appendAttribute(satelliteElement, doc, "period", satellite.getRotationPeriod() + "");
            parent.appendChild(satelliteElement);
        }
    }

    private Element createArtificialElement(Document doc, ArtificialSatellite artificial){
        Element newArtificial;
        if (artificial instanceof IonCannon){
            newArtificial = createIonCannonElement(doc, (IonCannon) artificial);
        } else if (artificial instanceof SpaceStation){
            newArtificial = createStationElement(doc, (SpaceStation) artificial);
        } else {
            newArtificial = doc.createElement("artificial");
        }

        appendAttribute(newArtificial, doc, "year", artificial.getYear() + "");
        return newArtificial;
    }

    private Element createIonCannonElement(Document doc, IonCannon cannon){
        Element newCannon = doc.createElement("ion_cannon");
        appendAttribute(newCannon, doc, "caliber", cannon.getCaliber() + "");
        return newCannon;
    }

    private Element createStationElement(Document doc, SpaceStation cannon){
        Element newStation = doc.createElement("station");
        appendAttribute(newStation, doc, "capacity", cannon.getCapacity() + "");
        return newStation;
    }

    private Element createTerrestrialElement(Document doc, TerrestrialPlanet terrestrial){
        Element element;
        if (terrestrial instanceof HabitablePlanet){
            element = createHabitableElement(doc, (HabitablePlanet) terrestrial);
        } else {
            element = doc.createElement("terrestrial");
        }
        appendAttribute(element, doc, "temperature", terrestrial.getTemperature() + "");
        return element;
    }

    private Element createHabitableElement(Document doc, HabitablePlanet habitable){
        Element newHabitable = doc.createElement("habitable");
        appendAttribute(newHabitable, doc, "population", habitable.getPopulation() + "");
        return newHabitable;
    }

    private Element createGasGiantElement(Document doc, GasGiant giant){
        Element newGiant = doc.createElement("giant");
        appendAttribute(newGiant, doc, "rings", giant.getRings() + "");
        return newGiant;
    }

    private void appendAttribute(Element e, Document doc, String name, String value){
        Attr attr = doc.createAttribute(name);
        attr.setValue(value);
        e.setAttributeNode(attr);
    }
}
