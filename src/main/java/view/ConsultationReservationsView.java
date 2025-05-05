package view;

import model.Chambre;
import model.Reservation;
import model.Categorie_Chambre;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Date;
import java.util.List;

public class ConsultationReservationsView extends JPanel {

    private JTable reservationTable;

    public DefaultTableModel getModel() {
        return model;
    }

    public JTable getReservationTable() {
        return reservationTable;
    }

    public void setReservationTable(JTable reservationTable) {
        this.reservationTable = reservationTable;
    }

    private DefaultTableModel model;

    // Champs du formulaire
    private JTextField idField = new JTextField(5);
    private JTextField nuitsField = new JTextField(5);
    private JTextField debutField = new JTextField(10); // format simple : yyyy-mm-dd
    private JTextField finField = new JTextField(10);
    private JTextField chambreIDField = new JTextField(5);

    public void setModel(DefaultTableModel model) {
        this.model = model;
    }

    JButton ajouterBtn = new JButton("Ajouter");
    JButton modifierBtn = new JButton("Modifier");


    public ConsultationReservationsView() {
        setLayout(new BorderLayout());

        // Titre
        JLabel title = new JLabel("GÉRER LES CONSULTATIONS", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        add(title, BorderLayout.NORTH);

        // Formulaire d'ajout
        JPanel formPanel = new JPanel(new GridLayout(3, 5, 5, 5));
        formPanel.setBorder(BorderFactory.createTitledBorder("Ajouter une réservation"));

        formPanel.add(new JLabel("ID"));
        formPanel.add(new JLabel("Nuits"));
        formPanel.add(new JLabel("Début (yyyy-mm-dd)"));
        formPanel.add(new JLabel("Fin (yyyy-mm-dd)"));
        formPanel.add(new JLabel("Chambre ID"));

        formPanel.add(idField);
        formPanel.add(nuitsField);
        formPanel.add(debutField);
        formPanel.add(finField);
        formPanel.add(chambreIDField);

        formPanel.add(ajouterBtn);
        formPanel.add(modifierBtn);
        formPanel.add(new JLabel("")); // Cellules vides pour remplir la ligne
        formPanel.add(new JLabel(""));
        formPanel.add(new JLabel(""));



        add(formPanel, BorderLayout.NORTH);

        // Tableau
        String[] colonnes = {"ID", "Nuits", "Début", "Fin", "Chambre"};
        model = new DefaultTableModel(colonnes, 0);
        reservationTable = new JTable(model);
        reservationTable.setRowHeight(25);

        add(new JScrollPane(reservationTable), BorderLayout.CENTER);
    }

    public void afficherReservations(List<Reservation> reservations) {
        model.setRowCount(0);
        for (Reservation r : reservations) {
            model.addRow(new Object[]{
                    r.getID_reservation(),
                    r.getNbr_nuits(),
                    r.getDate_debut(),
                    r.getDate_fin(),
                    r.getChambre().getID_chambre(),
                    r.total_reservation(r.getNbr_nuits(), r.getPrix())
            });
        }
    }

    // Getters pour les champs du formulaire

    public JButton getModifierBtn() {
        return modifierBtn;
    }

    public JButton getAjouterBtn() {
        return ajouterBtn;
    }

    public JTextField getIdField() {
        return idField;
    }

    public JTextField getNuitsField() {
        return nuitsField;
    }

    public JTextField getDebutField() {
        return debutField;
    }

    public JTextField getFinField() {
        return finField;
    }

    public JTextField getChambreIDField() {
        return chambreIDField;
    }


    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}