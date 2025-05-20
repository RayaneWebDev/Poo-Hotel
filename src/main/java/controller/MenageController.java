package controller;

import model.Chambre;
import model.FemmeDeMenage;
import model.Menage;
import view.GestionMenagesView;

import javax.swing.*;
import java.time.LocalTime;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class MenageController {
    private List<Menage> menages;
    private List<FemmeDeMenage> femmes;
    private List<Chambre> chambres;


    public MenageController(List<FemmeDeMenage> femmes, List<Chambre> chambres) {
        this.femmes = femmes;
        this.chambres = chambres;
        this.menages = new ArrayList<>();
    }

    public void loadMenages(GestionMenagesView view) {
        view.getModel().setRowCount(0);
        for (Menage m : menages) {
            String femmesString = m.getFemmesDeMenage().stream()
                    .map(f -> f.getNom() + " " + f.getPrenom())
                    .reduce((a, b) -> a + ", " + b)
                    .orElse("Aucune");

            view.getModel().addRow(new Object[]{
                    m.getID_service(),
                    m.getDate(),
                    m.getHeure(),
                    m.getNumChambre(),
                    femmesString
            });
        }
        view.loadFemmes(femmes);
        view.loadChambres(chambres);
    }

    public void ajouterMenageDepuisVue(GestionMenagesView view) {
        String dateStr = view.getDate();
        String heureStr = view.getHeure();
        int numChambre = Integer.parseInt(view.getChambre()) ;
        FemmeDeMenage femme = view.getSelectedFemme();
        Chambre chambre = view.getSelectedChambre();


            if (dateStr.isEmpty() || heureStr.isEmpty() || view.getChambre().isEmpty() || femme == null) {
            JOptionPane.showMessageDialog(view, "Tous les champs sont obligatoires", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Conversion date
        Date date;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(view, "Format de date invalide. Utilisez yyyy-MM-dd.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Conversion heure
        LocalTime heure;
        try {
            heure = LocalTime.parse(heureStr); // format attendu : "HH:mm"
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Format d'heure invalide. Utilisez HH:mm.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Vérification femme
        boolean existe = femmes.stream().anyMatch(f -> f.getID_user() == femme.getID_user());
        if (!existe) {
            JOptionPane.showMessageDialog(view, "La femme de ménage sélectionnée n'existe pas.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Création du ménage
        int id = menages.size() + 1;
        List<FemmeDeMenage> affectees = new ArrayList<>();
        affectees.add(femme);

        Menage menage = new Menage("menage", date, heure, id, numChambre, affectees);
        menages.add(menage);

        // Mise à jour femme
        if (!femme.getMenages().contains(menage)) {
            femme.getMenages().add(menage);
        }

        loadMenages(view);
    }

    public void supprimerMenageDepuisVue(GestionMenagesView view) {
        int selectedRow = view.getTable().getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(view, "Sélectionnez un ménage à supprimer.", "Erreur", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int id = (int) view.getModel().getValueAt(selectedRow, 0);
        Menage menage = menages.stream().filter(m -> m.getID_service() == id).findFirst().orElse(null);

        if (menage != null) {
            // Supprimer dans chaque femme affectée
            for (FemmeDeMenage femme : menage.getFemmesDeMenage()) {
                femme.getMenages().remove(menage);
            }
            menages.remove(menage);
            loadMenages(view);
        }
    }

    public void rechercherMenageDepuisVue(GestionMenagesView view) {
        String chambreRecherche = view.getChambre().toLowerCase();
        List<Menage> resultat = menages.stream()
                .filter(m -> String.valueOf(m.getNumChambre()).equals(chambreRecherche))
                .toList();

        view.getModel().setRowCount(0);
        for (Menage m : resultat) {
            String femmesString = m.getFemmesDeMenage().stream()
                    .map(f -> f.getNom() + " " + f.getPrenom())
                    .reduce((a, b) -> a + ", " + b)
                    .orElse("Aucune");

            view.getModel().addRow(new Object[]{
                    m.getID_service(),
                    m.getDate(),
                    m.getHeure(),
                    m.getNumChambre(),
                    femmesString
            });
        }
    }
}
