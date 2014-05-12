/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.zhakov.astro.model.satellites.artificial;

import by.zhakov.astro.locale.Strings;

/**
 *
 * @author student
 */
public class SpaceStation extends ArtificialSatellite {
    private int capacity;

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public SpaceStation(int capacity, short year, String name, int weight, int rotationPeriod) {
        super(year, name, weight, rotationPeriod);
        this.capacity = capacity;
    }

    public SpaceStation() {
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.capacity;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }     
        if (!super.equals(obj)) return false;
        final SpaceStation other = (SpaceStation) obj;
        if (this.capacity != other.capacity) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return super.toString() + " " + Strings.capacity + capacity;
    }
}
