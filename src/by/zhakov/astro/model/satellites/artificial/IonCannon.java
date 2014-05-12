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
public class IonCannon extends ArtificialSatellite{
    private int caliber;

    public int getCaliber() {
        return caliber;
    }

    public void setCaliber(int caliber) {
        this.caliber = caliber;
    }

    public IonCannon(int caliber, short year, String name, int weight, int rotationPeriod) {
        super(year, name, weight, rotationPeriod);
        this.caliber = caliber;
    }

    public IonCannon() {
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + this.caliber;
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
        
        final IonCannon other = (IonCannon) obj;
        if (this.caliber != other.caliber) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return super.toString() + " " + Strings.caliber + caliber;
    }


}
