package model;

import java.util.List;
import java.util.ArrayList;
import java.util.Objects;

public class Chambre {
    private int ID_chambre;
    private Categorie_Chambre categorie;
    private List<Menage> menages ;

    public Chambre(int ID_chambre, Categorie_Chambre categorie) {
        this.ID_chambre = ID_chambre;
        this.categorie = categorie;
        this.menages=new ArrayList<>();
    }

    public int getID_chambre() {
        return ID_chambre;
    }

    public void setID_chambre(int ID_chambre) {
        this.ID_chambre = ID_chambre;
    }

    public Categorie_Chambre getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie_Chambre categorie) {
        this.categorie = categorie;
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

    @Override
    public String toString() {
        return "Chambre{" +
                "ID_chambre=" + ID_chambre +
                ", categorie=" + categorie +
                ", menages=" + menages +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Chambre)) return false;
        Chambre chambre = (Chambre) o;
        return ID_chambre == chambre.ID_chambre &&
                Objects.equals(categorie, chambre.categorie) &&
                Objects.equals(menages, chambre.menages);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID_chambre, categorie, menages);
    }
}
