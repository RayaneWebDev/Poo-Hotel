package controller;

import model.*;
import view.ConsultationReservationsView;

import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class ReservationController {

    private ConsultationReservationsView view;
    private List<Reservation> reservations;
    private List<Client> clients;
    private Hotel hotel;
    private SejourController sejourController;

    public ReservationController(ConsultationReservationsView view, Hotel hotel) {
        this.view = view;
        this.hotel = hotel;
        this.reservations = (hotel.getReservations() != null)
                ? hotel.getReservations()
                : new ArrayList<>();
        this.clients = (hotel.getClients() != null)
                ? hotel.getClients()
                : new ArrayList<>();

        view.setController(this);

        // binds
        view.getAjouterBtn().addActionListener(e -> ajouterReservation());

        loadClients(hotel.getClients());
        chargerReservations();
    }

    public void loadClients(List<Client> clients) {
        this.clients = clients;
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

    public void setSejourController(SejourController sc) {
        this.sejourController = sc;
    }

    public List<Chambre> getChambresDisponibles(Date dateDebut, int nbrNuits) {
        if (dateDebut == null || nbrNuits <= 0) return Collections.emptyList();

        Calendar cal = Calendar.getInstance();
        cal.setTime(dateDebut);
        cal.add(Calendar.DATE, nbrNuits);
        Date dateFin = cal.getTime();

        List<Chambre> toutesChambres = hotel.getChambres();

        List<Chambre> chambresDisponibles = toutesChambres.stream()
                .filter(chambre -> {
                    for (Reservation res : reservations) {
                        if (res.getChambre().getID_chambre() == chambre.getID_chambre()) {
                            Date resDebut = res.getDate_debut();
                            Calendar calResFin = Calendar.getInstance();
                            calResFin.setTime(resDebut);
                            calResFin.add(Calendar.DATE, res.getNbr_nuits());
                            Date resFin = calResFin.getTime();

                            // Vérifier chevauchement
                            if (!(dateFin.compareTo(resDebut) <= 0 || dateDebut.compareTo(resFin) >= 0)) {
                                return false; // chambre non dispo
                            }
                        }
                    }
                    return true; // chambre dispo
                })
                .collect(Collectors.toList());

        return chambresDisponibles;
    }

    private void ajouterReservation() {
        try {
            int id = genererNouvelID();

            int nuits = Integer.parseInt(view.getNuitsField().getText());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date debut = sdf.parse(view.getDebutField().getText());

            Client client = view.getSelectedClient();
            if (client == null) {
                view.showMessage("Veuillez sélectionner un client.");
                return;
            }

            Chambre chambre = (Chambre) view.getChambreComboBox().getSelectedItem();
            if (chambre == null) {
                view.showMessage("Veuillez sélectionner une chambre disponible.");
                return;
            }

            // Vérifier qu'aucune réservation existante ne chevauche la période
            Calendar cal = Calendar.getInstance();
            cal.setTime(debut);
            cal.add(Calendar.DATE, nuits);
            Date dateFin = cal.getTime();

            for (Reservation res : reservations) {
                if (res.getChambre().getID_chambre() == chambre.getID_chambre()) {
                    Date resDebut = res.getDate_debut();
                    cal.setTime(resDebut);
                    cal.add(Calendar.DATE, res.getNbr_nuits());
                    Date resFin = cal.getTime();

                    if (!(dateFin.compareTo(resDebut) <= 0 || debut.compareTo(resFin) >= 0)) {
                        view.showMessage("La chambre est déjà réservée pour cette période.");
                        return;
                    }
                }
            }

            // Création de la réservation
            Reservation nouvelleRes = new Reservation(id, nuits, debut, chambre);
            hotel.addReservation(nouvelleRes);

            // Ajouter la réservation au client
            client.getReservations().add(nouvelleRes);

            if (sejourController != null) {
                sejourController.creerSejourPour(nouvelleRes);
            }



            // Mettre à jour l'état de la chambre
            chambre.setEtat("Reservée"); // 1 = Réservée

            view.showMessage("Réservation ajoutée avec succès.");
            chargerReservations();
            view.viderChamps();

        } catch (NumberFormatException nfe) {
            view.showMessage("Le nombre de nuits doit être un entier valide.");
        } catch (ParseException pe) {
            view.showMessage("La date de début doit être au format yyyy-MM-dd.");
        } catch (Exception e) {
            view.showMessage("Erreur lors de l'ajout de la réservation : " + e.getMessage());
            e.printStackTrace();
        }
    }


}

