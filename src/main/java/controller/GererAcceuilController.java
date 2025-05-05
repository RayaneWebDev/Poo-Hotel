package controller;

import model.Bagagiste;
import view.GererAcceuil;

import javax.swing.table.DefaultTableModel;
import java.util.List;

public class GererAcceuilController {
    private GererAcceuil view;
    private List<Bagagiste> bagagistes;

    public GererAcceuilController(GererAcceuil view, List<Bagagiste> listeBagagistes) {
        this.view = view;
        this.bagagistes = listeBagagistes;

        // Attach action listeners
        view.getAjouterBtn().addActionListener(e -> ajouter());
        view.getSupprimerBtn().addActionListener(e -> supprimer());

        // Initial display
        afficher();
    }

    private void ajouter() {
        String nom = view.getNom();
        String prenom = view.getPrenom();
        String tel = view.getTelephone();

        if (!nom.isEmpty() && !prenom.isEmpty() && !tel.isEmpty()) {
            int newId = bagagistes.size() + 1;
            Bagagiste newBagagiste = new Bagagiste(newId, nom, prenom, tel);
            bagagistes.add(newBagagiste);
            view.getModel().addRow(new Object[]{newId, nom, prenom, tel});
            view.showMessage("Bagagiste ajouté !");
        } else {
            view.showMessage("Veuillez remplir tous les champs !");
        }
    }

    private void supprimer() {
        int selectedRow = view.getTable().getSelectedRow();
        if (selectedRow == -1) {
            view.showMessage("Sélectionnez un bagagiste à supprimer.");
            return;
        }

        int id = (int) view.getModel().getValueAt(selectedRow, 0);
        bagagistes.removeIf(b -> b.getID_user() == id);
        afficher();
        view.showMessage("Bagagiste supprimé.");
    }

    private void afficher() {
        DefaultTableModel model = view.getModel();
        model.setRowCount(0);
        for (Bagagiste b : bagagistes) {
            model.addRow(new Object[]{
                    b.getID_user(),
                    b.getNom(),
                    b.getPrenom(),
                    b.getTel()
            });
        }
    }
}
