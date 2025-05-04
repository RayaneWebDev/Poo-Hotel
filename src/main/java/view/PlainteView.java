package view;

import controller.ClientController;
import controller.PlainteController;
import model.Plainte;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PlainteView extends JPanel {
    private JTable table;
    private DefaultTableModel model;
    private JButton btnRefresh;
    private PlainteController controller;


    // Constructeur par défaut
    public PlainteView() {
        setLayout(new BorderLayout());
        initUI();
    }

    public PlainteView(PlainteController controller) {
        this(); // Appeler le constructeur par défaut pour initialiser la vue
        this.controller = controller;
        if (controller == null) {
            throw new IllegalArgumentException("Le contrôleur ne peut pas être null");
        }
        controller.chargerPlaintes();
    }

    public void initUI(){
        model = new DefaultTableModel(new Object[]{"ID", "Objet", "Message", "Client"}, 0);
        table = new JTable(model);

        add(new JScrollPane(table), BorderLayout.CENTER);

        btnRefresh = new JButton("Rafraîchir les plaintes");
        btnRefresh.addActionListener(e -> controller.chargerPlaintes());

        JPanel panelSouth = new JPanel();
        panelSouth.add(btnRefresh);
        add(panelSouth, BorderLayout.SOUTH);
    }

    // Méthode pour mettre à jour le contrôleur après l'initialisation
    public void setController(PlainteController controller) {
        this.controller = controller;
        controller.chargerPlaintes();
    }

    public void ajouterPlainteDansTable(Plainte p) {
        model.addRow(new Object[]{
                p.getID_complaint(),
                p.getObjet(),
                p.getMessage(),
                p.getClient().getNom() + " " + p.getClient().getPrenom()
        });
    }

    public void clearTable() {
        model.setRowCount(0);
    }
}
