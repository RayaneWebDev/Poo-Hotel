package model;
import model.Accueil;
import model.Bagagiste;  // J'imagine que Bagagiste existe dans ton projet
import java.util.Date;
public class Main {

    public static void main(String[] args) {
        // Créer un objet Accueil
        Accueil accueil = new Accueil(1, "Service Bagages", new Date());

        // Afficher l'objet avec toString
        System.out.println("Contenu de l'accueil:");
        System.out.println(accueil);

        // Créer des bagagistes (il te faut une classe Bagagiste simple)
        Bagagiste bagagiste1 = new Bagagiste(101, "Ali");
        Bagagiste bagagiste2 = new Bagagiste(102, "Sophie");

        // Ajouter les bagagistes
        accueil.addBagagiste(bagagiste1);
        accueil.addBagagiste(bagagiste2);

        // Afficher les bagagistes
        System.out.println("\nListe des bagagistes:");
        for (Bagagiste b : accueil.getBagagistes()) {
            System.out.println(b);
        }

        // Tester removeBagagiste
        accueil.removeBagagiste(bagagiste1);

        // Vérifier la liste après suppression
        System.out.println("\nAprès suppression d'un bagagiste:");
        for (Bagagiste b : accueil.getBagagistes()) {
            System.out.println(b);
        }

        // Tester les getters/setters
        accueil.setNom_service("Nouveau Service");
        System.out.println("\nNouveau nom du service: " + accueil.getNom_service());
    }
}





