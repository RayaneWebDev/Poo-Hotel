package model;

public class ProduitMinibar {
    private int Id_produit;
    private String nomProduit;
    private float prix;

    public ProduitMinibar(int Id_produit, String nomProduit, float prix) {
        this.Id_produit = Id_produit;
        this.nomProduit = nomProduit;
        this.prix = prix;
    }

    public int getId_produit() {
        return Id_produit;
    }

    public void setId_produit(int id_produit) {
        Id_produit = id_produit;
    }

    public String getNomProduit() {
        return nomProduit;
    }

    public void setNomProduit(String nomProduit) {
        this.nomProduit = nomProduit;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }
}
