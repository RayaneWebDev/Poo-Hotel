package controller;

import model.Chambre;
import model.Reservation;
import model.Categorie_Chambre;
import view.ConsultationReservationsView;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReservationController {
    private ConsultationReservationsView view;
    private List<Reservation> reservations;

    public ReservationController(ConsultationReservationsView view) {
        this.view = view;
        this.reservations = new ArrayList<>();
        chargerReservations(); // remplir les données
        afficherReservations(); // afficher dans le tableau
    }

    // Remplir des réservations fictives
    private void chargerReservations() {
        Chambre chambre1 = new Chambre(101, Categorie_Chambre.SIMPLE, 0); // 0 = disponible
        Chambre chambre2 = new Chambre(102, Categorie_Chambre.DOUBLE, 1); // 1 = réservée

        reservations.add(new Reservation(1, 3, new Date(), new Date(), chambre1, true));
        reservations.add(new Reservation(2, 2, new Date(), new Date(), chambre2, false));
    }


    // Afficher toutes les réservations dans le tableau
    private void afficherReservations() {
        DefaultTableModel model = view.getModel();
        model.setRowCount(0); // nettoyer le tableau

        for (Reservation r : reservations) {
            model.addRow(new Object[]{
                    r.getID_reservation(),
                    r.getNbr_nuits(),
                    r.getDate_debut(),
                    r.getDate_fin(),
                    r.getChambre().getID_chambre(),
                    r.getChambre().getCategorie(),
                    r.getPrix(),
                    r.getstatus() ? "Confirmée" : "En attente"
            });
        }
    }

    // Rechercher une réservation par ID
    public void rechercherReservation() {
        String idStr = view.getSearchField().getText().trim();
        if (idStr.isEmpty()) {
            afficherReservations();
            return;
        }

        try {
            int idRecherche = Integer.parseInt(idStr);
            DefaultTableModel model = view.getModel();
            model.setRowCount(0);

            for (Reservation r : reservations) {
                if (r.getID_reservation() == idRecherche) {
                    model.addRow(new Object[]{
                            r.getID_reservation(),
                            r.getNbr_nuits(),
                            r.getDate_debut(),
                            r.getDate_fin(),
                            r.getChambre().getID_chambre(),
                            r.getChambre().getCategorie(),
                            r.getPrix(),
                            r.getstatus() ? "Confirmée" : "En attente"
                    });
                    return;
                }
            }

            // Si rien trouvé
            view.showMessage("Aucune réservation trouvée avec l'ID : " + idRecherche);

        } catch (NumberFormatException e) {
            view.showMessage("ID invalide !");
        }
    }
}

