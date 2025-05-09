package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import model.Bagagiste;
import java.util.List;
import controller.GererAcceuilController;


public class GererAcceuil extends JPanel {

    private JTable table;
    private GererAcceuilController controller;
    private DefaultTableModel model;
    private JTextField tfNom = new JTextField(10);
    private JButton ajouterBtn, supprimerBtn;
    private JTextField tfPrenom = new JTextField(10);
    private JTextField tfTelephone = new JTextField(10);
    private List<Bagagiste> bagagistes;


    public GererAcceuil(List<Bagagiste> bagagistes, GererAcceuilController controller) {
        this.controller = controller;

        this.bagagistes = bagagistes;

        setLayout(new BorderLayout());

        JLabel title = new JLabel("GESTION DES BAGAGISTES", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        add(title, BorderLayout.NORTH);

        // Panel formulaire
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

        // Tableau
        model = new DefaultTableModel(new String[]{"ID", "Nom", "Prénom", "Téléphone"}, 0);
        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);
    }


    // Getters
    public String getNom() {
        return tfNom.getText().trim();
    }


    public JTable getTable() {
        return table;
    }
    public String getPrenom() {
        return tfPrenom.getText().trim();
    }

    public String getTelephone() {
        return tfTelephone.getText().trim();
    }


    public DefaultTableModel getModel() {
        return model;
    }

    public JButton getAjouterBtn() {
        return ajouterBtn;
    }

    public JButton getSupprimerBtn() {
        return supprimerBtn;
    }

    public void showMessage(String msg) {
        JOptionPane.showMessageDialog(this, msg);
    }
}
