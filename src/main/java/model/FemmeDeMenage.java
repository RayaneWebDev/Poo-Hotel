package model;

import java.util.List;
import java.util.ArrayList;
import java.util.Objects;

public class FemmeDeMenage extends Personne {

    private List<Menage> menages;

    public FemmeDeMenage(int ID_user, String nom, String prenom, String tel) {
        super(ID_user, nom, prenom, tel, "femme de menage");
        this.menages=new ArrayList<>();
    }
    public List<Menage> getMenages() {
        return menages;
    }
    public void setMenages(List<Menage> menages) {
        if (menages != null) {
            this.menages = menages;
        } else {
            this.menages = new ArrayList<>();
        }
    }
    public void addMenage(Menage menage) {
        if (menage != null) {
            this.menages.add(menage);
        }
    }

    public void removeMenage(Menage menage) {
        if (menage != null) {
            this.menages.remove(menage);
        }
    }

    public int getNombreDeMenages() {
        return menages.size();
    }

    @Override
    public String toString() {
        return "FemmeDeMenage{" +
                "ID_user=" + getID_user() +
                ", nom=" + getNom() +
                ", prenom=" + getPrenom() +
                ", tel=" + getTel() +
                ", menages=" + menages +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FemmeDeMenage)) return false;
        if (!super.equals(o)) return false;
        FemmeDeMenage that = (FemmeDeMenage) o;
        return Objects.equals(menages, that.menages);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), menages);
    }

}
