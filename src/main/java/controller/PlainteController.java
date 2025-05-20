package controller;

import model.Plainte;
import model.Client;
import view.PlainteView;

import java.util.ArrayList;
import java.util.List;

public class PlainteController {
    private PlainteView view;
    private List<Plainte> plaintes;

    public PlainteController(PlainteView view) {
        this.view = view;
        this.plaintes = new ArrayList<>();

        // Simuler des plaintes externes
        Client c1 = new Client(1, "Durand", "Alice", "0620202020", "alice@example.com");
        Client c2 = new Client(2, "Moreau", "Julien", "0610101010", "julien@example.com");

        plaintes.add(new Plainte(101, "Propreté", "La chambre était sale", c1));
        plaintes.add(new Plainte(102, "Bruit", "Trop de bruit la nuit", c2));
        plaintes.add(new Plainte(103, "Service", "Réception fermée le soir", c1));
    }

    public void chargerPlaintes() {
        view.clearTable();
        for (Plainte p : plaintes) {
            view.ajouterPlainteDansTable(p);
        }
    }
}
