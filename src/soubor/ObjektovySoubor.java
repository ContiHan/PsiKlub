package soubor;

import model.Pes;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class ObjektovySoubor implements DataManager {
    @Override
    public void uloz(String jmenoSouboru, List<Pes> psi) throws Exception {
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(jmenoSouboru));
        out.writeObject(psi);
        out.close();
    }

    @Override
    public List<Pes> nacti(String jmenoSouboru) throws Exception {
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(jmenoSouboru));
        ArrayList<Pes> psi = (ArrayList<Pes>) in.readObject();
        in.close();
        return psi;
    }
}
