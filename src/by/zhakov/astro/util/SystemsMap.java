package by.zhakov.astro.util;

import by.zhakov.astro.containers.StarSystem;
import by.zhakov.astro.model.AstroBody;

import java.util.HashMap;

/**
 * This class is the extension for HasMap<String, StarSystem>, which contains
 * overloaded method public StarSystem put(String, AstroBody body)
 */
public class SystemsMap extends HashMap<String, StarSystem> {
    public StarSystem put(String key, AstroBody body) {
        if (this.containsKey(key)){
            this.get(key).getBodiesList().add(body);
            return super.put(key, this.get(key));
        } else {
            StarSystem newSystem = new StarSystem(key);
            newSystem.getBodiesList().add(body);
            super.put(key, newSystem);
            return null;
        }
    }
}
