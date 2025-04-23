package model;

import java.util.List;

public class Hotel {
    private int Id_Hotel;
    private String nom_hotel;
    private int note;
    private String adresse;
    private int nbr_chambre;
    private List<Receptionniste> receptionnistes;
    private List<Chambre> chambres;
    private List<ProduitMinibar> produitMinibars;
    private List<Reservation> reservations;

    public Hotel(int Id_Hotel, String nom_hotel, int note, String adresse, int nbr_chambre,
                 List<Receptionniste> receptionnistes, List<Chambre> chambres) {
        this.Id_Hotel = Id_Hotel;
        this.nom_hotel = nom_hotel;
        this.note = note;
        this.adresse = adresse;
        this.nbr_chambre = nbr_chambre;
        this.receptionnistes = receptionnistes;
        this.chambres = chambres;
    }

    public int getId_Hotel() {
        return Id_Hotel;
    }

    public void setId_Hotel(int id_Hotel) {
        Id_Hotel = id_Hotel;
    }

    public String getNom_hotel() {
        return nom_hotel;
    }

    public void setNom_hotel(String nom_hotel) {
        this.nom_hotel = nom_hotel;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public int getNbr_chambre() {
        return nbr_chambre;
    }

    public void setNbr_chambre(int nbr_chambre) {
        this.nbr_chambre = nbr_chambre;
    }

    public List<Receptionniste> getReceptionnistes() {
        return receptionnistes;
    }

    public void setReceptionnistes(List<Receptionniste> receptionnistes) {
        this.receptionnistes = receptionnistes;
    }

    public List<Chambre> getChambres() {
        return chambres;
    }

    public void setChambres(List<Chambre> chambres) {
        this.chambres = chambres;
    }

    public void addReceptionniste(Receptionniste receptionniste) {
        this.receptionnistes.add(receptionniste);
    }
    public void addChambre(Chambre chambre) {
        this.chambres.add(chambre);
    }

    public void addReservation(Reservation reservation){
        this.reservations.add(reservation);
    }

    public void setProduitMinibars(List<ProduitMinibar> produitMinibars) {
        this.produitMinibars = produitMinibars;
    }
    public List<ProduitMinibar> getProduitMinibars() {
        return produitMinibars;
    }
    public void addProduitMinibar(ProduitMinibar produit) {
        this.produitMinibars.add(produit);
    }
}

