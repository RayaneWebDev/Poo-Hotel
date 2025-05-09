package model;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

public class Menage extends Service {
    private String nom_service;
    private Date date;
    private LocalTime heure;
    private int numchambre;
    private List<FemmeDeMenage> femmesDeMenage;

    public Menage(String nom_service, Date date, LocalTime heure, int id_service, int numchambre, List<FemmeDeMenage> femmesDeMenage) {
        super(id_service);
        this.nom_service = nom_service;
        this.date = date;
        this.heure = heure;
        this.numchambre = numchambre;
        this.femmesDeMenage = femmesDeMenage;
    }

    public String getNomService() {
        return nom_service;
    }

    public void setNomService(String nom_service) {
        this.nom_service = nom_service;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public LocalTime getHeure() {
        return heure;
    }

    public void setHeure(LocalTime heure) {
        this.heure = heure;
    }

    public int getNumChambre() {
        return numchambre;
    }

    public void setNumChambre(int numchambre) {
        this.numchambre = numchambre;
    }

    public List<FemmeDeMenage> getFemmesDeMenage() {
        return femmesDeMenage;
    }

    public void setFemmesDeMenage(List<FemmeDeMenage> femmesDeMenage) {
        this.femmesDeMenage = femmesDeMenage;
    }

    public void ajouterFemmeMenage(FemmeDeMenage femmeDeMenage) {
        if (!femmesDeMenage.contains(femmeDeMenage)) {
            femmesDeMenage.add(femmeDeMenage);
        }
    }

    public void supprimerFemmeMenage(FemmeDeMenage femmeDeMenage) {
        if (femmesDeMenage != null && femmesDeMenage.contains(femmeDeMenage)) {
            femmesDeMenage.remove(femmeDeMenage);
        }
    }

    @Override
    public String toString() {
        return "Ménage{" +
                "id=" + getID_service() +
                ", nom_service='" + nom_service + '\'' +
                ", date=" + date +
                ", heure=" + (heure != null ? heure.toString() : "Non spécifiée") +
                ", chambre=" + numchambre +
                ", nbFemmes=" + (femmesDeMenage != null ? femmesDeMenage.size() : 0) +
                '}';
    }
}
