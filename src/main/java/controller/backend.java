package controller;

import view.*;
import model.*;

public class backend {

    public static void main(String arg[]){
        Receptionniste r = new Receptionniste(1, "hosam", "elhaddajy", "06");
        dashbord_receptionist dr = new dashbord_receptionist(r);
    }

}