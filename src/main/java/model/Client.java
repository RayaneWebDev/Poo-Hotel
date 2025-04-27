package model;
import java.util.ArrayList;
import java.util.List;

public class Client extends Personne {
    private List<Reservation> reservations;
    private List<Plainte> plaintes;

    public Client(int ID_user, String nom, String prenom, String tel,
                  List<Reservation> reservations) {
        super(ID_user, nom, prenom, tel, "client");
        this.reservations = reservations;
        this.plaintes = plaintes;
    }

    public void addReservation(Reservation reservation) {
        if(reservation==null){
            reservations=new ArrayList<>();
        }
        reservations.add(reservation);
    }


    public void addPlainte(Plainte plainte) {
        if(plaintes==null){
            plaintes=new ArrayList<>();
        }
        plaintes.add(plainte);
    }

    public List<Reservation> getReservations() {

        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {

        this.reservations = reservations;
    }

    public List<Plainte> getPlaintes() {
        return plaintes;
    }

    public void setPlaintes(List<Plainte> plaintes) {
        this.plaintes = plaintes;
    }

    @Override
    public String toString() {
        return "Client{" +
                "ID=" + getID_user() +
                ", nom='" + getNom() + '\'' +
                ", prenom='" + getPrenom() + '\'' +
                ", tel='" + getTel() + '\'' +
                ", nbReservations=" + (reservations != null ? reservations.size() : 0) +
                ", nbPlaintes=" + (plaintes != null ? plaintes.size() : 0) +
                '}';
    }

}
