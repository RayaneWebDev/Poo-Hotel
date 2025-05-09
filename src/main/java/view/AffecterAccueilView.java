package view;

import model.Bagagiste;
import model.Accueil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class AffecterAccueilView extends JPanel {

    private JComboBox<Bagagiste> cbBagagiste;
    private JComboBox<Accueil> cbAccueil;
    private JButton affecterBtn;
    private JTable table;
    private DefaultTableModel model;

    public AffecterAccueilView(List<Bagagiste> bagagistes, List<Accueil> accueils) {
        setLayout(new BorderLayout());

        // Top panel
        JPanel formPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        cbBagagiste = new JComboBox<>(bagagistes.toArray(new Bagagiste[0]));
        cbAccueil = new JComboBox<>(accueils.toArray(new Accueil[0]));
        affecterBtn = new JButton("Affecter");

        formPanel.add(new JLabel("Bagagiste :"));
        formPanel.add(cbBagagiste);
        formPanel.add(new JLabel("Service :"));
        formPanel.add(cbAccueil);
        formPanel.add(new JLabel(""));
        formPanel.add(affecterBtn);

        // Table
        model = new DefaultTableModel(new String[]{"Bagagiste", "Service", "Date"}, 0);
        table = new JTable(model);

        add(formPanel, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);
    }

    public Bagagiste getSelectedBagagiste() {
        return (Bagagiste) cbBagagiste.getSelectedItem();
    }

    public Accueil getSelectedAccueil() {
        return (Accueil) cbAccueil.getSelectedItem();
    }

    public JButton getAffecterBtn() {
        return affecterBtn;
    }

    public void showMessage(String msg) {
        JOptionPane.showMessageDialog(this, msg);
    }

    public void addRow(Bagagiste b, Accueil a) {
        model.addRow(new Object[]{
                b.getNom() + " " + b.getPrenom(),
                a.getNom_service(),
                a.getDate()
        });
    }

    public void refreshAssignments(List<Accueil> accueils) {
        model.setRowCount(0); // clear
        for (Accueil a : accueils) {
            for (Bagagiste b : a.getBagagistes()) {
                addRow(b, a);
            }
        }
    }
}
