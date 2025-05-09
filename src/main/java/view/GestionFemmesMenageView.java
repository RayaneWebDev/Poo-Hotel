package view;

import controller.FemmeDeMenageController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class GestionFemmesMenageView extends JPanel {
    private JTable table;
    private DefaultTableModel model;
    private JTextField tfNom, tfPrenom, tfTelephone;
    private JLabel nomLabel, prenomLabel, telephoneLabel;
    private FemmeDeMenageController controller;

    public GestionFemmesMenageView() {
        setLayout(new BorderLayout());
        initUI();
    }

    public GestionFemmesMenageView(FemmeDeMenageController controller) {
        this();
        this.controller = controller;
        if (controller == null) {
            throw new IllegalArgumentException("Le contrôleur ne peut pas être null");
        }
        loadFemmes(); // Charger les données
    }

    private void initUI() {
        // Table et modèle
        model = new DefaultTableModel(new Object[]{"ID", "Nom", "Prénom", "Téléphone", "Ménages"}, 0);
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Panel formulaire + boutons
        JPanel panel = new JPanel();

        nomLabel = new JLabel("Nom:");
        tfNom = new JTextField(10);

        prenomLabel = new JLabel("Prénom:");
        tfPrenom = new JTextField(10);

        telephoneLabel = new JLabel("Téléphone:");
        tfTelephone = new JTextField(10);

        JButton btnAjouter = new JButton("Ajouter");
        JButton btnModifier = new JButton("Modifier");
        JButton btnSupprimer = new JButton("Supprimer");
        JButton btnRechercher = new JButton("Rechercher");

        // Actions des boutons (à implémenter côté contrôleur)
        btnAjouter.addActionListener(e -> controller.ajouterFemmeDepuisVue(this));
        btnModifier.addActionListener(e -> controller.modifierFemmeDepuisVue(this));
        btnSupprimer.addActionListener(e -> controller.supprimerFemmeDepuisVue(this));
        btnRechercher.addActionListener(e -> controller.rechercherFemmeDepuisVue(this));

        // Ajout dans le panel
        panel.add(nomLabel);
        panel.add(tfNom);
        panel.add(prenomLabel);
        panel.add(tfPrenom);
        panel.add(telephoneLabel);
        panel.add(tfTelephone);

        panel.add(btnAjouter);
        panel.add(btnModifier);
        panel.add(btnSupprimer);
        panel.add(btnRechercher);

        add(panel, BorderLayout.SOUTH);
    }

    public void loadFemmes() {
        model.setRowCount(0);
        controller.getFemmes().forEach(femme -> model.addRow(new Object[]{
                femme.getID_user(),
                femme.getNom(),
                femme.getPrenom(),
                femme.getTel(),
                femme.getMenages().size() + " ménage(s)"
        }));
    }

    // Getters
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
}
