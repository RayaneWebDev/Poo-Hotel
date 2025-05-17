package view;

import controller.SejourController;
import model.Client;
import model.ProduitMinibar;
import model.Sejour;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class SejourView extends JPanel {
    private JTable table;
    private DefaultTableModel model;
    private JButton btnRefresh;
    private SejourController controller;

    public SejourView() {
        setLayout(new BorderLayout());
        initUI();
    }

    public SejourView(SejourController controller) {
        this.controller = controller;
        if (controller == null) {
            throw new IllegalArgumentException("Le contrôleur ne peut pas être null");
        }
        setLayout(new BorderLayout());
        controller.chargerSejours();
    }

    private void initUI() {
        model = new DefaultTableModel(new Object[]{
                "ID Séjour",
                "Client",
                "Début",
                "Nuits",
                "Total (€)",
                "Produits consommés"
        }, 0);
        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        btnRefresh = new JButton("Rafraîchir les séjours");
        btnRefresh.addActionListener(e -> {
            if (controller != null) {
                controller.chargerSejours();
            }
        });

        JPanel panelSouth = new JPanel();
        panelSouth.add(btnRefresh);
        add(panelSouth, BorderLayout.SOUTH);
    }

    public void ajouterSejourDansTable(Sejour sejour, Client client) {
        String nomClient = (client != null) ? client.getPrenom() + " " + client.getNom() : "Client inconnu";

        StringBuilder produits = new StringBuilder();
        for (ProduitMinibar p : sejour.getProduitMinibar()) {
            produits.append(p.getNomProduit())
                    .append(" (")
                    .append(p.getPrix())
                    .append(" €), ");
        }
        if (produits.length() > 0) {
            produits.setLength(produits.length() - 2); // supprime la dernière virgule et espace
        }

        model.addRow(new Object[]{
                sejour.getID_sejour(),
                nomClient,
                sejour.getReservation().getDate_debut(),
                sejour.getReservation().getNbr_nuits(),
                sejour.getReservation().total_reservation(
                        sejour.getReservation().getNbr_nuits(),
                        sejour.getReservation().getPrix()),
                produits.toString()
        });
    }

    public void clearTable() {
        model.setRowCount(0);
    }

    public void setController(SejourController controller) {
        this.controller = controller;
        controller.chargerSejours();
    }
}
