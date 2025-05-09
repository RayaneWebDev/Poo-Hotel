package view;

import controller.*;
import controller.FemmeDeMenageController;
import model.Chambre;
import model.FemmeDeMenage;
import model.Gestionnaire;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class dashboard_gestionnaire extends JFrame implements ActionListener {

    JButton exit_btn;
    JButton consulter_btn;
    JButton gerer_produit_btn;
    JButton gerer_femmes_btn;
    JButton gerer_menages_btn;
    private JPanel contentPanel;
    private Consultationstock produitController;
    private FemmeDeMenageController femmesDeMenageController;
    private MenageController menageController;
    private ChambreControleur chambreControleur;


    public dashboard_gestionnaire(Gestionnaire gestionnaire) {
        produitController = new Consultationstock();

        femmesDeMenageController = new FemmeDeMenageController();
        GestionChambres gestionChambresView = new GestionChambres();
        chambreControleur = new ChambreControleur(gestionChambresView);
        List<FemmeDeMenage> femmes = femmesDeMenageController.getFemmes();
        List<Chambre> chambres = chambreControleur.getChambres();

        // Initialise le MenageController avec la liste des femmes
        menageController = new MenageController(femmes,chambres);

        setTitle("Dashboard Gestionnaire");
        setSize(1500, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(new Color(30, 144, 255));
        leftPanel.setLayout(new GridLayout(7, 1, 0, 10));
        leftPanel.setPreferredSize(new Dimension(250, 800));

        consulter_btn = new JButton("Consulter le stock");
        gerer_produit_btn = new JButton("Gérer les produits");
        gerer_femmes_btn = new JButton("Gérer les femmes de ménage");
        gerer_menages_btn = new JButton("Gérer les ménages");
        exit_btn = new JButton("Exit");

        styleMenuButton(consulter_btn, true);
        styleMenuButton(gerer_produit_btn, false);
        styleMenuButton(gerer_femmes_btn, false);
        styleMenuButton(gerer_menages_btn, false);
        styleExitButton(exit_btn);

        consulter_btn.addActionListener(this);
        gerer_produit_btn.addActionListener(this);
        gerer_femmes_btn.addActionListener(this);
        gerer_menages_btn.addActionListener(this);
        exit_btn.addActionListener(this);

        leftPanel.add(consulter_btn);
        leftPanel.add(gerer_produit_btn);
        leftPanel.add(gerer_femmes_btn);
        leftPanel.add(gerer_menages_btn);
        leftPanel.add(exit_btn);
        add(leftPanel, BorderLayout.WEST);

        contentPanel = new JPanel(new BorderLayout());
        add(contentPanel, BorderLayout.CENTER);

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

    @Override
    public void actionPerformed(ActionEvent e) {
        contentPanel.removeAll();
        if (e.getSource() == gerer_produit_btn) {
            contentPanel.add(new GestionProduitView(produitController), BorderLayout.CENTER);
        } else if (e.getSource() == consulter_btn) {
            contentPanel.add(new ConsultationStock(produitController), BorderLayout.CENTER);
        } else if (e.getSource() == gerer_femmes_btn) {
            contentPanel.add(new GestionFemmesMenageView(femmesDeMenageController), BorderLayout.CENTER);
        } else if (e.getSource() == gerer_menages_btn) {
            contentPanel.add(new GestionMenagesView(menageController), BorderLayout.CENTER);
        } else if (e.getSource() == exit_btn) {
            System.exit(0);
        }
        contentPanel.revalidate();
        contentPanel.repaint();
    }
}
