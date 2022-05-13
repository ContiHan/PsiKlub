package model;

import java.io.Serializable;
import java.text.NumberFormat;

public class Pes implements Serializable {
    private String jmeno;
    private int vek;
    private String barva;
    private double cena;

    public Pes(String jmeno, int vek, String barva, double cena) {
        this.jmeno = jmeno;
        this.vek = vek;
        this.barva = barva;
        this.cena = cena;
    }

    public String getJmeno() {
        return jmeno;
    }

    public void setJmeno(String jmeno) {
        this.jmeno = jmeno;
    }

    public int getVek() {
        return vek;
    }

    public void setVek(int vek) {
        this.vek = vek;
    }

    public String getBarva() {
        return barva;
    }

    public void setBarva(String barva) {
        this.barva = barva;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    @Override
    public String toString() {
        NumberFormat menovyFormat = NumberFormat.getCurrencyInstance();
        return String.format("Jméno: %s, Věk: %s, Barva: %s, Cena: %s", jmeno, vek, barva, menovyFormat.format(cena));
    }
}
