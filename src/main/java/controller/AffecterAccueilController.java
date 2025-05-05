package controller;

import model.Accueil;
import model.Bagagiste;
import view.AffecterAccueilView;

import java.util.List;

public class AffecterAccueilController {

    private AffecterAccueilView view;
    private List<Bagagiste> bagagistes;
    private List<Accueil> accueils;

    public AffecterAccueilController(AffecterAccueilView view, List<Bagagiste> bagagistes, List<Accueil> accueils) {
        this.view = view;
        this.bagagistes = bagagistes;
        this.accueils = accueils;

        view.getAffecterBtn().addActionListener(e -> affecter());

        // Populate table from existing assignments
        view.refreshAssignments(accueils);
    }

    private void affecter() {
        Bagagiste b = view.getSelectedBagagiste();
        Accueil a = view.getSelectedAccueil();

        if (b == null || a == null) {
            view.showMessage("Veuillez sélectionner un bagagiste et un service.");
            return;
        }

        if (!b.getAccueils().contains(a)) {
            b.addAccueil(a);
            a.addBagagiste(b);
            view.addRow(b, a);
            view.showMessage("Affectation réussie !");
            accueils.add(a);
        } else {
            view.showMessage("Ce bagagiste est déjà affecté à ce service.");
        }
    }
}
