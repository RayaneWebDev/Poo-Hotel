package view;

import controller.ClientController;
import model.Receptionniste;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class dashbord_receptionist extends JFrame implements ActionListener {

    private JButton exit_btn;
    private JButton consulter_btn;
    private JButton gerer_chambre_btn;
    private JButton gerer_plainte_btn;
    private JButton gererClientsBtn;

    private JPanel rightPanel;

    public dashbord_receptionist(Receptionniste receptionniste) {
        setTitle("Dashboard Receptionist");
        setSize(1500, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Left panel
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(new Color(30, 144, 255));
        leftPanel.setLayout(new GridLayout(6, 1, 0, 10));
        leftPanel.setPreferredSize(new Dimension(250, 800));

        consulter_btn = createMenuButton("Consulter les réservations");
        gerer_chambre_btn = createMenuButton("Gérer les chambres");
        gerer_plainte_btn = createMenuButton("Gérer les plaintes");
        gererClientsBtn = createMenuButton("Gérer les clients");
        exit_btn = createExitButton("Exit");

        // Gestion des clients
        GestionClientsView gestionClientsView = new GestionClientsView();
        ClientController controller = new ClientController(gestionClientsView);
        gestionClientsView.setController(controller);

        gererClientsBtn.addActionListener(e -> {
            rightPanel.removeAll();
            rightPanel.setLayout(new BorderLayout());
            rightPanel.add(gestionClientsView, BorderLayout.CENTER);
            rightPanel.revalidate();
            rightPanel.repaint();
        });

        exit_btn.addActionListener(e -> System.exit(0));

        leftPanel.add(consulter_btn);
        leftPanel.add(gerer_chambre_btn);
        leftPanel.add(gerer_plainte_btn);
        leftPanel.add(gererClientsBtn);
        leftPanel.add(Box.createVerticalStrut(100));
        leftPanel.add(exit_btn);

        // Right panel
        rightPanel = new JPanel();
        rightPanel.setLayout(new BorderLayout());

        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.CENTER);

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
        // Implement general action handling if needed
    }
}
