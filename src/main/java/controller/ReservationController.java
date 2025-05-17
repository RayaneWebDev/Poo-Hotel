package controller;

import model.*;
import view.ConsultationReservationsView;

import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ReservationController {

    private ConsultationReservationsView view;
    private List<Reservation> reservations;
    private List<Client> clients;
    private Hotel hotel;

    public ReservationController(ConsultationReservationsView view, Hotel hotel) {
        this.view  = view;
        this.hotel = hotel;
        this.reservations = (hotel.getReservations() != null)
                ? hotel.getReservations()
                : new ArrayList<>();
        this.clients = (hotel.getClients() != null)
                ? hotel.getClients()
                : new ArrayList<>();
        // binds
        view.getAjouterBtn().addActionListener(e -> ajouterReservation());
        view.getModifierBtn().addActionListener(e -> modifierReservation());
        // initial load
        loadClients(hotel.getClients());
        chargerReservations();
    }

    public void loadReservations(List<Reservation> reservations) {
        this.reservations = new ArrayList<>(reservations);
        chargerReservations();
    }

    public void loadClients(List<Client> clients) {
        view.setClients(clients);
    }

    public void chargerReservations() {
        view.afficherReservations(hotel.getReservations(), hotel.getClients());
    }

    private int genererNouvelID() {
        return reservations.stream()
                .mapToInt(Reservation::getID_reservation)
                .max()
                .orElse(0) + 1;
    }

    private void ajouterReservation() {
        try {
            // 1) Génération de l’ID
            int id = genererNouvelID();

            // 2) Lecture des champs
            int nuits = Integer.parseInt(view.getNuitsField().getText());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date debut = sdf.parse(view.getDebutField().getText());
            int chambreID = Integer.parseInt(view.getChambreIDField().getText());

            // 3) Récupérer le client sélectionné
            Client client = view.getSelectedClient();
            if (client == null) {
                view.showMessage("Veuillez sélectionner un client.");
                return;
            }

            // 4) Créer la chambre (tu pourras tirer d'un gestionnaire plus tard)
            Chambre chambre = new Chambre(chambreID,
                    chambreID % 2 == 0 ? Categorie_Chambre.SIMPLE : Categorie_Chambre.SUITE_NORMALE,
                    1);

            // 5) Créer la réservation
            Reservation nouvelleReservation = new Reservation(id, nuits, debut, chambre);

            // 6) Lier au client
            client.getReservations().add(nouvelleReservation);

            // 7) Lier à l'hôtel (et donc à la même liste 'reservations')
            hotel.addReservation(nouvelleReservation);
            if (sejourController != null) {
                sejourController.creerSejourPour(nouvelleReservation);
            }
            chargerReservations();
            view.showMessage("Réservation ajoutée avec succès !");
            viderChamps();
        }
        catch (NumberFormatException ex) {
            view.showMessage("Erreur : vérifiez les champs Nuits et Chambre ID.");
        }
        catch (ParseException ex) {
            view.showMessage("Erreur : format de date incorrect (yyyy-MM-dd).");
        }
    }


    private void modifierReservation() {
        try {
            // Récupérer la sélection dans la table pour obtenir l'ID
            int selectedRow = view.getReservationTable().getSelectedRow();
            if (selectedRow == -1) {
                view.showMessage("Veuillez sélectionner une réservation à modifier dans le tableau.");
                return;
            }

            int id = (int) view.getReservationTable().getValueAt(selectedRow, 0); // Attention : il faut que la colonne ID soit présente ou accessible
            // Dans ton tableau, tu n’as pas l’ID affiché, donc tu dois gérer autrement
            // Par exemple, stocker un Map<rowIndex, Reservation> dans la vue ou trouver une autre solution.

            // Ici, on peut retrouver la réservation par sélection et champs (plus simple)
            Reservation res = reservations.get(selectedRow);

            int nuits = Integer.parseInt(view.getNuitsField().getText());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date debut = sdf.parse(view.getDebutField().getText());
            int chambreID = Integer.parseInt(view.getChambreIDField().getText());

            Chambre chambre = new Chambre(chambreID,
                    chambreID % 2 == 0 ? Categorie_Chambre.SIMPLE : Categorie_Chambre.SUITE_NORMALE, 1);

            res.setNbr_nuits(nuits);
            res.setDate_debut(debut);
            res.setChambre(chambre);

            chargerReservations();
            view.showMessage("Réservation modifiée avec succès !");
            viderChamps();

        } catch (NumberFormatException ex) {
            view.showMessage("Erreur : vérifiez que les champs Nuits et Chambre ID sont bien des nombres.");
        } catch (ParseException ex) {
            view.showMessage("Erreur : format de date incorrect. Format attendu : yyyy-MM-dd");
        } catch (IndexOutOfBoundsException ex) {
            view.showMessage("Erreur : sélection invalide.");
        }
    }

    private void viderChamps() {
        view.getNuitsField().setText("");
        view.getDebutField().setText("");
        view.getChambreIDField().setText("");
    }
    private SejourController sejourController;
    public void setSejourController(SejourController sc) {
        this.sejourController = sc;
    }


}
