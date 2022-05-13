package app;

import model.KomparatorPsu;
import model.Pes;
import model.PsiKlub;

import java.util.List;
import java.util.Scanner;

public class Konzole {
    private static Scanner input = new Scanner(System.in);
    private PsiKlub klub;

    public Konzole() {
        klub = new PsiKlub();

        nactiMenu();
    }

    private void nactiMenu() {
        char volba;

        do {
            System.out.println("==========PSÍ KLUB==========");
            System.out.println("p - přidat psa");
            System.out.println("e - editovat psa");
            System.out.println("s - smazat psa");
            System.out.println("z - zobrazit psa");
            System.out.println("r - radit psy");
            System.out.println("v - vyhledat psy");
            System.out.println("u - ulozit psy");
            System.out.println("n - nacist psy");
            System.out.println("k - konec programu");
            System.out.print("Zvol akci: ");
            volba = input.next().toLowerCase().charAt(0);

            switch (volba) {
                case 'p':
                    System.out.print("Zadej jméno: ");
                    String jmeno = input.next();

                    int vek = getIntegerOdUzivatele("Zadej věk: ");

                    System.out.print("Zadej barvu: ");
                    String barva = input.next();

                    double cena = getDoubleOdUzivatele("Zadej cenu: ");

                    klub.pridejPsa(jmeno, vek, barva, cena);
                    System.out.println("Pes " + jmeno + " byl přidán");
                    System.out.println();
                    break;
                case 'e':
                    System.out.print("Zadej původní jméno: ");
                    String puvodniJmeno = input.next();
                    if (klub.existujePes(puvodniJmeno)) {
                        System.out.print("Zadej nové jméno: ");
                        String noveJmeno = input.next();

                        int novyVek = getIntegerOdUzivatele("Zadej nový věk: ");

                        System.out.print("Zadej novou barvu: ");
                        String novaBarva = input.next();

                        double novaCena = getDoubleOdUzivatele("Zadej novou cenu: ");

                        klub.upravPsa(puvodniJmeno, noveJmeno, novyVek, novaBarva, novaCena);
                        System.out.println("Pes " + puvodniJmeno + " byl upraven");
                    } else {
                        System.out.println("Pes se zadaným jménem neexistuje");
                    }
                    System.out.println();
                    break;
                case 's':
                    System.out.print("Zadej jméno psa, kterého chceš vymazat: ");
                    String smazatJmeno = input.next();
                    if (klub.existujePes(smazatJmeno)) {
                        klub.vymazPsa(smazatJmeno);
                        System.out.println("Pes " + smazatJmeno + " byl vymazán");
                    } else {
                        System.out.println("Pes se zadaným jménem neexistuje");
                    }
                    System.out.println();
                    break;
                case 'z':
                    System.out.println(klub.getPsyJakoString());
                    break;
                case 'r':
                    int typ = getIntegerOdUzivatele(
                            KomparatorPsu.DLE_JMENA + " - jméno\n" +
                                    KomparatorPsu.DLE_VEKU + " - věk\n" +
                                    KomparatorPsu.DLE_BARVY + " - barva\n" +
                                    KomparatorPsu.DLE_CENY + " - cena\n" +
                                    "Zadej parametr řazení: ");
                    if (typ >= 0 && typ <= 3) {
                        klub.seradPsy(typ);
                    } else {
                        System.out.println("Zadáno číslo mimo možnost");
                    }
                    System.out.println();
                    break;
                case 'v':
                    System.out.print("Zadej jméno k vyhledání: ");
                    String hledane = input.next();
                    List<Pes> nalezene = klub.hledejPsy(hledane);
                    if (nalezene.size() > 0) {
                        System.out.println("NALEZENÍ PSI");
                        for (Pes pes : nalezene) {
                            System.out.println(pes.toString());
                        }
                    } else {
                        System.out.println("Nebyl nalezen žádný pes");
                    }
                    System.out.println();
                    break;
                case 'u':
                    try {
                        klub.uloz();
                        System.out.println("Data byla uložena");
                    } catch (Exception e) {
                        System.out.println("Data nebyla uložena: " + e);
                    }
                    System.out.println();
                    break;
                case 'n':
                    try {
                        klub.nacti();
                        System.out.println("Data byla načtena");
                    } catch (Exception e) {
                        System.out.println("Data nebyla načtena: " + e);
                    }
                    System.out.println();
                    break;
                default:
                    break;
            }
        } while (volba != 'k');
    }

    private double getDoubleOdUzivatele(String zprava) {
        boolean jeCislo = false;
        double cislo = 0;
        while (!jeCislo) {
            try {
                System.out.print(zprava);
                cislo = Double.parseDouble(input.next());
                jeCislo = true;
            } catch (NumberFormatException e) {
                System.out.println("Nebylo zadáno číslo");
            }
        }
        return cislo;
    }

    private int getIntegerOdUzivatele(String zprava) {
        boolean jeCeleCislo = false;
        int celeCislo = 0;
        while (!jeCeleCislo) {
            try {
                System.out.print(zprava);
                celeCislo = Integer.parseInt(input.next());
                jeCeleCislo = true;
            } catch (NumberFormatException e) {
                System.out.println("Nebylo zadáno celé číslo");
            }
        }
        return celeCislo;
    }
}
