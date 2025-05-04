package model;

public class ProduitMinibar {
    private int Id_produit;
    private String nomProduit;
    private int quantite ;
    private float prix;

    public ProduitMinibar(int Id_produit, String nomProduit, float prix, int quantite) {
        this.Id_produit = Id_produit;
        this.nomProduit = nomProduit;
        this.prix = prix;
        this.quantite = quantite;
    }
    public int getQuantite(){
        return this.quantite;
    }
    public void setQuantite(int quantite){
        this.quantite = quantite;
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

    public void afficher(){
        System.out.println("ID_Produit : "+getId_produit());
        System.out.println("Nom du Produit : "+getNomProduit());
        System.out.println("Prix : "+getPrix());
    }
}
