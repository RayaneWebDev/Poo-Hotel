package view;

import controller.ClientController;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class GestionClientsView extends JPanel {
    private JTable table;
    private DefaultTableModel model;
    private JTextField tfNom, tfPrenom, tfTelephone, tfEmail;
    private JLabel nomLabel , prenomLabel, telephoneLabel, emailLabel;
    private ClientController controller;

    public GestionClientsView() {
        setLayout(new BorderLayout());
        initUI();
    }

    public GestionClientsView(ClientController controller) {
        this();
        this.controller = controller;
        if (controller == null) {
            throw new IllegalArgumentException("Le contrôleur ne peut pas être null");
        }
        controller.loadClients();
    }

    private void initUI() {
        model = new DefaultTableModel(new Object[]{"ID", "Nom", "Prenom", "Téléphone", "Email"}, 0);
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        JPanel panel = new JPanel();

        nomLabel = new JLabel("Nom:");
        tfNom = new JTextField(10);

        prenomLabel = new JLabel("Prénom:");
        tfPrenom = new JTextField(10);

        telephoneLabel = new JLabel("Téléphone:");
        tfTelephone = new JTextField(10);

        emailLabel = new JLabel("Email:");
        tfEmail = new JTextField(10);

        JButton btnAjouter = new JButton("Ajouter");
        JButton btnModifier = new JButton("Modifier");
        JButton btnSupprimer = new JButton("Supprimer");
        JButton btnRechercher = new JButton("Rechercher");

        btnAjouter.addActionListener(e -> controller.ajouterClient());
        btnModifier.addActionListener(e -> controller.modifierClient());
        btnSupprimer.addActionListener(e -> controller.supprimerClient());
        btnRechercher.addActionListener(e -> controller.rechercherClient());

        panel.add(nomLabel);
        panel.add(tfNom);

        panel.add(prenomLabel);
        panel.add(tfPrenom);

        panel.add(telephoneLabel);
        panel.add(tfTelephone);

        panel.add(emailLabel);
        panel.add(tfEmail);

        panel.add(btnAjouter);
        panel.add(btnModifier);
        panel.add(btnSupprimer);
        panel.add(btnRechercher);

        add(panel, BorderLayout.SOUTH);
    }


    public void setController(ClientController controller) {
        this.controller = controller;
        controller.loadClients();
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

    public String getEmail(){ return tfEmail.getText(); }

    public JTable getTable() {
        return table;
    }

    public DefaultTableModel getModel() {
        return model;
    }
}
