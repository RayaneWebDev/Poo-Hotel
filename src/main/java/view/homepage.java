package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;

public class homepage extends JFrame implements ActionListener {

    private Image backgroundImage;
    private JButton boutonEntree;
    private JButton boutonSortir;

    public homepage() {
        setTitle("Fenêtre avec Image et Bouton");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Charger l'image
        try {
            InputStream imgStream = getClass().getClassLoader().getResourceAsStream("media/homepage.jpg");
            if (imgStream != null) {
                backgroundImage = ImageIO.read(imgStream);
                try {
                    imgStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Erreur : L'image media/homepage.jpg n'a pas été trouvée dans le classpath.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Erreur lors du chargement de l'image : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }

        // Create a panel with the background image and a GridBagLayout to center buttons
        JPanel backgroundPanel = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (backgroundImage != null) {
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };
        backgroundPanel.setOpaque(false);

        // Constraints for centering the group of buttons
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.CENTER;

        // Panel to hold the buttons horizontally
        JPanel buttonsHorizontalPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 40, 0));
        buttonsHorizontalPanel.setOpaque(false);

        // Créer le bouton "Entrée"
        boutonEntree = new JButton("Entrée");
        boutonEntree.setBackground(Color.BLACK);
        boutonEntree.setForeground(Color.WHITE);
        boutonEntree.setFont(new Font("Arial", Font.BOLD, 24));
        boutonEntree.setPreferredSize(new Dimension(150, 60));
        boutonEntree.addActionListener(this);
        buttonsHorizontalPanel.add(boutonEntree);

        // Créer le bouton "Sortir"
        boutonSortir = new JButton("Sortir");
        boutonSortir.setBackground(Color.BLACK);
        boutonSortir.setForeground(Color.WHITE);
        boutonSortir.setFont(new Font("Arial", Font.BOLD, 24));
        boutonSortir.setPreferredSize(new Dimension(150, 60));
        boutonSortir.addActionListener(this);
        buttonsHorizontalPanel.add(boutonSortir);

        // Add the horizontal button panel to the center of the background panel
        backgroundPanel.add(buttonsHorizontalPanel, gbc);

        add(backgroundPanel, BorderLayout.CENTER);

        pack();
        if (backgroundImage != null) {
            setSize(Math.max(backgroundImage.getWidth(this), 400), Math.max(backgroundImage.getHeight(this), 150));
        } else {
            setSize(400, 150);
        }
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == boutonEntree) {
            JOptionPane.showMessageDialog(this, "Bouton Entrée cliqué !", "Action", JOptionPane.INFORMATION_MESSAGE);
            // Ajouter ici l'action pour le bouton Entrée
        } else if (e.getSource() == boutonSortir) {
            JOptionPane.showMessageDialog(this, "Bouton Sortir cliqué !", "Action", JOptionPane.INFORMATION_MESSAGE);
            // Ajouter ici l'action pour le bouton Sortir
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new homepage());
    }
}