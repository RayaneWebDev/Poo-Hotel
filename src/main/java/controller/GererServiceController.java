package controller;

import model.Accueil;
import view.GererServiceView;

import java.util.Date;
import java.util.List;

public class GererServiceController {

    private final GererServiceView view;
    private final List<Accueil> services;
    private int nextId = 3; // ou commence à services.size() + 1

    public GererServiceController(GererServiceView view, List<Accueil> services) {
        this.view = view;
        this.services = services;

        view.getBtnAjouter().addActionListener(e -> ajouter());
        view.getBtnModifier().addActionListener(e -> modifier());
        view.getBtnSupprimer().addActionListener(e -> supprimer());
    }

    private void ajouter() {
        String nom = view.getNomService();
        if (nom.isEmpty()) {
            view.showMessage("Le nom du service est requis.");
            return;
        }

        Accueil a = new Accueil(nextId++, nom, new Date());
        services.add(a);
        view.refreshTable(services);
        view.clearInput();
        view.showMessage("Service ajouté.");
    }

    private void modifier() {
        int selectedRow = view.getTable().getSelectedRow();
        if (selectedRow == -1) {
            view.showMessage("Sélectionnez un service à modifier.");
            return;
        }

        String nouveauNom = view.getNomService();
        if (nouveauNom.isEmpty()) {
            view.showMessage("Le nom ne peut pas être vide.");
            return;
        }

        int id = (int) view.getTable().getValueAt(selectedRow, 0);
        for (Accueil a : services) {
            if (a.getID_service() == id) {
                a.setNom_service(nouveauNom);
                break;
            }
        }

        view.refreshTable(services);
        view.clearInput();
        view.showMessage("Service modifié.");
    }

    private void supprimer() {
        int selectedRow = view.getTable().getSelectedRow();
        if (selectedRow == -1) {
            view.showMessage("Sélectionnez un service à supprimer.");
            return;
        }

        int id = (int) view.getTable().getValueAt(selectedRow, 0);
        services.removeIf(a -> a.getID_service() == id);
        view.refreshTable(services);
        view.showMessage("Service supprimé.");
    }
}
