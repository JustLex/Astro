/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.zhakov.astro.model.satellites.artificial;

import by.zhakov.astro.locale.Strings;
import by.zhakov.astro.model.satellites.Satellite;

/**
 *
 * @author student
 */
public class ArtificialSatellite extends Satellite{
    private short year;

    public ArtificialSatellite(short year, String name, int weight, int rotationPeriod) {
        super(name, weight, rotationPeriod);
        this.year = year;
    }

    public short getYear() {
        return year;
    }

    public void setYear(short year) {
        this.year = year;
    }

    public ArtificialSatellite() {
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + this.year;
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
        
        final ArtificialSatellite other = (ArtificialSatellite) obj;
        if (this.year != other.year) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return super.toString() + " " + Strings.year + year;
    }
}
