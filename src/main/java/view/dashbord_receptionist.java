package view;

import controller.*;
import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class dashbord_receptionist extends JFrame {

    private JButton exit_btn;
    private JButton consulter_btn;
    private JButton gerer_chambre_btn;
    private JButton gerer_plainte_btn;
    private JButton gererClientsBtn;
    private JButton gerer_sejour_btn;

    private JPanel rightPanel;

    public dashbord_receptionist(Receptionniste receptionniste) {
        setTitle("Dashboard Receptionist");
        setSize(1500, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // --- Création des données de test ---
        Chambre chambre1 = new Chambre(100, Categorie_Chambre.SIMPLE, 1);
        Chambre chambre2 = new Chambre(101, Categorie_Chambre.SUITE_NORMALE, 1);
        Chambre chambre3 = new Chambre(102, Categorie_Chambre.SIMPLE, 1);
        Chambre chambre4 = new Chambre(103, Categorie_Chambre.SUITE_PRESIDENTIELLE, 1);
        List<Chambre> chambres = new ArrayList<>();
        chambres.add(chambre1);
        chambres.add(chambre2);
        chambres.add(chambre3);
        chambres.add(chambre4);

        List<Receptionniste> receptionnistes = new ArrayList<>();
        Receptionniste receptionniste1 = new Receptionniste(0,"ElHaddajy","Hosam","0774152633");
        receptionnistes.add(receptionniste1);
        Hotel hotel = new Hotel(1,"Dandy",5,"81 rue de paris 75006",4,receptionnistes,chambres);


        // Produits minibar
        ProduitMinibar produit1 = new ProduitMinibar(1, "Eau", 2.5f,100);
        ProduitMinibar produit2 = new ProduitMinibar(2, "Chips", 3.0f,100);
        List<ProduitMinibar> produitsSejour1 = new ArrayList<>();
        produitsSejour1.add(produit1);
        produitsSejour1.add(produit2);

        // Chambres


        // Réservations
        Date debut = new Date(2025 - 1900, 4 - 1, 20); // Attention : mois = 0-based
        Reservation reservation1 = new Reservation(1001, 5, debut, chambre1);

        // Clients
        List<Reservation> reservationsClient1 = new ArrayList<>();
        reservationsClient1.add(reservation1);
        hotel.setReservations(new ArrayList<>(reservationsClient1));
        Client client1 = new Client(0, "Dupont", "Jean", "0600000000", "jean.dupont@email.com", reservationsClient1);

        List<Client> clients = new ArrayList<>();
        clients.add(client1);

        hotel.setClients(clients);
        hotel.setChambres(chambres);

        // Séjours
        List<Sejour> sejours = new ArrayList<>();
        Sejour sejour1 = new Sejour(5001, reservation1, produitsSejour1);
        sejours.add(sejour1);
        hotel.setSejours(sejours);




        // --- Fin création données ---

        // Left panel
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(new Color(45, 52, 54));
        leftPanel.setLayout(new GridLayout(7, 1, 0, 10));
        leftPanel.setPreferredSize(new Dimension(250, 800));

        consulter_btn = createMenuButton("Consulter les réservations");
        gerer_sejour_btn = createMenuButton("Gérer les séjours");
        gerer_chambre_btn = createMenuButton("Gérer les chambres");
        gerer_plainte_btn = createMenuButton("Voir les plaintes");
        gererClientsBtn = createMenuButton("Gérer les clients");
        exit_btn = createExitButton("Exit");

        // Gestion Clients
        GestionClientsView gestionClientsView = new GestionClientsView();
        ClientController clientController = new ClientController(gestionClientsView,hotel);
        gestionClientsView.setController(clientController);
        // Injection des clients dans le controller

        gererClientsBtn.addActionListener(e -> {
            rightPanel.removeAll();
            rightPanel.setLayout(new BorderLayout());
            rightPanel.add(gestionClientsView, BorderLayout.CENTER);
            rightPanel.revalidate();
            rightPanel.repaint();
        });

        // Consultation réservations
        ConsultationReservationsView reservationsView = new ConsultationReservationsView();
        reservationsView.setClients(clients);
        ReservationController reservationController = new ReservationController(reservationsView,hotel);
//        reservationController.loadClients(clients);


        clientController.setReservationController(reservationController);

        gestionClientsView.setController(clientController);
        reservationsView.setClients(hotel.getClients());
        consulter_btn.addActionListener(e -> {
            rightPanel.removeAll();
            rightPanel.setLayout(new BorderLayout());
            rightPanel.add(reservationsView, BorderLayout.CENTER);
            rightPanel.revalidate();
            rightPanel.repaint();
        });

        ChambreView gestionChambres = new ChambreView();
        ChambreControleur chambreControleur = new ChambreControleur(gestionChambres,chambres);
        chambreControleur.loadChambres(chambres);

        // Gestion chambres
        gerer_chambre_btn.addActionListener(e -> {

            rightPanel.removeAll();
            rightPanel.setLayout(new BorderLayout());
            rightPanel.add(gestionChambres, BorderLayout.CENTER);
            rightPanel.revalidate();
            rightPanel.repaint();
        });

        // Gestion plaintes
        PlainteView gestionPlaintesView = new PlainteView();
        PlainteController gestionPlainteController = new PlainteController(gestionPlaintesView);
        gestionPlaintesView.setController(gestionPlainteController);

        gerer_plainte_btn.addActionListener(e -> {
            rightPanel.removeAll();
            rightPanel.setLayout(new BorderLayout());
            rightPanel.add(gestionPlaintesView, BorderLayout.CENTER);
            rightPanel.revalidate();
            rightPanel.repaint();
        });

        // Gestion séjours
        SejourView sejourView = new SejourView();
        SejourController sejourController = new SejourController(sejourView,hotel);
        sejourView.setController(sejourController);
        sejourController.loadSejours(sejours, clients);


        // Injection des données dans le controller séjour
        sejourController.setClients(clients);
        sejourController.setSejours(sejours);

        reservationController.setSejourController(sejourController);
        clientController.setReservationController(reservationController);


        gerer_sejour_btn.addActionListener(e -> {
            rightPanel.removeAll();
            rightPanel.setLayout(new BorderLayout());
            rightPanel.add(sejourView, BorderLayout.CENTER);
            rightPanel.revalidate();
            rightPanel.repaint();
        });

        exit_btn.addActionListener(e -> System.exit(0));

        leftPanel.add(consulter_btn);
        leftPanel.add(gerer_chambre_btn);
        leftPanel.add(gerer_plainte_btn);
        leftPanel.add(gererClientsBtn);
        leftPanel.add(gerer_sejour_btn);
        leftPanel.add(Box.createVerticalStrut(100));
        leftPanel.add(exit_btn);

        rightPanel = new JPanel();
        rightPanel.setLayout(new BorderLayout());
        rightPanel.setBackground(new Color(245, 245, 245));

        JPanel header = new JPanel();
        header.setBackground(new Color(26, 37, 47));
        header.setPreferredSize(new Dimension(getWidth(), 60));
        JLabel title = new JLabel("Bienvenue Monsieur " + receptionniste.getNom() + " " + receptionniste.getPrenom());
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Segoe UI", Font.BOLD, 20));
        header.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 15));
        header.add(title);
        add(header, BorderLayout.NORTH);

        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.CENTER);

        // Image par défaut dans right panel
        ImageIcon imageIcon = new ImageIcon("resources/media/image.png");
        Image image = imageIcon.getImage().getScaledInstance(600, 400, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(image);
        JLabel imageLabel = new JLabel(resizedIcon);
        rightPanel.add(imageLabel, BorderLayout.CENTER);

        setVisible(true);
    }

    private JButton createMenuButton(String text) {
        JButton button = new JButton(text);
        button.setFocusPainted(false);
        button.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        button.setBackground(new Color(44, 62, 80));
        button.setForeground(Color.WHITE);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                button.setBackground(new Color(52, 73, 94));
            }

            public void mouseExited(MouseEvent e) {
                button.setBackground(new Color(44, 62, 80));
            }
        });

        return button;
    }

    private JButton createExitButton(String text) {
        JButton button = new JButton(text);
        button.setFocusPainted(false);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setBackground(new Color(236, 240, 241));
        button.setForeground(new Color(52, 73, 94));
        button.setBorderPainted(false);
        return button;
    }
}
