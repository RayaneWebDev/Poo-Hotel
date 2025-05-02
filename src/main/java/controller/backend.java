package controller;

import view.*;
import model.*;

public class backend {

    private homepage homepage;

    public void startApp() {
        homepage = new homepage();
    }

    public void dashboard_receptionneste() {
        Receptionniste r = new Receptionniste(1, "Hosam", "Elhaddajy", "06");
        new dashbord_receptionist(r);
    }

    public void  dashboard_gestionnaire() {
        Gestionnaire g = new Gestionnaire(1, "Hosam", "Elhaddajy", "06");
        new dashboard_gestionnaire(g);
    }
}
