package by.zhakov.astro.model;

import by.zhakov.astro.locale.Strings;

public class Star extends AstroBody{
	private String type;

	public Star(String name, int weight, String type){
		super(name, weight);
		this.type = type;
	}

	public Star(){
		super();
	}

	public String getType(){
		return type;
	}

	public void setType(String type){
		this.type = type;
	}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Star)) return false;
        if (!super.equals(o)) return false;

        Star star = (Star) o;

        if (!type.equals(star.type)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + type.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return super.toString()+ " " + Strings.type + type;
    }
}