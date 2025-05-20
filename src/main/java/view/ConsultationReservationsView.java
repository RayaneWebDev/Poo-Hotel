package view;

import controller.ReservationController;
import model.Chambre;
import model.Client;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ConsultationReservationsView extends JPanel {

    private JTable reservationTable;
    private DefaultTableModel model;

    private JTextField nuitsField = new JTextField(5);
    private JTextField debutField = new JTextField(10);
    private JComboBox<Chambre> chambreComboBox = new JComboBox<>();
    private JComboBox<Client> clientComboBox = new JComboBox<>();

    private JButton ajouterBtn = new JButton("Ajouter");

    // Référence au controller pour récupérer les chambres disponibles
    private ReservationController controller;

    public ConsultationReservationsView() {
        setLayout(new BorderLayout());

        JLabel title = new JLabel("GÉRER LES RÉSERVATIONS", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        add(title, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridLayout(2, 4, 5, 5));
        formPanel.setBorder(BorderFactory.createTitledBorder("Ajouter / Modifier une réservation"));

        formPanel.add(new JLabel("Client"));
        formPanel.add(new JLabel("Nuits"));
        formPanel.add(new JLabel("Début (yyyy-MM-dd)"));
        formPanel.add(new JLabel("Chambre"));

        formPanel.add(clientComboBox);
        formPanel.add(nuitsField);
        formPanel.add(debutField);
        formPanel.add(chambreComboBox);

        add(formPanel, BorderLayout.NORTH);

        // Boutons dans un panel séparé (plus esthétique)
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(ajouterBtn);
        add(buttonPanel, BorderLayout.SOUTH);

        // Tableau des réservations
        String[] colonnes = {"Nom Client", "Nuits", "Date Début", "Numéro Chambre", "Prix Total (€)"};
        model = new DefaultTableModel(colonnes, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        reservationTable = new JTable(model);
        reservationTable.setRowHeight(25);
        add(new JScrollPane(reservationTable), BorderLayout.CENTER);

        // Ecouteurs pour mise à jour dynamique des chambres disponibles
        DocumentListener docListener = new DocumentListener() {
            public void changedUpdate(DocumentEvent e) { updateChambresDisponibles(); }
            public void removeUpdate(DocumentEvent e) { updateChambresDisponibles(); }
            public void insertUpdate(DocumentEvent e) { updateChambresDisponibles(); }
        };
        nuitsField.getDocument().addDocumentListener(docListener);
        debutField.getDocument().addDocumentListener(docListener);
    }

    public void setController(ReservationController controller) {
        this.controller = controller;
    }

    public void afficherReservations(List<model.Reservation> reservations, List<Client> clients) {
        model.setRowCount(0);
        for (model.Reservation r : reservations) {
            Client clientTrouve = clients.stream()
                    .filter(c -> c.getReservations() != null && c.getReservations().contains(r))
                    .findFirst()
                    .orElse(null);

            String nomComplet = (clientTrouve != null)
                    ? clientTrouve.getPrenom() + " " + clientTrouve.getNom()
                    : "Inconnu";

            double prixUnitaire = r.getChambre().getPrix();
            double prixTotal = r.getNbr_nuits() * prixUnitaire;

            model.addRow(new Object[]{
                    nomComplet,
                    r.getNbr_nuits(),
                    r.getDate_debut(),
                    r.getChambre().getID_chambre(),
                    prixTotal
            });
        }
    }

    public void setClients(List<Client> clients) {
        clientComboBox.removeAllItems();
        for (Client c : clients) {
            clientComboBox.addItem(c);
        }
    }

    public Client getSelectedClient() {
        return (Client) clientComboBox.getSelectedItem();
    }

    public int getSelectedReservationRow() {
        return reservationTable.getSelectedRow();
    }

    public void remplirFormulaire(model.Reservation r) {
        if (r == null) return;
        nuitsField.setText(String.valueOf(r.getNbr_nuits()));
        debutField.setText(r.getDate_debut().toString());

        // On sélectionne la chambre dans le combo, s'il est présent
        chambreComboBox.removeAllItems();
        chambreComboBox.addItem(r.getChambre());
        chambreComboBox.setSelectedItem(r.getChambre());

        // Sélectionner le client dans le combo
        Client client = null;
        for (int i = 0; i < clientComboBox.getItemCount(); i++) {
            Client c = clientComboBox.getItemAt(i);
            if (c.getReservations() != null && c.getReservations().contains(r)) {
                client = c;
                break;
            }
        }
        if (client != null) {
            clientComboBox.setSelectedItem(client);
        } else {
            clientComboBox.setSelectedIndex(-1);
        }
    }

    public JTextField getNuitsField() { return nuitsField; }
    public JTextField getDebutField() { return debutField; }
    public JComboBox<Chambre> getChambreComboBox() { return chambreComboBox; }
    public JButton getAjouterBtn() { return ajouterBtn; }
    public JTable getReservationTable() { return reservationTable; }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public void viderChamps() {
        nuitsField.setText("");
        debutField.setText("");
        chambreComboBox.removeAllItems();
        clientComboBox.setSelectedIndex(-1);
    }

    // Appelée quand nuits ou date début changent pour mettre à jour la liste des chambres disponibles
    private void updateChambresDisponibles() {
        if (controller == null) return;

        try {
            int nuits = Integer.parseInt(nuitsField.getText());
            java.util.Date debut = new java.text.SimpleDateFormat("yyyy-MM-dd").parse(debutField.getText());

            List<Chambre> dispo = controller.getChambresDisponibles(debut, nuits);
            chambreComboBox.removeAllItems();
            for (Chambre c : dispo) {
                chambreComboBox.addItem(c);
            }
        } catch (Exception e) {
            // Si erreur de parsing, on vide la liste
            chambreComboBox.removeAllItems();
        }
    }
}
