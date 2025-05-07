package controller;

import model.Categorie_Chambre;
import model.Chambre;
import view.GestionChambres;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;

public class ChambreControleur {

    private final GestionChambres view;
    private final List<Chambre> chambres;

    public ChambreControleur(GestionChambres view) {
        this.view = view;
        this.chambres = new ArrayList<>();
    }

    public void ajouterChambre() {
        String numero = view.getNumero();
        String type = view.getType();
        String etat = view.getEtat();

        view.getModel().addRow(new Object[]{numero, type, "", etat});
    }

    public void modifierChambre() {
        System.out.println("Modifier chambre");
    }

    public void supprimerChambre() {
        System.out.println("Supprimer chambre");
    }

    public void rechercherChambre() {
        System.out.println("Rechercher chambre");
    }

    public void chargerChambres() {
        DefaultTableModel model = view.getModel();
        model.setRowCount(0);
        for (Chambre c : chambres) {
            model.addRow(new Object[]{
                    c.getID_chambre(),
                    c.getCategorie().name(),
                    c.getPrix(),
                    convertirEtat(c.getEtat())
            });
        }
    }

    private String convertirEtat(int etat) {
        return switch (etat) {
            case 0 -> "Disponible";
            case 1 -> "Occupée";
            case 2 -> "En nettoyage";
            case 3 -> "En maintenance";
            case 4 -> "Réservée";
            default -> "Inconnu";
        };
    }
}