package controller;


import model.Chambre;
import view.GestionChambres;

public class ChambreControleur {

    private GestionChambres view;

    public ChambreControleur(GestionChambres view) {
        this.view = view;
    }

    public void ajouterChambre() {
        // Récupère les champs depuis la vue
        String numero = view.getNumero();
        String type = String.valueOf(view.getType());
        String prix = view.getPrix();
        String etat = view.getEtat();

        // TODO : Valider et ajouter la chambre
        System.out.println("Chambre ajoutée : " + numero);
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

