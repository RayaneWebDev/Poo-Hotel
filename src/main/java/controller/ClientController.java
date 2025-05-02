package controller ;
import model.Client;
import model.Reservation;
import view.GestionClientsView;

import java.util.ArrayList;
import java.util.List;

public class ClientController {
    private GestionClientsView view;
    private ArrayList<Client> clients;

    public ClientController(GestionClientsView view) {
        this.view = view; // Lier la vue au contrôleur
        this.clients = new ArrayList<>();
    }

    public void loadClients() {
        if (view != null) {
            view.getModel().setRowCount(0); // Clear
            for (Client c : clients) {
                view.getModel().addRow(new Object[]{c.getId(), c.getNom(), c.getPrenom(), c.getTel()});
            }
        }
    }

    public void ajouterClient() {
        String nom = view.getNom(), prenom = view.getPrenom(), tel = view.getTelephone();
        if (nom.isEmpty() || prenom.isEmpty() || tel.isEmpty()) return;
        int id = clients.size() + 1;
        Client c = new Client(id, nom, prenom, tel, new ArrayList<Reservation>());
        clients.add(c);
        loadClients();
    }

    public void modifierClient() {
        int selected = view.getTable().getSelectedRow();
        if (selected == -1) return;
        Client c = clients.get(selected);
        c.setNom(view.getNom());
        c.setPrenom(view.getPrenom());
        c.setTel(view.getTelephone());
        loadClients();
    }

    public void supprimerClient() {
        int selected = view.getTable().getSelectedRow();
        if (selected == -1) return;
        clients.remove(selected);
        loadClients();
    }

    public void rechercherClient() {
        String nom = view.getNom();
        view.getModel().setRowCount(0);
        for (Client c : clients) {
            if (c.getNom().toLowerCase().contains(nom.toLowerCase())) {
                view.getModel().addRow(new Object[]{c.getId(), c.getNom(), c.getPrenom(), c.getTel()});
            }
        }
    }
}
