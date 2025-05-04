package view;

import controller.Consultationstock;
import model.ProduitMinibar;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ConsultationStock extends JPanel {
    private JTable produitTable;
    private DefaultTableModel model;
    private Consultationstock controller;

    public ConsultationStock(Consultationstock controller) {
        this.controller = controller;
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("CONSULTATION DU STOCK", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        add(titleLabel, BorderLayout.NORTH);

        String[] colonnes = {"ID", "Nom", "Prix", "Quantité"};
        model = new DefaultTableModel(colonnes, 0);
        produitTable = new JTable(model);
        add(new JScrollPane(produitTable), BorderLayout.CENTER);

        refreshTable();
    }

    private void refreshTable() {
        model.setRowCount(0);
        for (ProduitMinibar p : controller.getProduits()) {
            model.addRow(new Object[]{p.getId_produit(), p.getNomProduit(), p.getPrix(), p.getQuantite()});
        }
    }
}
