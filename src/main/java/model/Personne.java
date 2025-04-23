package model;

public class Personne {
    protected int ID_user;
    protected String nom;
    protected String prenom;
    protected String tel;
    protected String role;

    public Personne(int ID_user, String nom, String prenom, String tel, String role) {
        this.ID_user = ID_user;
        this.nom = nom;
        this.prenom = prenom;
        this.tel = tel;
        this.role = role;
    }

    public int getID_user() {
        return ID_user;
    }

    public void setID_user(int ID_user) {
        this.ID_user = ID_user;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
