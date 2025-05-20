package view;

import model.Bagagiste;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class GererAcceuil extends JPanel {
    private JTable table;
    private DefaultTableModel model;
    private JTextField tfNom = new JTextField(10);
    private JTextField tfPrenom = new JTextField(10);
    private JTextField tfTelephone = new JTextField(10);
    private JButton ajouterBtn, supprimerBtn;

    public GererAcceuil(List<Bagagiste> bagagistes) {
        setLayout(new BorderLayout());

        JPanel formPanel = new JPanel();
        formPanel.add(new JLabel("Nom:"));
        formPanel.add(tfNom);
        formPanel.add(new JLabel("Prénom:"));
        formPanel.add(tfPrenom);
        formPanel.add(new JLabel("Téléphone:"));
        formPanel.add(tfTelephone);

        ajouterBtn = new JButton("Ajouter");
        supprimerBtn = new JButton("Supprimer");
        formPanel.add(ajouterBtn);
        formPanel.add(supprimerBtn);

        add(formPanel, BorderLayout.NORTH);

        model = new DefaultTableModel(new String[]{"ID", "Nom", "Prénom", "Téléphone"}, 0);
        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        refreshTable(bagagistes);
    }

    public void refreshTable(List<Bagagiste> bagagistes) {
        model.setRowCount(0);
        for (Bagagiste b : bagagistes) {
            model.addRow(new Object[]{b.getID_user(), b.getNom(), b.getPrenom(), b.getTel()});
        }
    }

    public JButton getAjouterBtn() {
        return ajouterBtn;
    }

    public JButton getSupprimerBtn() {
        return supprimerBtn;
    }

    public String getNom() {
        return tfNom.getText();
    }

    public String getPrenom() {
        return tfPrenom.getText();
    }

    public String getTelephone() {
        return tfTelephone.getText();
    }

    public JTable getTable() {
        return table;
    }

    public DefaultTableModel getModel() {
        return model;
    }

    public void showMessage(String msg) {
        JOptionPane.showMessageDialog(this, msg);
    }
}
