package by.zhakov.astro.model.satellites;

import by.zhakov.astro.locale.Strings;
import by.zhakov.astro.model.AstroBody;

public class Satellite extends AstroBody{
	private int rotationPeriod;

	public Satellite(String name, int weight, int rotationPeriod){
		super(name, weight);
		this.rotationPeriod = rotationPeriod;
	}

	public Satellite(){
		super();
	}

	public int getRotationPeriod(){
		return rotationPeriod;
	}

	public void setRotationPeriod(int rotationPeriod){
		this.rotationPeriod = rotationPeriod;
	}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Satellite)) return false;
        if (!super.equals(o)) return false;

        Satellite satellite = (Satellite) o;

        if (rotationPeriod != satellite.rotationPeriod) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + rotationPeriod;
        return result;
    }

    @Override
    public String toString() {
        return super.toString() + " " + Strings.rotationPeriod + rotationPeriod;
    }
}