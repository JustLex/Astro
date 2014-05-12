package by.zhakov.astro.model.planets.terrestrial;

import by.zhakov.astro.locale.Strings;
import by.zhakov.astro.model.satellites.Satellite;
import java.util.ArrayList;

public class HabitablePlanet extends TerrestrialPlanet{
	private int population;

	public HabitablePlanet(String name, int weight, ArrayList<Satellite> satellites, int temperature, int population){
		super(name, weight, satellites, temperature);
		this.population = population;
	}

	public HabitablePlanet(){
		super();
	}

	public int getPopulation(){
		return population;
	}

	public void setPopulation(int population){
		this.population = population;
	}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HabitablePlanet)) return false;
        if (!super.equals(o)) return false;

        HabitablePlanet that = (HabitablePlanet) o;

        if (population != that.population) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + population;
        return result;
    }

    @Override
    public String toString() {
        return super.toString()+ " " + Strings.population + population;
    }
}