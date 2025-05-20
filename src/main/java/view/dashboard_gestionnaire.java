package view;

import controller.*;
import model.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class dashboard_gestionnaire extends JFrame implements ActionListener {

    JButton exit_btn;
    JButton consulter_btn;
    JButton gerer_produit_btn;
    JButton gerer_femmes_btn;
    JButton gerer_menages_btn;
    JButton gerer_bagagiste_btn;
    JButton affecter_bagagiste_btn;
    JButton gerer_services_btn;

    private JPanel contentPanel;
    private Consultationstock produitController;
    private FemmeDeMenageController femmesDeMenageController;
    private MenageController menageController;
    private ChambreControleur chambreControleur;
    private GererAcceuilController gererAcceuilController;
    private AffecterAccueilController affecterAccueilController;
    private GererServiceController gererServiceController;

    private Hotel hotel;
    private GererAcceuil gererAcceuilView;
    private AffecterAccueilView affecterAccueilView;
    private GererServiceView gererServiceView;

    private List<Bagagiste> listeBagagier;
    private List<Accueil> accueilList;
    private List<FemmeDeMenage> femmesList;
    private List<Chambre> chambresList;

    public dashboard_gestionnaire(Gestionnaire gestionnaire) {


        Chambre chambre1 = new Chambre(100, Categorie_Chambre.SIMPLE, "Disponible");
        Chambre chambre2 = new Chambre(101, Categorie_Chambre.SUITE_NORMALE,"Disponible" );
        Chambre chambre3 = new Chambre(102, Categorie_Chambre.SIMPLE, "Disponible");
        Chambre chambre4 = new Chambre(103, Categorie_Chambre.SUITE_PRESIDENTIELLE, "Disponible");
        List<Chambre> chambres = new ArrayList<>();
        chambres.add(chambre1);
        chambres.add(chambre2);
        chambres.add(chambre3);
        chambres.add(chambre4);

        Hotel hotel = new Hotel(1,"Dandy",5,"81 rue de paris 75006",4,null,chambres);
        hotel.setChambres(chambres);

        produitController = new Consultationstock();
        femmesDeMenageController = new FemmeDeMenageController();
        femmesList = femmesDeMenageController.getFemmes();
        chambresList = new ArrayList<>();
        chambreControleur = new ChambreControleur(new ChambreView(), hotel);

        listeBagagier = new ArrayList<>();
        accueilList = new ArrayList<>();

        gererAcceuilView = new GererAcceuil(listeBagagier);
        affecterAccueilView = new AffecterAccueilView(listeBagagier, accueilList);
        gererServiceView = new GererServiceView(accueilList);

        gererAcceuilController = new GererAcceuilController(gererAcceuilView, listeBagagier);
        affecterAccueilController = new AffecterAccueilController(affecterAccueilView, listeBagagier, accueilList);
        gererServiceController = new GererServiceController(gererServiceView, accueilList);
        menageController = new MenageController(femmesList, chambresList);

        setTitle("Dashboard Gestionnaire");
        setSize(1500, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(new Color(30, 144, 255));
        leftPanel.setLayout(new GridLayout(8, 1, 0, 10));
        leftPanel.setPreferredSize(new Dimension(250, 800));

        consulter_btn = new JButton("Consulter le stock");
        gerer_produit_btn = new JButton("Gérer les produits");
        gerer_femmes_btn = new JButton("Gérer les femmes de ménage");
        gerer_menages_btn = new JButton("Gérer les ménages");
        gerer_bagagiste_btn = new JButton("Gérer les Bagagistes");
        affecter_bagagiste_btn = new JButton("Affecter Bagagiste");
        gerer_services_btn = new JButton("Gérer les Services");
        exit_btn = new JButton("Exit");

        styleMenuButton(consulter_btn);
        styleMenuButton(gerer_produit_btn);
        styleMenuButton(gerer_femmes_btn);
        styleMenuButton(gerer_menages_btn);
        styleMenuButton(gerer_bagagiste_btn);
        styleMenuButton(affecter_bagagiste_btn);
        styleMenuButton(gerer_services_btn);
        styleExitButton(exit_btn);

        leftPanel.add(consulter_btn);
        leftPanel.add(gerer_produit_btn);
        leftPanel.add(gerer_femmes_btn);
        leftPanel.add(gerer_menages_btn);
        leftPanel.add(gerer_bagagiste_btn);
        leftPanel.add(affecter_bagagiste_btn);
        leftPanel.add(gerer_services_btn);
        leftPanel.add(exit_btn);

        consulter_btn.addActionListener(this);
        gerer_produit_btn.addActionListener(this);
        gerer_femmes_btn.addActionListener(this);
        gerer_menages_btn.addActionListener(this);
        gerer_bagagiste_btn.addActionListener(this);
        affecter_bagagiste_btn.addActionListener(this);
        gerer_services_btn.addActionListener(this);
        exit_btn.addActionListener(this);

        add(leftPanel, BorderLayout.WEST);

        contentPanel = new JPanel(new BorderLayout());
        add(contentPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    private void styleMenuButton(JButton button) {
        button.setFocusPainted(false);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setBackground(Color.WHITE);
        button.setForeground(Color.BLACK);
        button.setBorderPainted(false);
    }

    private void styleExitButton(JButton button) {
        button.setFocusPainted(false);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setBackground(Color.RED);
        button.setForeground(Color.WHITE);
        button.setBorderPainted(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        contentPanel.removeAll();

        if (e.getSource() == consulter_btn) {
            contentPanel.add(new ConsultationStock(produitController), BorderLayout.CENTER);
        } else if (e.getSource() == gerer_produit_btn) {
            contentPanel.add(new GestionProduitView(produitController), BorderLayout.CENTER);
        } else if (e.getSource() == gerer_femmes_btn) {
            contentPanel.add(new GestionFemmesMenageView(femmesDeMenageController), BorderLayout.CENTER);
        } else if (e.getSource() == gerer_menages_btn) {
            contentPanel.add(new GestionMenagesView(menageController), BorderLayout.CENTER);
        } else if (e.getSource() == gerer_bagagiste_btn) {
            gererAcceuilView.refreshTable(listeBagagier);
            contentPanel.add(gererAcceuilView, BorderLayout.CENTER);
        } else if (e.getSource() == affecter_bagagiste_btn) {
            affecterAccueilView.refreshAssignments(listeBagagier, accueilList); // Both lists
            contentPanel.add(affecterAccueilView, BorderLayout.CENTER);
        } else if (e.getSource() == gerer_services_btn) {
            gererServiceView.refreshTable(accueilList);
            contentPanel.add(gererServiceView, BorderLayout.CENTER);
        } else if (e.getSource() == exit_btn) {
            System.exit(0);
        }

        contentPanel.revalidate();
        contentPanel.repaint();
    }

}
