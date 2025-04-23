package model;

import java.util.List;

public class Sejour {
    private int ID_sejour;
    List<ProduitMinibar> produitMinibar;
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
        return produitMinibar;
    }
    public void setProduitMinibar(List<ProduitMinibar> prodMinibar) {
        this.produitMinibar = prodMinibar;
    }
    public void addProduitMinibar(ProduitMinibar produitMinibar) {
        this.produitMinibar.add(produitMinibar);
    }
}
