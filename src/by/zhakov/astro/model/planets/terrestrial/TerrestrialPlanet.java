package by.zhakov.astro.model.planets.terrestrial;

import by.zhakov.astro.locale.Strings;
import by.zhakov.astro.model.planets.Planet;
import by.zhakov.astro.model.satellites.Satellite;
import java.util.List;

public class TerrestrialPlanet extends Planet{
	private int temperature;

	public TerrestrialPlanet(String name, int weight, List<Satellite> satellites, int temperature){
		super(name, weight, satellites);
		this.temperature = temperature;
	}

	public TerrestrialPlanet(){
		super();
	}

	public int getTemperature(){
		return temperature;
	}

	public void setTemperature(int temperature){
		this.temperature = temperature;
	}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TerrestrialPlanet)) return false;
        if (!super.equals(o)) return false;

        TerrestrialPlanet that = (TerrestrialPlanet) o;

        if (temperature != that.temperature) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + temperature;
        return result;
    }

    @Override
    public String toString() {
        return super.toString() + " " + Strings.temperature + temperature;
    }
}