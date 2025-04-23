package model;

import java.util.List;

public class Gestionnaire extends Personne {
    private List<ProduitMinibar> produitMinibars;
    public Gestionnaire(int ID_user, String nom, String prenom, String tel) {
        super(ID_user, nom, prenom, tel, "gestionnaire");
    }
    public void setProduitMinibars(List<ProduitMinibar> produitMinibars){
        this.produitMinibars = produitMinibars ;
    }
    public List<ProduitMinibar> getProduitMinibars(){
        return produitMinibars;
    }
    public void addProduitsMiniBar(ProduitMinibar produitMinibar){
        this.produitMinibars.add(produitMinibar);
    }

}
