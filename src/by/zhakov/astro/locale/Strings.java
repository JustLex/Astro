package by.zhakov.astro.locale;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by Aleksei on 07.04.2014.
 */
public class Strings {
    static{
        resources = ResourceBundle.getBundle("by.zhakov.astro.locale.model", Locale.getDefault());

    }
    private static ResourceBundle resources;
    public static String weight = resources.getString("AstroBody.weight");
    public static String name = resources.getString("AstroBody.name");
    public static String type = resources.getString("Star.type");
    public static String satellites = resources.getString("Planet.satellites");
    public static String rings = resources.getString("GasGiant.rings");
    public static String temperature = resources.getString("Terrestrial.temperature");
    public static String population = resources.getString("Habitable.population");
    public static String rotationPeriod = resources.getString("Satellite.rotationPeriod");
    public static String year = resources.getString("ArtificialSatellite.year");
    public static String capacity = resources.getString("SpaceStation.capacity");
    public static String caliber = resources.getString("IonCannon.caliber");
}
