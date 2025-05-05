package controller;


import model.Chambre;
import view.GestionChambres;

public class ChambreControleur {

    private GestionChambres view;

    public ChambreControleur(GestionChambres view) {
        this.view = view;
    }

    public void ajouterChambre() {
        String numero = view.getNumero();
        String type = view.getType();
        String prix = view.getPrix();
        String etat = view.getEtat();


        view.getModel().addRow(new Object[]{numero, type, prix, etat});


    }

    public void modifierChambre() {
        // TODO : Logique de modification
        System.out.println("Modifier chambre");
    }

    public void supprimerChambre() {
        // TODO : Logique de suppression
        System.out.println("Supprimer chambre");
    }

    public void rechercherChambre() {
        // TODO : Logique de recherche
        System.out.println("Rechercher chambre");
    }

    public void chargerChambres() {
        // TODO : Charger les chambres depuis la base ou un tableau
        System.out.println("Chargement des chambres");
    }
}

