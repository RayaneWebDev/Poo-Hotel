package controller;

import model.Client;
import model.Hotel;
import model.Reservation;
import model.Sejour;
import view.SejourView;

import java.util.ArrayList;
import java.util.List;

public class SejourController {
    private SejourView view;
    private List<Sejour> sejours;
    private List<Client> clients;
    private Hotel hotel;

    public SejourController(SejourView view, Hotel hotel) {
        this.view    = view;
        this.hotel   = hotel;
        this.sejours = (hotel.getSejours() != null)
                ? hotel.getSejours()
                : new ArrayList<>();
        this.clients = (hotel.getClients() != null)
                ? hotel.getClients()
                : new ArrayList<>();
    }

    // Appelé après chaque création de réservation
    public void creerSejourPour(Reservation r) {
        int nextId = sejours.stream()
                .mapToInt(Sejour::getID_sejour)
                .max()
                .orElse(0) + 1;
        Sejour sj = new Sejour(nextId, r, new ArrayList<>());
        sejours.add(sj);
        hotel.setSejours(sejours);   // mettre à jour l’hôtel
        chargerSejours();
    }

    public void chargerSejours() {
        view.clearTable();
        for (Sejour s : sejours) {
            Client c = trouverClientParReservation(s.getReservation());
            view.ajouterSejourDansTable(s, c);
        }
    }

    public void setSejours(List<Sejour> sejours) {
        this.sejours = sejours;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    public void loadSejours(List<Sejour> sejours, List<Client> clients) {
        this.sejours = sejours;
        this.clients = clients;
        chargerSejours(); // recharge la vue
    }



    private Client trouverClientParReservation(model.Reservation reservation) {
        if (reservation == null) return null;
        for (Client c : clients) {
            if (c.getReservations() != null) {
                for (model.Reservation r : c.getReservations()) {
                    if (r.equals(reservation)) {
                        return c;
                    }
                }
            }
        }
        return null;
    }
}
