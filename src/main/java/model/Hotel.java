package model;

import java.util.ArrayList;
import java.util.List;

public class Hotel {
    private int Id_Hotel;
    private String nom_hotel;
    private int note;
    private String adresse;
    private int nbr_chambre;
    private List<Receptionniste> receptionnistes = new ArrayList<>();
    private List<Client> clients = new ArrayList<>();
    private List<Chambre> chambres = new ArrayList<>();
    private List<ProduitMinibar> produitMinibars = new ArrayList<>();
    private List<Reservation> reservations = new ArrayList<>();
    private List<Sejour> sejours = new ArrayList<>();

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
    public void ajouterReceptionniste( Receptionniste receptionniste){
        if (!receptionnistes.contains(receptionniste)) {
            receptionnistes.add(receptionniste);
        }
    }
    public void supprimerReceptionniste(Receptionniste receptionniste){
        if (receptionnistes != null && receptionnistes.contains(receptionniste)) {
            receptionnistes.remove(receptionniste);
        }
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

    public void ajouterChambre(Chambre chambre) {
        if (!chambres.contains(chambre)) {
            chambres.add(chambre);
            nbr_chambre++;
        }
    }

    public void supprimerChambre(Chambre chambre) {
        if (chambres != null && chambres.contains(chambre)) {
            chambres.remove(chambre);
            nbr_chambre--;
        }
    }

    public void addReservation(Reservation reservation){
        this.reservations.add(reservation);
    }
    public void supprimerReservation(Reservation reservation){
        if (reservations != null && reservations.contains(reservation)) {
            reservations.remove(reservation);
        }
    }

    public List<Reservation> getReservations() {
        return reservations;
    }
    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public List<Sejour> getSejours() {
        return sejours;
    }

    public void setSejours(List<Sejour> sejours) {
        this.sejours = sejours;
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
    public void supprimerProdMinibar(ProduitMinibar produitMinibar){
        if (produitMinibars != null && produitMinibars.contains(produitMinibar)) {
            produitMinibars.remove(produitMinibar);
        }
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    public void ajouterClient(Client client) {
        if (!clients.contains(client)) {
            clients.add(client);
        }
    }

}

