package by.zhakov.astro.bl;

import by.zhakov.astro.containers.StarSystem;
import by.zhakov.astro.model.AstroBody;
import by.zhakov.astro.model.planets.Planet;
import by.zhakov.astro.model.satellites.Satellite;

/**
 *
 * @author student
 */
public class SystemAnalyzer {
    public static int getWeight(StarSystem system){
        int weight = 0;
        for (AstroBody body : system.getBodiesList()) {
            if (body instanceof Planet){
                Planet planet = (Planet) body;
                for (Satellite sat : planet.getSatellites()) {
                    weight += sat.getWeight();
                }
            }
            weight += body.getWeight();
        }
        return weight;
    }
    
    public static int getCount(StarSystem system){
        int count = 0;
        for (AstroBody body : system.getBodiesList()) {
            if (body instanceof Planet){
                Planet planet = (Planet) body;
                count += planet.getSatellites().size();
            }
        }
        count += system.getBodiesList().size();
        return count;
    }
}
