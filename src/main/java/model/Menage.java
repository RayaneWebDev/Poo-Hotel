package model;

import java.util.Date;
import java.util.List;

public class Menage extends Service {
    private String nom_service;
    private Date date;
    private List<FemmeDeMenage> femmesDeMenage;

    public Menage(String nom_service, Date date, int id_service,Service service,List <FemmeDeMenage> femmesDeMenage) {
        super(id_service);
        this.nom_service = nom_service;
        this.date = date;
        this.femmesDeMenage = femmesDeMenage;

    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public void setFemmesDeMenage(List <FemmeDeMenage> femmesDeMenage){
        this.femmesDeMenage = femmesDeMenage;
    }
    public List<FemmeDeMenage> getFemmesDeMenage(){
        return this.femmesDeMenage;
    }

}
