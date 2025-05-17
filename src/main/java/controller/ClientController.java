package controller;

import model.Client;
import model.Hotel;
import view.GestionClientsView;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class ClientController {
    private final GestionClientsView view;
    private final Hotel hotel;
    private ReservationController reservationController;

    public ClientController(GestionClientsView view, Hotel hotel) {
        this.view  = view;
        this.hotel = hotel;
        loadClients();
    }

    public void setReservationController(ReservationController rc) {
        this.reservationController = rc;
    }

    public void loadClients() {
        DefaultTableModel m = view.getModel();
        m.setRowCount(0);
        for (Client c : hotel.getClients()) {
            m.addRow(new Object[]{
                    c.getId(),
                    c.getNom(),
                    c.getPrenom(),
                    c.getTel(),
                    c.getEmail()
            });
        }
    }


    public void ajouterClient() {
        String nom    = view.getNom();
        String prenom = view.getPrenom();
        String tel    = view.getTelephone();
        String email  = view.getEmail();
        if (nom.isEmpty() || prenom.isEmpty() || tel.isEmpty() || email.isEmpty()) return;

        int newId = hotel.getClients().stream()
                .mapToInt(Client::getId)
                .max()
                .orElse(0) + 1;
        Client c = new Client(newId, nom, prenom, tel, email, new ArrayList<>());
        c.setId(newId);
        hotel.ajouterClient(c);
        loadClients();

        if (reservationController != null) {
            reservationController.loadClients(hotel.getClients());
        }
    }

    public void modifierClient() {
        int sel = view.getTable().getSelectedRow();
        if (sel < 0) return;
        Client c = hotel.getClients().get(sel);
        c.setNom(view.getNom());
        c.setPrenom(view.getPrenom());
        c.setTel(view.getTelephone());
        c.setEmail(view.getEmail());
        loadClients();
    }

    public void supprimerClient() {
        int sel = view.getTable().getSelectedRow();
        if (sel < 0) return;
        hotel.getClients().remove(sel);
        loadClients();
        if (reservationController != null) {
            reservationController.loadClients(hotel.getClients());
        }
    }

    public void rechercherClient() {
        String filtre = view.getNom().toLowerCase();
        DefaultTableModel m = view.getModel();
        m.setRowCount(0);
        for (Client c : hotel.getClients()) {
            if (c.getNom().toLowerCase().contains(filtre)) {
                m.addRow(new Object[]{c.getId(), c.getNom(), c.getPrenom(), c.getTel(), c.getEmail()});
            }
        }
    }
}
