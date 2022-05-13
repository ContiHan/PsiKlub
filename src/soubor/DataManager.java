package soubor;

import model.Pes;

import java.util.List;

public interface DataManager {
    public void uloz(String jmenoSouboru, List<Pes> psi) throws Exception;

    public List<Pes> nacti(String jmenoSouboru) throws Exception;
}
