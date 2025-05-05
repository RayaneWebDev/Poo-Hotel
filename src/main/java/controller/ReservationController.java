package controller;

import model.Chambre;
import model.Reservation;
import view.ConsultationReservationsView;

import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ReservationController {
    private ConsultationReservationsView view;
    private List<Reservation> reservations;

    public ReservationController(ConsultationReservationsView view) {
        this.view = view;
        this.reservations = new ArrayList<>();

        this.view.getAjouterBtn().addActionListener(e -> ajouterReservation());
        view.getModifierBtn().addActionListener(e -> modifierReservation());

    }

    private void ajouterReservation() {
        try {
            int id = Integer.parseInt(view.getIdField().getText());
            int nuits = Integer.parseInt(view.getNuitsField().getText());

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date debut = sdf.parse(view.getDebutField().getText());
            Date fin = sdf.parse(view.getFinField().getText());

            int chambreID = Integer.parseInt(view.getChambreIDField().getText());

            // Chambre fictive avec prix basé sur l’ID
            Chambre chambre = new Chambre(chambreID, chambreID % 2 == 0 ? model.Categorie_Chambre.SIMPLE : model.Categorie_Chambre.SUITE_NORMALE, 1);

            Reservation nouvelleReservation = new Reservation(id, nuits, debut, fin, chambre);
            reservations.add(nouvelleReservation);

            view.afficherReservations(reservations);
            view.showMessage("Réservation ajoutée avec succès !");
            viderChamps();

        } catch (NumberFormatException | ParseException ex) {
            view.showMessage("Erreur : vérifiez les champs. Format de date attendu : yyyy-MM-dd");
        }
    }

    public void modifierReservation() {
        int selectedRow = view.getReservationTable().getSelectedRow();
        if (selectedRow == -1) {
            view.showMessage("Veuillez sélectionner une réservation à modifier.");
            return;
        }

        try {
            int id = Integer.parseInt(view.getIdField().getText());
            int nuits = Integer.parseInt(view.getNuitsField().getText());

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date debut = sdf.parse(view.getDebutField().getText());
            Date fin = sdf.parse(view.getFinField().getText());

            int chambreID = Integer.parseInt(view.getChambreIDField().getText());

            for (Reservation r : reservations) {
                if (r.getID_reservation() == id) {
                    r.setNbr_nuits(nuits);
                    r.setDate_debut(debut);
                    r.setDate_fin(fin);

                    Chambre chambre = new Chambre(
                            chambreID,
                            chambreID % 2 == 0 ? model.Categorie_Chambre.SIMPLE : model.Categorie_Chambre.SUITE_NORMALE,
                            1
                    );
                    r.setChambre(chambre);

                    break;
                }
            }

            view.afficherReservations(reservations);
            view.showMessage("Réservation modifiée avec succès !");
            viderChamps();

        } catch (NumberFormatException | ParseException ex) {
            view.showMessage("Erreur : vérifiez les champs. Format de date attendu : yyyy-MM-dd");
        }
    }




    private void viderChamps() {
        view.getIdField().setText("");
        view.getNuitsField().setText("");
        view.getDebutField().setText("");
        view.getFinField().setText("");
        view.getChambreIDField().setText("");
    }
}