package model;
import java.util.List;

public class Client extends Personne {
    private int id;
    private List<Reservation> reservations;
    private List<Plainte> plaintes;

    public Client(int ID_user, String nom, String prenom, String tel,
                  List<Reservation> reservations) {
        super(ID_user, nom, prenom, tel, "client");
        this.reservations = reservations;
    }

    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }

    public void addReservation(Reservation reservation) {
        reservations.add(reservation);
    }
    public void addPlainte(Plainte plainte) {
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

    public void setTel(String tel) {
        this.tel = tel;
    }
    public String getTel() {
        return tel;
    }
}
