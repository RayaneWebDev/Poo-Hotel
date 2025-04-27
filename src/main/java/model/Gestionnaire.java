package model;

import java.util.List;
import java.util.ArrayList;
import java.util.Objects;

public class Gestionnaire extends Personne {
    private List<ProduitMinibar> produitMinibars;

    public Gestionnaire(int ID_user, String nom, String prenom, String tel) {
        super(ID_user, nom, prenom, tel, "gestionnaire");
        this.produitMinibars=new ArrayList<>();
    }
    public void setProduitMinibars(List<ProduitMinibar> produitMinibars){
        if (produitMinibars != null) {
            this.produitMinibars = produitMinibars;
        } else {
            this.produitMinibars = new ArrayList<>();
        }
    }
    public List<ProduitMinibar> getProduitMinibars(){
        return produitMinibars;
    }
    public void addProduitsMiniBar(ProduitMinibar produitMinibar){
        this.produitMinibars.add(produitMinibar);
    }

    public void removeProduitMinibar(ProduitMinibar produitMinibar) {
        if (produitMinibar != null) {
            this.produitMinibars.remove(produitMinibar);
        }
    }

    public int getNombreDeProduits() {
        return produitMinibars.size();
    }

    @Override
    public String toString() {
        return "Gestionnaire{" +
                "ID_user=" + getID_user() +
                ", nom=" + getNom() +
                ", prenom=" + getPrenom() +
                ", tel=" + getTel() +
                ", produitMinibars=" + produitMinibars +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Gestionnaire)) return false;
        if (!super.equals(o)) return false;
        Gestionnaire that = (Gestionnaire) o;
        return Objects.equals(produitMinibars, that.produitMinibars);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), produitMinibars);
    }

}
