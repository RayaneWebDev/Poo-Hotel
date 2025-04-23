package model;

import java.util.List;

public class FemmeDeMenage extends Personne {

    private List<Menage> menages;

    public FemmeDeMenage(int ID_user, String nom, String prenom, String tel) {
        super(ID_user, nom, prenom, tel, "femme de menage");
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
