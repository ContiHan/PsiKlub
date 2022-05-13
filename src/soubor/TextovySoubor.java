package soubor;

import model.Pes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class TextovySoubor implements DataManager {
    private static final String ODD = ";";

    @Override
    public void uloz(String jmenoSouboru, List<Pes> psi) throws Exception {
        PrintWriter out = new PrintWriter(new FileWriter(jmenoSouboru));
        for (Pes pes : psi) {
            out.println(pes.getJmeno() + ODD + pes.getVek() + ODD + pes.getBarva() + ODD + pes.getCena());
        }
        out.close();
    }

    @Override
    public List<Pes> nacti(String jmenoSouboru) throws Exception {
        BufferedReader in = new BufferedReader(new FileReader(jmenoSouboru));
        List<Pes> psi = new ArrayList<>();
        String radek;
        StringTokenizer token;

        while ((radek = in.readLine()) != null) {
            token = new StringTokenizer(radek, ODD);
            try {
                psi.add(new Pes(token.nextToken(),
                        Integer.parseInt(token.nextToken()),
                        token.nextToken(),
                        Double.parseDouble(token.nextToken())));
            } catch (NumberFormatException e) {
                // zahodí záznam
            }
        }

        in.close();
        return psi;
    }
}
