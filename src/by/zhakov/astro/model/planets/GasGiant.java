package by.zhakov.astro.model.planets;

import by.zhakov.astro.locale.Strings;
import by.zhakov.astro.model.satellites.Satellite;

import java.util.ArrayList;

public class GasGiant extends Planet{
	private int rings;

	public GasGiant(String name, int weight, ArrayList<Satellite> satellites, int rings){
		super(name, weight, satellites);
		this.rings = rings;
	}

	public GasGiant(){
		super();
	}

	public int getRings(){
		return rings;
	}

	public void setRings(int rings){
		this.rings = rings;
	}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GasGiant)) return false;
        if (!super.equals(o)) return false;

        GasGiant gasGiant = (GasGiant) o;

        if (rings != gasGiant.rings) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + rings;
        return result;
    }

    @Override
    public String toString() {
        return super.toString()+ " " + Strings.rings + rings;
    }
}