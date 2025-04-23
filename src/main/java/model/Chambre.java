package model;

import java.util.List;

public class Chambre {
    private int ID_chambre;
    private Caregorie_Chambre categorie;
    private List<Menage> menages ;

    public Chambre(int ID_chambre, Caregorie_Chambre categorie) {
        this.ID_chambre = ID_chambre;
        this.categorie = categorie;
    }

    public int getID_chambre() {
        return ID_chambre;
    }

    public void setID_chambre(int ID_chambre) {
        this.ID_chambre = ID_chambre;
    }

    public Caregorie_Chambre getCategorie() {
        return categorie;
    }

    public void setCategorie(Caregorie_Chambre categorie) {
        this.categorie = categorie;
    }

    public List<Menage> getMenages() {
        return menages;
    }
    public void setMenages(List<Menage> menages) {
        this.menages = menages;
    }
    public void addMenage(Menage menage) {
        this.menages.add(menage);
    }
}
