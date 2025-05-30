package model;
import java.util.Date;

public class Reservation {
    private int ID_reservation;
    private int nbr_nuits;
    private Date date_debut;
    private Chambre chambre;

    private boolean status = false;

    public Reservation(int ID_reservation, int nbr_nuits, Date date_debut, Chambre chambre) {
        this.ID_reservation = ID_reservation;
        this.nbr_nuits = nbr_nuits;
        this.date_debut = date_debut;
        this.chambre = chambre;
    }


    public int getID_reservation() {
        return ID_reservation;
    }

    public void setID_reservation(int ID_reservation) {
        this.ID_reservation = ID_reservation;
    }

    public int getNbr_nuits() {
        return nbr_nuits;
    }

    public void setNbr_nuits(int nbr_nuits) {
        this.nbr_nuits = nbr_nuits;
    }

    public Date getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(Date date_debut) {
        this.date_debut = date_debut;
    }



    public float total_reservation(int nbr_nuit, float prix){
        float total = nbr_nuit*prix;
        return total ;
    }

    public Chambre getChambre() {
        return chambre;
    }
    public void setChambre(Chambre chambre) {
        this.chambre = chambre;
    }

    public float getPrix(){
        return chambre.getPrix();
    }


    public void afficherReservation() {
        System.out.println("ID: " + ID_reservation + " | Nuits: " + nbr_nuits + " | Prix chambre: " + chambre.getPrix());
    }


}