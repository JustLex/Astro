package by.zhakov.astro.model.planets;

import by.zhakov.astro.locale.Strings;
import by.zhakov.astro.model.AstroBody;
import by.zhakov.astro.model.satellites.Satellite;
import java.util.List;
import java.util.ArrayList;

public abstract class Planet extends AstroBody{
	private List<Satellite> satellites = new ArrayList<>();

	public Planet(String name, int weight, List<Satellite> satellites){
		super(name, weight);
		this.satellites = satellites;
	}

	public Planet(){
		super();
	}

	public List<Satellite> getSatellites(){
		return satellites;
	}

	public void setSatellites(List<Satellite> satellites){
		this.satellites = satellites;
	}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Planet)) return false;
        if (!super.equals(o)) return false;

        Planet planet = (Planet) o;

        if (!satellites.equals(planet.satellites)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + satellites.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return super.toString() + " " + Strings.satellites + satellites.toString();
    }
}

