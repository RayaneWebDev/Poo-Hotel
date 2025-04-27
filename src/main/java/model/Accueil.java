package model;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.Objects;

public class Accueil extends Service {
    private String nom_service;
    private Date date;
    private List<Bagagiste> bagagistes;

    public Accueil(int ID_service, String nom_service,Date date) {
        super(ID_service);
        this.nom_service = nom_service != null ? nom_service : "bagagierService";
        this.date = date;
        this.bagagistes=new ArrayList<>();
    }

    public String getNom_service(){
        return nom_service;
    }

    public void setNom_service(String nom_service){
        this.nom_service=nom_service;
    }

    public Date getDate(){
        return date;
    }

    public void setDate(Date date){
        this.date=date;
    }


    public List<Bagagiste> getBagagistes(){
        return bagagistes;
    }

    public void setBagagistes(List<Bagagiste> bagagistes){
        this.bagagistes = bagagistes != null ? bagagistes : new ArrayList<>();
    }

    public void addBagagiste(Bagagiste bagagiste){
        if (bagagiste != null) {
            this.bagagistes.add(bagagiste);
        }
    }

    public void removeBagagiste(Bagagiste bagagiste){
        if (bagagiste != null) {
            this.bagagistes.remove(bagagiste);
        }
    }

    @Override
    public String toString(){
        return "Accueil{" +
                "ID_service=" + getID_service() +
                ", nom_service='" + nom_service + '\'' +
                ", date=" + date +
                ", bagagistes=" + bagagistes +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Accueil)) return false;
        if (!super.equals(o)) return false;
        Accueil accueil = (Accueil) o;
        return Objects.equals(nom_service, accueil.nom_service) &&
                Objects.equals(date, accueil.date) &&
                Objects.equals(bagagistes, accueil.bagagistes);
    }
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), nom_service, date, bagagistes);
    }
}
