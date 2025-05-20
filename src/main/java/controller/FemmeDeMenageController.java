package controller;

import model.FemmeDeMenage;
import view.GestionFemmesMenageView;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FemmeDeMenageController {
    private List<FemmeDeMenage> femmes;

    public FemmeDeMenageController() {
        femmes = new ArrayList<>();
    }

    public void ajouterFemme(FemmeDeMenage femme) {
        femmes.add(femme);
    }

    public List<FemmeDeMenage> getFemmes() {
        return femmes;
    }

    public FemmeDeMenage trouverFemmeParId(int id) {
        return femmes.stream()
                .filter(f -> f.getID_user() == id)
                .findFirst()
                .orElse(null);
    }

    // ========== Méthodes liées à la Vue Swing ==========

    public void ajouterFemmeDepuisVue(GestionFemmesMenageView view) {
        String nom = view.getNom();
        String prenom = view.getPrenom();
        String tel = view.getTelephone();

        if (nom.isEmpty() || prenom.isEmpty() || tel.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Tous les champs sont obligatoires.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int id = femmes.size() + 1; // ID simple, à améliorer si besoin
        FemmeDeMenage femme = new FemmeDeMenage(id, nom, prenom, tel);
        ajouterFemme(femme);
        view.loadFemmes();
    }

    public void modifierFemmeDepuisVue(GestionFemmesMenageView view) {
        int selectedRow = view.getTable().getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(view, "Veuillez sélectionner une femme de ménage à modifier.", "Erreur", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int id = (int) view.getModel().getValueAt(selectedRow, 0);
        FemmeDeMenage femme = trouverFemmeParId(id);

        if (femme != null) {
            femme.setNom(view.getNom());
            femme.setPrenom(view.getPrenom());
            femme.setTel(view.getTelephone());
            view.loadFemmes();
        }
    }

    public void supprimerFemmeDepuisVue(GestionFemmesMenageView view) {
        int selectedRow = view.getTable().getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(view, "Veuillez sélectionner une femme de ménage à supprimer.", "Erreur", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int id = (int) view.getModel().getValueAt(selectedRow, 0);
        FemmeDeMenage femme = trouverFemmeParId(id);

        if (femme != null) {
            femmes.remove(femme);
            view.loadFemmes();
        }
    }

    public void rechercherFemmeDepuisVue(GestionFemmesMenageView view) {
        String nomRecherche = view.getNom().toLowerCase();
        List<FemmeDeMenage> resultats = femmes.stream()
                .filter(f -> f.getNom().toLowerCase().contains(nomRecherche))
                .collect(Collectors.toList());

        DefaultListModel model = new DefaultListModel();
        view.getModel().setRowCount(0); // On vide le tableau

        for (FemmeDeMenage f : resultats) {
            view.getModel().addRow(new Object[]{
                    f.getID_user(),
                    f.getNom(),
                    f.getPrenom(),
                    f.getTel(),
                    f.getMenages().size() + " ménage(s)"
            });
        }
    }
}
