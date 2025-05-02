package view;

import controller.ClientController;
import model.Receptionniste;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class dashbord_receptionist extends JFrame implements ActionListener {

    JButton exit_btn;
    JButton consulter_btn;
    JButton gerer_chambre_btn;
    JButton gerer_plainte_btn;
    JButton gererClientsBtn;

    private JPanel rightPanel;  // Le panneau où la vue des clients sera affichée

    public dashbord_receptionist(Receptionniste receptionniste) {
        GestionClientsView gestionClientsView = new GestionClientsView();
        ClientController controller = new ClientController(gestionClientsView); // Passer la vue au contrôleur
        gestionClientsView.setController(controller);

        setTitle("Dashboard Receptionist");
        setSize(1500, 800);
        setLocationRelativeTo(null); // Center on screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Left panel (pour la navbar)
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(new Color(30, 144, 255));
        leftPanel.setLayout(new GridLayout(6, 1, 0, 10));  // Ajouter un espace pour l'Exit
        leftPanel.setPreferredSize(new Dimension(250, 800));

        consulter_btn = createMenuButton("Consulter les réservations");
        gerer_chambre_btn = createMenuButton("Gérer les chambres");
        gerer_plainte_btn = createMenuButton("Gérer les plaintes");
        gererClientsBtn = createMenuButton("Gérer les clients");

        gererClientsBtn.addActionListener(e -> {
            // Créer un contrôleur

            // Créer la vue des clients avec le contrôleur

            // Effacer le contenu du rightPanel avant d'ajouter la vue des clients
            rightPanel.removeAll();  // Supprimer tous les anciens composants

            // Ajouter la vue des clients au rightPanel
            rightPanel.setLayout(new BorderLayout());
            rightPanel.add(gestionClientsView, BorderLayout.CENTER);

            // Revalider et repainter pour mettre à jour le contenu
            rightPanel.revalidate();
            rightPanel.repaint();
        });


        // Action pour Exit
        exit_btn = createExitButton("Exit");
        exit_btn.addActionListener(e -> System.exit(0)); // Ferme l'application

        // Ajouter les boutons à leftPanel
        leftPanel.add(consulter_btn);
        leftPanel.add(gerer_chambre_btn);
        leftPanel.add(gerer_plainte_btn);
        leftPanel.add(gererClientsBtn);
        leftPanel.add(Box.createVerticalStrut(200));  // Espacement avant Exit
        leftPanel.add(exit_btn);

        // Right panel (pour afficher les données)
        rightPanel = new JPanel();
        rightPanel.setLayout(new BorderLayout());  // On utilisera un layout pour la vue des clients

        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.CENTER);  // Le côté droit sera l'endroit où la vue des clients s'affiche
        setVisible(true);
    }

    private JButton createMenuButton(String text) {
        JButton button = new JButton(text);
        button.setFocusPainted(false);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setBackground(Color.WHITE);
        button.setForeground(Color.BLACK);
        button.setBorderPainted(false);
        return button;
    }

    private JButton createExitButton(String text) {
        JButton button = new JButton(text);
        button.setFocusPainted(false);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setBackground(Color.RED);
        button.setForeground(Color.WHITE);
        button.setBorderPainted(false);
        return button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Gestion des actions si nécessaire
    }
}
