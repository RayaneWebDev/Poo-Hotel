package view;

import controller.ChambreControleur;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class GestionChambres extends JPanel {

    private JTable chambreTable;
    private DefaultTableModel model;
    private JTextField tfNumero;
    private JComboBox<String> cbType, cbEtat;
    private ChambreControleur controller;

    public GestionChambres() {
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("GESTION DES CHAMBRES", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        add(titleLabel, BorderLayout.NORTH);

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        tfNumero = new JTextField(5);
        cbType = new JComboBox<>(new String[]{"SIMPLE", "SUITE_NORMALE", "SUITE_PRESIDENTIELLE"});
        cbEtat = new JComboBox<>(new String[]{"Disponible", "Occupée", "En nettoyage", "En maintenance", "Réservée"});

        JButton ajouterBtn = new JButton("Ajouter");
        JButton modifierBtn = new JButton("Modifier");
        JButton supprimerBtn = new JButton("Supprimer");
        JButton rechercherBtn = new JButton("Rechercher");

        topPanel.add(new JLabel("Numéro:"));
        topPanel.add(tfNumero);
        topPanel.add(new JLabel("Type:"));
        topPanel.add(cbType);
        topPanel.add(new JLabel("État:"));
        topPanel.add(cbEtat);
        topPanel.add(ajouterBtn);
        topPanel.add(modifierBtn);
        topPanel.add(supprimerBtn);
        topPanel.add(rechercherBtn);

        add(topPanel, BorderLayout.PAGE_START);

        String[] colonnes = {"Numéro", "Type", "Prix (€)", "État"};
        model = new DefaultTableModel(colonnes, 0);
        chambreTable = new JTable(model);
        add(new JScrollPane(chambreTable), BorderLayout.CENTER);

        controller = new ChambreControleur(this);

        ajouterBtn.addActionListener(e -> controller.ajouterChambre());
        modifierBtn.addActionListener(e -> controller.modifierChambre());
        supprimerBtn.addActionListener(e -> controller.supprimerChambre());
        rechercherBtn.addActionListener(e -> controller.rechercherChambre());
    }

    public String getNumero() { return tfNumero.getText(); }
    public String getType() { return cbType.getSelectedItem().toString(); }
    public String getEtat() { return cbEtat.getSelectedItem().toString(); }
    public JTable getTable() { return chambreTable; }
    public DefaultTableModel getModel() { return model; }

    public void resetChamps() {
        tfNumero.setText("");
        cbType.setSelectedIndex(0);
        cbEtat.setSelectedIndex(0);
    }
}
