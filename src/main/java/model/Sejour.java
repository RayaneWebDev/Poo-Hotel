package model;

import java.util.List;

public class Sejour {
    private int ID_sejour;
    List<ProduitMinibar> produitsConsommes;
    private Reservation reservation;

    public Sejour(int ID_sejour, Reservation reservation) {
        this.ID_sejour = ID_sejour;
        this.reservation = reservation;
    }

    public int getID_sejour() {
        return ID_sejour;
    }

    public void setID_sejour(int ID_sejour) {
        this.ID_sejour = ID_sejour;
    }
    public List<ProduitMinibar> getProduitMinibar() {
        return produitsConsommes;
    }
    public void setProduitMinibar(List<ProduitMinibar> prodMinibar) {
        this.produitsConsommes = prodMinibar;
    }
    public void addProduitMinibar(ProduitMinibar produitMinibar) {
        this.produitsConsommes.add(produitMinibar);
    }
    public void afficherProduitsConsommes() {
        System.out.println("Produits consommés : ");
        for (ProduitMinibar p : produitsConsommes) {
            p.afficher();
        }
    }
    public float calculerTotalConsommation() {
        float total = 0;
        for (ProduitMinibar p : produitsConsommes) {
            total += p.getPrix();
        }
        total += reservation.getPrix();
        return total;
    }
}
