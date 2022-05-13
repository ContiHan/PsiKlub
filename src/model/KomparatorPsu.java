package model;

import java.util.Comparator;

public class KomparatorPsu implements Comparator<Pes> {
    public static final int DLE_JMENA = 0;
    public static final int DLE_VEKU = 1;
    public static final int DLE_BARVY = 2;
    public static final int DLE_CENY = 3;

    private int typ;

    public KomparatorPsu(int typ) {
        this.typ = typ;
    }

    @Override
    public int compare(Pes pes1, Pes pes2) {
        switch (typ) {
            case DLE_JMENA:
                return pes1.getJmeno().compareToIgnoreCase(pes2.getJmeno());
            case DLE_VEKU:
                return pes1.getVek() - pes2.getVek();
            case DLE_BARVY:
                return pes1.getBarva().compareToIgnoreCase(pes2.getBarva());
            case DLE_CENY:
                return (int) (pes1.getCena() - pes2.getCena());
        }

        return 0;
    }
}
