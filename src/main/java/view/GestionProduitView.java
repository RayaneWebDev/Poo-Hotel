package view;

import controller.Consultationstock;
import model.ProduitMinibar;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class GestionProduitView extends JPanel {
    private JTable produitTable;
    private DefaultTableModel model;
    private JTextField tfId, tfNom, tfPrix, tfQauntite;
    private Consultationstock controller;

    public GestionProduitView(Consultationstock controller) {
        this.controller = controller;
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("GESTION DES PRODUITS", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        add(titleLabel, BorderLayout.NORTH);

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        tfId = new JTextField(5);
        tfNom = new JTextField(10);
        tfPrix = new JTextField(5);
        tfQauntite =  new JTextField(5);
        JButton ajouterBtn = new JButton("Ajouter");
        JButton supprimerBtn = new JButton("Supprimer");

        topPanel.add(new JLabel("ID:")); topPanel.add(tfId);
        topPanel.add(new JLabel("Nom:")); topPanel.add(tfNom);
        topPanel.add(new JLabel("Prix:")); topPanel.add(tfPrix);
        topPanel.add(new JLabel("Quantite:")); topPanel.add(tfQauntite);
        topPanel.add(ajouterBtn); topPanel.add(supprimerBtn);
        add(topPanel, BorderLayout.NORTH);

        String[] colonnes = {"ID", "Nom", "Prix", "Quantite"};
        model = new DefaultTableModel(colonnes, 0);
        produitTable = new JTable(model);
        add(new JScrollPane(produitTable), BorderLayout.CENTER);

        // Initial load
        refreshTable();

        ajouterBtn.addActionListener(e -> {
            try {
                int id = Integer.parseInt(tfId.getText());
                String nom = tfNom.getText();
                float prix = Float.parseFloat(tfPrix.getText());
                int quatite = Integer.parseInt(tfQauntite.getText());
                controller.AddProf(id, nom, prix, quatite);
                refreshTable();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Entrée invalide !");
            }
        });

        supprimerBtn.addActionListener(e -> {
            int selectedRow = produitTable.getSelectedRow();
            if (selectedRow >= 0) {
                int id = (int) model.getValueAt(selectedRow, 0);
                controller.Supprimer(id);
                refreshTable();
            } else {
                JOptionPane.showMessageDialog(this, "Sélectionnez une ligne à supprimer.");
            }
        });
    }

    private void refreshTable() {
        model.setRowCount(0);
        for (ProduitMinibar p : controller.getProduits()) {
            model.addRow(new Object[]{p.getId_produit(), p.getNomProduit(), p.getPrix(), p.getQuantite()});
        }
    }
}
