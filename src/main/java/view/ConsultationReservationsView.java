package view;

import model.Reservation;
import model.Client;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ConsultationReservationsView extends JPanel {

    private JTable reservationTable;
    private DefaultTableModel model;

    // Champs du formulaire
    private JTextField nuitsField = new JTextField(5);
    private JTextField debutField = new JTextField(10);
    private JTextField chambreIDField = new JTextField(5);
    private JComboBox<Client> clientComboBox = new JComboBox<>();

    private JButton ajouterBtn = new JButton("Ajouter");
    private JButton modifierBtn = new JButton("Modifier");

    public ConsultationReservationsView() {
        setLayout(new BorderLayout());

        JLabel title = new JLabel("GÉRER LES RÉSERVATIONS", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        add(title, BorderLayout.NORTH);

        // Formulaire avec 4 colonnes et 2 lignes (labels + champs)
        JPanel formPanel = new JPanel(new GridLayout(2, 4, 5, 5));
        formPanel.setBorder(BorderFactory.createTitledBorder("Ajouter / Modifier une réservation"));

        formPanel.add(new JLabel("Client"));
        formPanel.add(new JLabel("Nuits"));
        formPanel.add(new JLabel("Début (yyyy-mm-dd)"));
        formPanel.add(new JLabel("Numéro chambre"));

        formPanel.add(clientComboBox);
        formPanel.add(nuitsField);
        formPanel.add(debutField);
        formPanel.add(chambreIDField);

        add(formPanel, BorderLayout.NORTH);

        // Boutons dans un panel séparé (plus esthétique)
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(ajouterBtn);
        buttonPanel.add(modifierBtn);
        add(buttonPanel, BorderLayout.AFTER_LAST_LINE);

        // Tableau des réservations
        String[] colonnes = {"Nom Client", "Nuits", "Date Début", "Numéro Chambre", "Prix Total (€)"};
        model = new DefaultTableModel(colonnes, 0) {
            // Rendre le tableau non éditable directement
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        reservationTable = new JTable(model);
        reservationTable.setRowHeight(25);

        add(new JScrollPane(reservationTable), BorderLayout.CENTER);
    }

    public void afficherReservations(List<Reservation> reservations, List<Client> clients) {
        model.setRowCount(0);
        for (Reservation r : reservations) {
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

    // Récupérer la ligne sélectionnée dans le tableau
    public int getSelectedReservationRow() {
        return reservationTable.getSelectedRow();
    }

    // Remplir le formulaire à partir d'une réservation (pour modification)
    public void remplirFormulaire(Reservation r) {
        if (r == null) return;
        nuitsField.setText(String.valueOf(r.getNbr_nuits()));
        debutField.setText(r.getDate_debut().toString());
        chambreIDField.setText(String.valueOf(r.getChambre().getID_chambre()));

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

    // Getters pour les champs
    public JTextField getNuitsField() { return nuitsField; }
    public JTextField getDebutField() { return debutField; }
    public JTextField getChambreIDField() { return chambreIDField; }
    public JButton getAjouterBtn() { return ajouterBtn; }
    public JButton getModifierBtn() { return modifierBtn; }
    public JTable getReservationTable() { return reservationTable; }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public void viderChamps() {
        nuitsField.setText("");
        debutField.setText("");
        chambreIDField.setText("");
        clientComboBox.setSelectedIndex(-1);
    }
}
