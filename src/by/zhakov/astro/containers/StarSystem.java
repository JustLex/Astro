package by.zhakov.astro.containers;

import by.zhakov.astro.model.AstroBody;

import java.util.List;
import java.util.ArrayList;

/**
 * This class wraps List<AstroBosy> which represents list of astronomical objects within system.
 */
public class StarSystem{
    private String name;
    private List<AstroBody> bodiesList = new ArrayList<>();

    public StarSystem(String name) {
        this.name = name;
    }

    public StarSystem() {
    }

    public String getName() {
        return name;
    }

    public List<AstroBody> getBodiesList() {
        return bodiesList;
    }
    
    public void addBody(AstroBody body){
        bodiesList.add(body);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StarSystem)) return false;

        StarSystem that = (StarSystem) o;

        if (!bodiesList.equals(that.bodiesList)) return false;
        return name.equals(that.name);

    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + bodiesList.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Name: " + name + " Bodies: " + bodiesList.toString();
    }
}
