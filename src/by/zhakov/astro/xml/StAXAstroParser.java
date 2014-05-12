package by.zhakov.astro.xml;

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
import by.zhakov.astro.util.SystemsMap;
import org.apache.log4j.Logger;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;

public class StAXAstroParser {
    private static Logger log = Logger.getLogger(StAXAstroParser.class);
    private SystemsMap systemsMap = new SystemsMap();
    private SAXAstroParser parser = new SAXAstroParser();
    private String system;
    private String currentAttribute;
    private AstroBody currentBody;
    private Satellite currentSatellite;

    public SystemsMap getSystems(String path){
        try {
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLStreamReader r = factory.createXMLStreamReader(new FileInputStream(new File(path)));
            try  {
                int event = r.getEventType();
                while (true) {
                    switch (event) {
                        case XMLStreamConstants.START_ELEMENT:
                            startElement(r);
                            break;
                        case XMLStreamConstants.CHARACTERS:
                            characters(r);
                            break;
                        case XMLStreamConstants.END_ELEMENT:
                            endElements(r);
                            break;
                    }

                    if (!r.hasNext())
                        break;

                    event = r.next();
                }
            } finally {
                r.close();
            }
        } catch (Exception e){
            log.error("StAX exception", e);
        }
        return systemsMap;
    }

    private void startElement(XMLStreamReader r){
        system = r.getAttributeValue(0);
        switch (r.getName().toString()){
            case "star" : {initializeElement(new Star());break;}
            case "giant" : {initializeElement(new GasGiant());break;}
            case "terrestrial" : {initializeElement(new TerrestrialPlanet());break;}
            case "habitable" : {initializeElement(new HabitablePlanet());break;}
            case "artificial" : {initializeSatellite(new ArtificialSatellite());break;}
            case "station" : {initializeSatellite(new SpaceStation());break;}
            case "ion_cannon" : {initializeSatellite(new IonCannon());break;}
            case "satellite" : {initializeSatellite(new Satellite());break;}
            default: currentAttribute = r.getName().toString();
        }
    }

    private void characters(XMLStreamReader r){
        String characters = r.getText();
        AstroBody target = currentSatellite;
        if (currentSatellite == null){
            target = currentBody;
        }
        switch (currentAttribute){
            case "name" : {target.setName(characters);; break;}
            case "weight" : {target.setWeight(Integer.parseInt(characters));break;}
            case "type" : {((Star)target).setType(characters);break;}
            case "period" : {((Satellite)target).setRotationPeriod(Integer.parseInt(characters));break;}
            case "rings" : {((GasGiant)target).setRings(Integer.parseInt(characters));break;}
            case "temperature" : {((TerrestrialPlanet)target).setTemperature(Integer.parseInt(characters));break;}
            case "population" : {((HabitablePlanet)target).setPopulation(Integer.parseInt(characters));break;}
            case "year" : {((ArtificialSatellite)target).setYear(Short.parseShort(characters));break;}
            case "capacity" : {((SpaceStation)target).setCapacity(Integer.parseInt(characters));break;}
            case "caliber" : {((IonCannon)target).setCaliber(Integer.parseInt(characters));}
        }
        currentAttribute = "";
    }

    public void endElements(XMLStreamReader r){
        switch (r.getName().toString()) {
            case "name" : {}
            case "weight" : {}
            case "type" : {}
            case "period" : {}
            case "rings" : {}
            case "temperature" : {}
            case "population" : {}
            case "year" : {}
            case "satellites" : {}
            case "capacity" : {}
            case "caliber" : {break;}
            default: {clear();}
        }
    }

    private void clear(){
        if (currentSatellite == null){
            currentBody = null;
        } else {
            currentSatellite = null;
        }
    }

    private void initializeSatellite(Satellite satellite){
        ((Planet)currentBody).getSatellites().add(satellite);
        currentSatellite = satellite;
    }

    private void initializeElement(AstroBody body){
        currentBody = body;
        systemsMap.put(system, body);
    }
}
