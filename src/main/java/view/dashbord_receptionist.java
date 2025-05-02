package view;

import controller.ClientController;
import model.Receptionniste;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


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
        leftPanel.setBackground(new Color(45, 52, 54)); // Gris foncé élégant
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

        gerer_chambre_btn.addActionListener(e -> {
            GestionChambres gestionChambres = new GestionChambres();
            rightPanel.removeAll();
            rightPanel.setLayout(new BorderLayout());
            rightPanel.add(gestionChambres, BorderLayout.CENTER);
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
        rightPanel.setBackground(new Color(245, 245, 245)); // un gris clair doux


        // Header panel (haut)
        JPanel header = new JPanel();
        header.setBackground(new Color(26, 37, 47));
        header.setPreferredSize(new Dimension(getWidth(), 60));

        JLabel title = new JLabel("Bienvenue, Réceptionniste");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Segoe UI", Font.BOLD, 20));

        header.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 15));
        header.add(title);
        add(header, BorderLayout.NORTH);



        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.CENTER);


        ImageIcon imageIcon = new ImageIcon("resources/media/image.png");
        Image image = imageIcon.getImage().getScaledInstance(600, 400, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(image);
        JLabel imageLabel = new JLabel(resizedIcon);

        rightPanel.setLayout(new BorderLayout());
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

        // Hover effect
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
        button.setBackground(new Color(236, 240, 241)); // Gris clair doux
        button.setForeground(new Color(52, 73, 94));    // Texte bleu foncé
        button.setBorderPainted(false);
        return button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Implement general action handling if needed
    }
}
