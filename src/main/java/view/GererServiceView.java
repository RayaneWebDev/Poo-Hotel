package view;

import model.Accueil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class GererServiceView extends JPanel {

    private JTextField tfNomService;
    private JButton btnAjouter, btnModifier, btnSupprimer;
    private JTable table;
    private DefaultTableModel model;

    public GererServiceView(List<Accueil> services) {
        setLayout(new BorderLayout());

        // Formulaire haut
        JPanel formPanel = new JPanel();
        formPanel.add(new JLabel("Nom du service :"));
        tfNomService = new JTextField(15);
        formPanel.add(tfNomService);

        btnAjouter = new JButton("Ajouter");
        btnModifier = new JButton("Modifier");
        btnSupprimer = new JButton("Supprimer");

        formPanel.add(btnAjouter);
        formPanel.add(btnModifier);
        formPanel.add(btnSupprimer);

        add(formPanel, BorderLayout.NORTH);

        // Table
        model = new DefaultTableModel(new String[]{"ID", "Nom du service", "Date"}, 0);
        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // Initialisation
        refreshTable(services);
    }

    public void refreshTable(List<Accueil> services) {
        model.setRowCount(0);
        for (Accueil a : services) {
            model.addRow(new Object[]{a.getID_service(), a.getNom_service(), a.getDate()});
        }
    }

    public String getNomService() {
        return tfNomService.getText().trim();
    }

    public void clearInput() {
        tfNomService.setText("");
    }

    public JTable getTable() {
        return table;
    }

    public JButton getBtnAjouter() {
        return btnAjouter;
    }

    public JButton getBtnModifier() {
        return btnModifier;
    }

    public JButton getBtnSupprimer() {
        return btnSupprimer;
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}
