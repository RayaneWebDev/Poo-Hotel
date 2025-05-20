package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Bagagiste extends Personne {

    private List<Accueil> accueils ;

    public Bagagiste(int ID_user, String nom, String prenom, String tel) {
        super(ID_user, nom, prenom, tel, "bagagiste");
        this.accueils = new ArrayList<>();
    }

    public void addAccueil(Accueil accueil) {
        if(accueil !=null){
            this.accueils.add(accueil);
        }

    }

    public void removeAccueil(Accueil accueil){
        if(accueil !=null){
            this.accueils.remove(accueil);
        }
    }

    public List<Accueil> getAccueils(){
        return this.accueils;
    }


    public void setAccueils(List<Accueil> accueils) {
        if(accueils!=null){
            this.accueils = accueils;
        }else{
            this.accueils = new ArrayList<>();
        }
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Bagagiste{")
                .append("ID_user=").append(getID_user())
                .append(", nom=").append(getNom())
                .append(", prenom=").append(getPrenom())
                .append(", tel=").append(getTel())
                .append(", accueils=[");

        for (Accueil accueil : accueils) {
            sb.append(accueil.getNom_service()).append(", ");
        }

        if (!accueils.isEmpty()) {
            sb.setLength(sb.length() - 2); // remove last comma
        }

        sb.append("]}");
        return sb.toString();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Bagagiste)) return false;
        if (!super.equals(o)) return false;
        Bagagiste bagagiste = (Bagagiste) o;
        return Objects.equals(accueils, bagagiste.accueils);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), accueils);
    }


}
