package model;

public class Plainte extends Service {
    private int ID_complaint;
    private String objet;
    private String message;
    private Client client;
    private Receptionniste receptionniste;
    public Plainte(int ID_complaint, String objet, String message , Client client) {
        super(ID_complaint);
        this.ID_complaint = ID_complaint;
        this.objet = objet;
        this.client = client;
        this.message = message;
    }

    public Plainte(int ID_complaint, String objet, String message, Client client, Receptionniste receptionniste) {
        super(ID_complaint);
        this.ID_complaint = ID_complaint;
        this.objet = objet;
        this.message = message;
        this.client = client;
        this.receptionniste = receptionniste;

    }

    public int getID_complaint() {
        return ID_complaint;
    }

    public void setID_complaint(int ID_complaint) {
        this.ID_complaint = ID_complaint;
    }

    public String getObjet() {
        return objet;
    }

    public void setObjet(String objet) {
        this.objet = objet;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Client getClient() {
        return client;
    }
    public void setClient(Client client) {
        this.client = client;
    }

    public void setReceptionniste(Receptionniste receptionniste){
        this.receptionniste = receptionniste;
    }
    public Receptionniste getReceptionniste(){
        return this.receptionniste;
    }


}
