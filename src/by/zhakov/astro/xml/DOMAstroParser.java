package by.zhakov.astro.xml;

import by.zhakov.astro.model.satellites.Satellite;
import by.zhakov.astro.model.satellites.artificial.ArtificialSatellite;
import by.zhakov.astro.model.satellites.artificial.IonCannon;
import by.zhakov.astro.model.satellites.artificial.SpaceStation;
import by.zhakov.astro.util.SystemsMap;
import by.zhakov.astro.model.planets.GasGiant;
import by.zhakov.astro.model.planets.terrestrial.TerrestrialPlanet;
import by.zhakov.astro.model.planets.terrestrial.HabitablePlanet;
import by.zhakov.astro.model.planets.Planet;
import by.zhakov.astro.model.*;

import java.util.ArrayList;
import java.util.List;
import java.io.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

class DOMAstroParser {
	private SystemsMap systemsMap = new SystemsMap();
	private String system;

	public SystemsMap getSystems(String path) throws Exception{
		systemsMap.clear();
		File file = new File(path);
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();

		Document doc = db.parse(file);

		Node root = doc.getDocumentElement();
		root.normalize();

		NodeList children = root.getChildNodes();

		for(int i = 0; i < children.getLength(); i++){
			Node child = children.item(i);
			if (child.getNodeType() == Node.ELEMENT_NODE){
				switch (child.getNodeName()){
					case "star": {parseStar((Element)child); break;}
					case "terrestrial": {parseTerrestrial((Element)child); break;}
					case "giant": {parseGiant((Element)child); break;}
					case "habitable": {parseHabitable((Element)child); break;}
				}
			}
		}
		return systemsMap;
	}

	private void parseBody(Element bodyElement, AstroBody body){
		body.setName(getInnerTagContent(bodyElement, "name"));
		body.setWeight(Integer.parseInt(getInnerTagContent(bodyElement, "weight")));
	}

	private void parseStar(Element starElement){
		system = starElement.getAttribute("system");
		Star star = new Star();
		star.setType(getInnerTagContent(starElement, "type"));

		parseBody(starElement, star);
		systemsMap.put(system, star);
	}

	private void parsePlanet(Element planetElement, Planet planet){
		List<Satellite> satellitesList = new ArrayList<>();
        NodeList satellites = planetElement.getElementsByTagName("satellites");
        if (satellites.getLength() > 0){
            satellites = satellites.item(0).getChildNodes();
            for(int i = 0; i < satellites.getLength(); i++){
                if (satellites.item(i).getNodeType() != Node.ELEMENT_NODE){
                    continue;
                }
                Satellite satellite = null;
                Element satelliteElement = (Element) satellites.item(i);
                if (satelliteElement.getNodeName().equals("satellite")) {
                    satellite = new Satellite();
                    parseSatellite(satelliteElement, satellite);
                } else if (satelliteElement.getNodeName().equals("artificial")){
                    satellite = new ArtificialSatellite();
                    parseArtificial(satelliteElement, (ArtificialSatellite)satellite);
                } else if (satelliteElement.getNodeName().equals("ion_cannon")){
                    satellite = new IonCannon();
                    parseIonCannon(satelliteElement, (IonCannon) satellite);
                } else if (satelliteElement.getNodeName().equals("station")){
                    satellite = new SpaceStation();
                    parseStation(satelliteElement, (SpaceStation) satellite);
                }
                satellitesList.add(satellite);
            }
            planet.setSatellites(satellitesList);
        }
		parseBody(planetElement, planet);
	}

    private void parseStation(Element stationElement, SpaceStation station){
        station.setCapacity(Integer.parseInt(getInnerTagContent(stationElement, "capacity")));

        parseArtificial(stationElement, station);
    }

    private void parseIonCannon(Element cannonElement, IonCannon cannon){
        cannon.setCaliber(Integer.parseInt(getInnerTagContent(cannonElement, "caliber")));

        parseArtificial(cannonElement, cannon);
    }

    private void parseArtificial(Element artificialElement, ArtificialSatellite artificial){
        artificial.setYear(Short.parseShort(getInnerTagContent(artificialElement, "year")));

        parseSatellite(artificialElement, artificial);
    }

	private void parseSatellite(Element satelliteElement, Satellite satellite){
		satellite.setRotationPeriod(Integer.parseInt(getInnerTagContent(satelliteElement, "period")));

		parseBody(satelliteElement, satellite);
	}

	private void parseTerrestrial(Element terrestrialElement){
		system = terrestrialElement.getAttribute("system");
		TerrestrialPlanet terrestrial = new TerrestrialPlanet();

		parseTerrestrial(terrestrialElement, terrestrial);
	}

	private void parseTerrestrial(Element terrestrialElement, TerrestrialPlanet terrestrial){
		terrestrial.setTemperature(Integer.parseInt(getInnerTagContent(terrestrialElement, "temperature")));

		parsePlanet(terrestrialElement, terrestrial);
		systemsMap.put(system, terrestrial);
	}

	private void parseGiant(Element giantElement){
		system = giantElement.getAttribute("system");
		GasGiant giant = new GasGiant();

		giant.setRings(Integer.parseInt(getInnerTagContent(giantElement, "rings")));

		parsePlanet(giantElement, giant);
		systemsMap.put(system, giant);
	}

	private void parseHabitable(Element habitableElement){
		system = habitableElement.getAttribute("system");
		HabitablePlanet habitable = new HabitablePlanet();

		habitable.setPopulation(Integer.parseInt(getInnerTagContent(habitableElement, "population")));

		parseTerrestrial(habitableElement, habitable);
	}

	private String getInnerTagContent(Element el, String tagName){
		return el.getElementsByTagName(tagName).item(0).getTextContent();
	}
}