package app;

import model.KomparatorPsu;
import model.Pes;
import model.PsiKlub;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

public class Okno extends JFrame {
    private JPanel pnlMain;
    private JTextField tfPridatJmeno;
    private JTextField tfPridatVek;
    private JButton btnPridat;
    private JLabel lbPridatJmeno;
    private JLabel blPridatVek;
    private JTextField tfPridatBarva;
    private JTextField tfPridatCena;
    private JLabel lbPridatBarva;
    private JLabel lbPridatCena;
    private JTextArea taVypis;
    private JScrollPane spVypis;
    private JTextField tfUpravitJmenoPuvodni;
    private JTextField tfUpravitJmenoNove;
    private JTextField tfUpravitVekNovy;
    private JTextField tfUpravitBarvaNova;
    private JTextField tfUpravitCenaNova;
    private JButton btnUpravit;
    private JLabel lbUpravitJmenoPuvodni;
    private JLabel lbUpravitJmenoNove;
    private JLabel lbUpravitVekNovy;
    private JLabel lbUpravitBarvaNova;
    private JLabel lbUpravitCenaNova;
    private JTextField tfSmazatJmeno;
    private JButton btnSmazat;
    private JLabel lbSmazatJmeno;
    private JTextField tfVyhledatJmeno;
    private JButton btnVyhledat;
    private JLabel lbVyhledatJmeno;
    private JRadioButton rbSeraditJmeno;
    private JRadioButton rbSeraditVek;
    private JButton btnSeradit;
    private JLabel lbSeradit;
    private JRadioButton rbSeraditBarva;
    private JRadioButton rbSeraditCena;
    private JButton btnNacist;
    private JButton btnUlozit;
    private JButton btnVypsat;
    private JPanel pnlPridat;
    private JPanel pnlUpravit;
    private JPanel pnlSmazat;
    private JPanel pnlVyhledat;
    private JPanel pnlSeradit;
    private JPanel pnlNacistUlozit;
    private JPanel pnlVypis;

    private PsiKlub psiKlub;

    public Okno() {
        psiKlub = new PsiKlub();

        inicializujUI();
        naslouchejTlacitka();
/*        tfSmazatJmeno.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                List<Pes> nalezene = psiKlub.hledejPsy(tfSmazatJmeno.getText());
                if (!nalezene.isEmpty()) {
                    taVypis.setText("");
                    for (Pes pes : nalezene) {
                        taVypis.append(pes.getJmeno() + "\n");
                    }
                } else {
                    taVypis.setText("");
                }
            }
        });*/
    }

    private void inicializujUI() {
        setContentPane(pnlMain);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000, 575);
        setMinimumSize(new Dimension(800, 575));
        setTitle("Ps?? klub");
        setVisible(true);
    }

    private void naslouchejTlacitka() {
        btnVypsat.addActionListener(actionEvent -> aktualizujVypis());
        btnPridat.addActionListener(actionEvent -> pridejPsa());
        btnUpravit.addActionListener(actionEvent -> upravPsa());
        btnSmazat.addActionListener(actionEvent -> vymazPsa());
        btnVyhledat.addActionListener(actionEvent -> hledejPsy());
        btnSeradit.addActionListener(actionEvent -> seradPsy());
        btnNacist.addActionListener(actionEvent -> nactiData());
        btnUlozit.addActionListener(actionEvent -> ulozData());
    }

    private void aktualizujVypis() {
        taVypis.setText(psiKlub.getPsyJakoString());
    }

    private void pridejPsa() {
        if (!tfPridatJmeno.getText().isEmpty() &&
                !tfPridatVek.getText().isEmpty() &&
                !tfPridatBarva.getText().isEmpty() &&
                !tfPridatCena.getText().isEmpty()) {
            try {
                psiKlub.pridejPsa(tfPridatJmeno.getText(),
                        Integer.parseInt(tfPridatVek.getText()),
                        tfPridatBarva.getText(),
                        Double.parseDouble(tfPridatCena.getText()));
                aktualizujVypis();
                JOptionPane.showMessageDialog(this, "Pes " + tfPridatJmeno.getText() + " byl p??id??n", "P??id??n?? psa", JOptionPane.INFORMATION_MESSAGE);
                tfPridatJmeno.setText("");
                tfPridatVek.setText("");
                tfPridatBarva.setText("");
                tfPridatCena.setText("");
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Nebylo zad??no cel?? ????slo u v??ku nebo ????slo u ceny", "P??id??n?? psa", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            if (tfPridatJmeno.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Pr??zdn?? pole jm??na", "P??id??n?? psa", JOptionPane.WARNING_MESSAGE);
                tfPridatJmeno.grabFocus();
            } else if (tfPridatVek.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Pr??zdn?? pole v??ku", "P??id??n?? psa", JOptionPane.WARNING_MESSAGE);
                tfPridatVek.grabFocus();
            } else if (tfPridatBarva.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Pr??zdn?? pole barvy", "P??id??n?? psa", JOptionPane.WARNING_MESSAGE);
                tfPridatBarva.grabFocus();
            } else {
                JOptionPane.showMessageDialog(this, "Pr??zdn?? pole ceny", "P??id??n?? psa", JOptionPane.WARNING_MESSAGE);
                tfPridatCena.grabFocus();
            }
        }
    }

    private void upravPsa() {
        if (!tfUpravitJmenoPuvodni.getText().isEmpty() && psiKlub.existujePes(tfUpravitJmenoPuvodni.getText())) {
            if (!tfUpravitJmenoNove.getText().isEmpty() &&
                    !tfUpravitVekNovy.getText().isEmpty() &&
                    !tfUpravitBarvaNova.getText().isEmpty() &&
                    !tfUpravitCenaNova.getText().isEmpty()) {
                try {
                    psiKlub.upravPsa(tfUpravitJmenoPuvodni.getText(),
                            tfUpravitJmenoNove.getText(),
                            Integer.parseInt(tfUpravitVekNovy.getText()),
                            tfUpravitBarvaNova.getText(),
                            Double.parseDouble(tfUpravitCenaNova.getText()));
                    aktualizujVypis();
                    JOptionPane.showMessageDialog(this, "Pes " + tfUpravitJmenoPuvodni.getText() + " byl upraven", "Upraven?? psa", JOptionPane.INFORMATION_MESSAGE);
                    tfUpravitJmenoPuvodni.setText("");
                    tfUpravitJmenoNove.setText("");
                    tfUpravitVekNovy.setText("");
                    tfUpravitBarvaNova.setText("");
                    tfUpravitCenaNova.setText("");
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this, "Nebylo zad??no cel?? ????slo u nov??ho v??ku nebo ????slo u nov?? ceny", "Upraven?? psa", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                if (tfUpravitJmenoNove.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Pr??zdn?? pole nov??ho jm??na", "Upraven?? psa", JOptionPane.WARNING_MESSAGE);
                    tfUpravitJmenoNove.grabFocus();
                } else if (tfUpravitVekNovy.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Pr??zdn?? pole nov??ho v??ku", "Upraven?? psa", JOptionPane.WARNING_MESSAGE);
                    tfUpravitVekNovy.grabFocus();
                } else if (tfUpravitBarvaNova.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Pr??zdn?? pole nov?? barvy", "Upraven?? psa", JOptionPane.WARNING_MESSAGE);
                    tfUpravitBarvaNova.grabFocus();
                } else {
                    JOptionPane.showMessageDialog(this, "Pr??zdn?? pole p??vodn?? ceny", "Upraven?? psa", JOptionPane.WARNING_MESSAGE);
                    tfUpravitCenaNova.grabFocus();
                }
            }
        } else {
            if (tfUpravitJmenoPuvodni.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Pr??zdn?? pole p??vodn??ho jm??na", "Upraven?? psa", JOptionPane.WARNING_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Pes se zadan??m jm??nem neexistuje", "Upraven?? psa", JOptionPane.WARNING_MESSAGE);
            }
            tfUpravitJmenoPuvodni.grabFocus();
        }
    }

    private void vymazPsa() {
        if (!tfSmazatJmeno.getText().isEmpty() && psiKlub.existujePes(tfSmazatJmeno.getText())) {
            psiKlub.vymazPsa(tfSmazatJmeno.getText());
            aktualizujVypis();
            JOptionPane.showMessageDialog(this, "Pes " + tfSmazatJmeno.getText() + " byl smaz??n", "Smaz??n?? psa", JOptionPane.INFORMATION_MESSAGE);
            tfSmazatJmeno.setText("");
        } else {
            if (tfSmazatJmeno.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Pr??zdn?? pole jm??na", "Smaz??n?? psa", JOptionPane.WARNING_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Pes se zadan??m jm??nem neexistuje", "Smaz??n?? psa", JOptionPane.WARNING_MESSAGE);
            }

        }
    }

    private void hledejPsy() {
        String hledane = tfVyhledatJmeno.getText();
        if (!hledane.isEmpty()) {
            List<Pes> nalezene = psiKlub.hledejPsy(hledane);
            if (nalezene.size() > 0) {
                taVypis.setText("NALEZEN?? PSI\n");
                for (Pes pes : nalezene) {
                    taVypis.append(pes.toString() + "\n");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Nebyl nalezen ????dn?? pes", "Vyhled??n?? ps??", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Pr??zdn?? pole jm??na", "Vyhled??n?? ps??", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void seradPsy() {
        if (rbSeraditJmeno.isSelected()) {
            psiKlub.seradPsy(KomparatorPsu.DLE_JMENA);
            aktualizujVypis();
        } else if (rbSeraditVek.isSelected()) {
            psiKlub.seradPsy(KomparatorPsu.DLE_VEKU);
            aktualizujVypis();
        } else if (rbSeraditBarva.isSelected()) {
            psiKlub.seradPsy(KomparatorPsu.DLE_BARVY);
            aktualizujVypis();
        } else {
            psiKlub.seradPsy(KomparatorPsu.DLE_CENY);
            aktualizujVypis();
        }
    }

    private void nactiData() {
        try {
            psiKlub.nacti();
            JOptionPane.showMessageDialog(this, "Data byla na??tena", "Na??ten?? dat", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Data nebyla na??tena: " + e, "Na??ten?? dat", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void ulozData() {
        try {
            psiKlub.uloz();
            JOptionPane.showMessageDialog(this, "Data byla ulo??ena", "Ulo??en?? dat", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Data nebyla ulo??ena: " + e, "Ulo??en?? dat", JOptionPane.WARNING_MESSAGE);
        }
    }
}
