package by.zhakov.astro.model;

import by.zhakov.astro.locale.Strings;

public abstract class AstroBody{
	private String name;
	private int weight;

	public AstroBody(String name, int weight){
		this.name = name;
		this.weight = weight;
	}

	public AstroBody(){
		super();
	}

	public String getName(){
		return name;
	}

	public int getWeight(){
		return weight;
	}

	public void setWeight(int weight){
		this.weight = weight;
	}

	public void setName(String name){
		this.name = name;
	}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AstroBody)) return false;

        AstroBody astroBody = (AstroBody) o;

        if (weight != astroBody.weight) return false;
        if (name != null ? !name.equals(astroBody.name) : astroBody.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + weight;
        return result;
    }

    @Override
        public String toString(){
            return Strings.name + name + " " + Strings.weight + weight;
        }
}