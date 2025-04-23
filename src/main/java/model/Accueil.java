package model;

import java.util.Date;
import java.util.List;

public class Accueil extends Service {
    private String nom_service = "bagagierService";
    private Date date;
    private List<Bagagiste> bagagistes;
    public Accueil(int ID_service, String nom_service,Date date) {
        super(ID_service);
        this.nom_service = nom_service;
        this.date = date;
    }
    public void addBagagiste(Bagagiste bagagiste){
        this.addBagagiste(bagagiste);
    }
    public List<Bagagiste> getBagagistes(){
        return this.bagagistes;
    }
    public void setBagagistes(List<Bagagiste> bagagistes){
        this.bagagistes = bagagistes;
    }
}
