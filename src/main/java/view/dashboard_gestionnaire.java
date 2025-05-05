package view;

import controller.AffecterAccueilController;
import controller.Consultationstock;
import controller.GererAcceuilController;
import model.Accueil;
import model.Bagagiste;
import model.Gestionnaire;
import controller.GererServiceController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class dashboard_gestionnaire extends JFrame implements ActionListener {

    private JButton exit_btn;
    private JButton consulter_btn;
    private JButton gerer_produit_btn;
    private JButton gerer_service_bagagiste;
    private JButton affecter_service_bagagiste;

    private JButton gerer_services_btn;

    private JPanel contentPanel;
    private final Consultationstock produitController;

    private  GererAcceuilController gererAcceuilController;

    private final List<Bagagiste> listeBagagistes = new ArrayList<>();
    private final List<Accueil> listeAccueils = new ArrayList<>();

    public dashboard_gestionnaire(Gestionnaire gestionnaire) {
        produitController = new Consultationstock();

        setTitle("Dashboard Gestionnaire");
        setSize(1500, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Menu gauche
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(new Color(30, 144, 255));
        leftPanel.setLayout(new GridLayout(6, 1, 0, 10));
        leftPanel.setPreferredSize(new Dimension(250, 800));

        consulter_btn = new JButton("Consulter le stock");
        gerer_produit_btn = new JButton("Gérer les produits");
        gerer_service_bagagiste = new JButton("Gérer les bagagistes");
        affecter_service_bagagiste = new JButton("Affecter un bagagiste");
        exit_btn = new JButton("Quitter");
        gerer_services_btn = new JButton("Gérer les services");





        styleMenuButton(consulter_btn, true);
        styleMenuButton(gerer_produit_btn, false);
        styleMenuButton(gerer_service_bagagiste, false);
        styleMenuButton(affecter_service_bagagiste, false);
        styleMenuButton(gerer_services_btn, false);
        styleExitButton(exit_btn);

        consulter_btn.addActionListener(this);
        gerer_produit_btn.addActionListener(this);
        gerer_service_bagagiste.addActionListener(this);
        affecter_service_bagagiste.addActionListener(this);
        gerer_services_btn.addActionListener(this);
        exit_btn.addActionListener(this);

        leftPanel.add(consulter_btn);
        leftPanel.add(gerer_produit_btn);
        leftPanel.add(gerer_service_bagagiste);
        leftPanel.add(affecter_service_bagagiste);
        leftPanel.add(gerer_services_btn);
        leftPanel.add(exit_btn);

        add(leftPanel, BorderLayout.WEST);

        // Zone centrale
        contentPanel = new JPanel(new BorderLayout());
        add(contentPanel, BorderLayout.CENTER);

        // Fake data
        chargerDonneesFictives();

        setVisible(true);
    }

    private void styleMenuButton(JButton button, boolean selected) {
        button.setFocusPainted(false);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setBackground(selected ? new Color(0, 0, 128) : Color.WHITE);
        button.setForeground(selected ? Color.WHITE : Color.BLACK);
        button.setBorderPainted(false);
    }

    private void styleExitButton(JButton button) {
        button.setFocusPainted(false);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setBackground(Color.RED);
        button.setForeground(Color.WHITE);
        button.setBorderPainted(false);
    }

    private void chargerDonneesFictives() {
        Bagagiste b1 = new Bagagiste(1, "Elhaddajy", "Soufian", "0600000000");
        Bagagiste b2 = new Bagagiste(2, "Doe", "John", "0700000000");

        listeBagagistes.add(b1);
        listeBagagistes.add(b2);

        Accueil a1 = new Accueil(1, "Porter bagages", new Date());
        Accueil a2 = new Accueil(2, "Accueil VIP", new Date());

        listeAccueils.add(a1);
        listeAccueils.add(a2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        contentPanel.removeAll();

        if (e.getSource() == gerer_produit_btn) {
            contentPanel.add(new GestionProduitView(produitController), BorderLayout.CENTER);

        } else if (e.getSource() == consulter_btn) {
            contentPanel.add(new ConsultationStock(produitController), BorderLayout.CENTER);
        }
            else if (e.getSource() == gerer_service_bagagiste) {
            GererAcceuil bagagisteView = new GererAcceuil(listeBagagistes, gererAcceuilController );

            new GererAcceuilController(bagagisteView, listeBagagistes);
                contentPanel.add(bagagisteView, BorderLayout.CENTER);
            } else if (e.getSource() == affecter_service_bagagiste) {
            AffecterAccueilView affecterAccueilView = new AffecterAccueilView(listeBagagistes, listeAccueils);
            new AffecterAccueilController(affecterAccueilView, listeBagagistes, listeAccueils);
            contentPanel.add(affecterAccueilView, BorderLayout.CENTER);

        }
        else if (e.getSource() == gerer_services_btn) {
            GererServiceView serviceView = new GererServiceView(listeAccueils);
            new GererServiceController(serviceView, listeAccueils);
            contentPanel.add(serviceView, BorderLayout.CENTER);
        }
            else if (e.getSource() == exit_btn) {
            System.exit(0);
        }

        contentPanel.revalidate();
        contentPanel.repaint();
    }
}
