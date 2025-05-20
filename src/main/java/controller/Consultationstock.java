package controller;

import model.ProduitMinibar;
import java.util.ArrayList;
import java.util.List;

public class Consultationstock {
    private List<ProduitMinibar> produitMinibar;

    public Consultationstock() {
        produitMinibar = new ArrayList<>();
    }

    public List<ProduitMinibar> AddProf(int id, String nom, float prix, int quantite) {
        ProduitMinibar p = new ProduitMinibar(id, nom, prix, quantite);
        produitMinibar.add(p);
        return produitMinibar;
    }

    public List<ProduitMinibar> Supprimer(int id) {
        produitMinibar.removeIf(p -> p.getId_produit() == id);
        return produitMinibar;
    }

    public List<ProduitMinibar> getProduits() {
        return produitMinibar;
    }
}
