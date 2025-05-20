package model;

import java.util.List;

public class Receptionniste extends Personne {

    List<Reservation> reservations;
    public Receptionniste(int ID_user, String nom, String prenom, String tel) {
        super(ID_user, nom, prenom, tel, "receptionniste");
    }
    public void addReservation(Reservation reservation){
        this.reservations.add(reservation);
    }
    public void supprmierReservation(Reservation reservation){
        if (reservations.contains(reservation)){
            this.reservations.remove(reservation);
        }
    }
}
