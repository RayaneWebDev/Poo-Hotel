package view;

import model.Reservation;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ConsultationReservationsView extends JPanel {

    private JTable reservationTable;
    private JTextField searchField = new JTextField(10);

    public JTextField getSearchField() {
        return searchField;
    }

    public void setSearchField(JTextField searchField) {
        this.searchField = searchField;
    }

    public JTable getReservationTable() {
        return reservationTable;
    }

    public void setReservationTable(JTable reservationTable) {
        this.reservationTable = reservationTable;
    }

    private DefaultTableModel model;

    public DefaultTableModel getModel() {
        return model;
    }

    public void setModel(DefaultTableModel model) {
        this.model = model;
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }


    public ConsultationReservationsView() {
        setLayout(new BorderLayout());

        // Titre
        JLabel titleLabel = new JLabel("CONSULTATION DES RÉSERVATIONS", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        add(titleLabel, BorderLayout.NORTH);

        // Colonnes du tableau
        String[] colonnes = {"ID", "Nuits", "Début", "Fin", "Chambre", "Prix Total", "Statut"};
        model = new DefaultTableModel(colonnes, 0);
        reservationTable = new JTable(model);
        reservationTable.setRowHeight(25);
        add(new JScrollPane(reservationTable), BorderLayout.CENTER);
    }

    public void afficherReservations(List<Reservation> reservations) {
        model.setRowCount(0); // Vider le tableau avant de le remplir
        for (Reservation r : reservations) {
            Object[] row = {
                    r.getID_reservation(),
                    r.getNbr_nuits(),
                    r.getDate_debut(),
                    r.getDate_fin(),
                    r.getChambre().getID_chambre(),
                    r.total_reservation(r.getNbr_nuits(), r.getPrix()),
                    r.getstatus() ? "Confirmée" : "En attente"
            };
            model.addRow(row);
        }
    }
}

