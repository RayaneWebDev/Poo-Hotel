package model;

import java.util.List;

public class Bagagiste extends Personne {
    private List<Accueil> accueils ;
    public Bagagiste(int ID_user, String nom, String prenom, String tel) {
        super(ID_user, nom, prenom, tel, "bagagiste");
    }
    public void addAccueil(Accueil accueil) {
        this.accueils.add(accueil);
    }
    public List<Accueil> getAccueils(){
        return this.accueils;
    }
    public void setAccueils(List<Accueil> accueils) {
        this.accueils = accueils;
    }
}
