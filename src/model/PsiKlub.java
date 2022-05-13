package model;

import soubor.DataManager;
import soubor.ObjektovySoubor;

import java.util.*;
import java.util.function.ToDoubleFunction;
import java.util.stream.Collectors;

public class PsiKlub {
    private static final String SOUBOR = "psi.obj";
    private List<Pes> psi;
    private DataManager dataManager;

    public PsiKlub() {
        psi = new ArrayList<>();
        dataManager = new ObjektovySoubor();
    }

    public List<Pes> getPsy() {
        return psi;
    }

    public void setPsy(List<Pes> psi) {
        this.psi = psi;
    }

    public void pridejPsa(Pes pes) {
        if (pes == null) {
            throw new IllegalArgumentException("Pes je null");
        }
        psi.add(pes);
    }

    public void pridejPsa(String jmeno, int vek, String barva, double cena) {
        if (jmeno.isEmpty() || vek < 0 || barva.isEmpty() || cena < 0) {
            throw new IllegalArgumentException("Chybně vyplněný pes");
        }
        pridejPsa(new Pes(jmeno, vek, barva, cena));
    }

    public void seradPsy(int typ) {
        psi.sort(new KomparatorPsu(typ));
    }

    public List<Pes> hledejPsy(String hledanyString) {
        /*List<Pes> nalezene = new ArrayList<>();
        for (Pes pes : psi) {
            if (pes.getJmeno().toUpperCase().contains(hledanyString.toUpperCase())) {
                nalezene.add(pes);
            }
        }
        return nalezene;*/
        return psi.stream().filter(pes -> pes.getJmeno().toUpperCase().contains(hledanyString.toUpperCase())).toList();
    }

    public String getPsyJakoString() {
        /*String psiJakoString = "";
        for (Pes pes : psi) {
            psiJakoString += pes.toString() + "\n";
        }
        return psiJakoString;*/
        return psi.stream().map(pes -> pes.toString() + "\n").collect(Collectors.joining());
    }

    public void upravPsa(String puvodniJmeno, String noveJmeno, int novyVek, String novaBarva, double novaCena) {
        /*if (!psi.isEmpty()) {
            for (Pes pes : psi) {
                if (pes.getJmeno().equalsIgnoreCase(puvodniJmeno)) {
                    pes.setJmeno(noveJmeno);
                    pes.setVek(novyVek);
                    pes.setBarva(novaBarva);
                    pes.setCena(novaCena);
                }
            }
        }*/
        psi.stream().filter(pes -> pes.getJmeno().equalsIgnoreCase(puvodniJmeno)).forEach(pes -> {
            pes.setJmeno(noveJmeno);
            pes.setVek(novyVek);
            pes.setBarva(novaBarva);
            pes.setCena(novaCena);
        });
    }

    public boolean existujePes(String jmeno) {
        /*boolean existuje = false;
        for (Pes pes : psi) {
            if (pes.getJmeno().equalsIgnoreCase(jmeno)) {
                return true;
            }
        }
        return existuje;*/
        return psi.stream().anyMatch(pes -> pes.getJmeno().equalsIgnoreCase(jmeno));
    }

    public void vymazPsa(String jmeno) {
        /*if (!psi.isEmpty()) {
            for (Iterator<Pes> pes = psi.iterator(); pes.hasNext(); ) {
                if (pes.next().getJmeno().equalsIgnoreCase(jmeno)) {
                    pes.remove();
                }
            }
        }*/
        psi.removeIf(pes -> pes.getJmeno().equalsIgnoreCase(jmeno));
    }

    public void uloz() throws Exception {
        dataManager.uloz(SOUBOR, psi);
    }

    public void nacti() throws Exception {
        psi = dataManager.nacti(SOUBOR);
    }

    public double getPrumernyVek() {
        return spocitejPrumer(Pes::getVek);
    }

    public double getPrumernaCena() {
        return spocitejPrumer(Pes::getCena);
    }

    private double spocitejPrumer(ToDoubleFunction<Pes> getr) {
        return psi.stream().mapToDouble(getr).average().orElse(0);
    }
}
