package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import controller.backend;

public class homepage extends JFrame implements ActionListener {

    private Image backgroundImage;
    private JButton receptionnist;
    private JButton gestionnaire;
    private JButton exit;
    private backend controller;

    public homepage() {
        this.controller = new backend();
        setTitle("Acceuil");
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

        gbc.anchor = GridBagConstraints.CENTER;

        // Panel to hold the buttons horizontally
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 40, 0));
        panel.setOpaque(false);
        receptionnist = creerBtn("receptionnist");
        panel.add(receptionnist);
        gestionnaire = creerBtn("gestionnaire");

        panel.add(gestionnaire);

        backgroundPanel.add(panel, gbc);

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
        if (e.getSource() == receptionnist) {
            controller.dashboard_receptionneste();
            dispose();

        } else if (e.getSource() == gestionnaire) {
            controller.dashboard_gestionnaire();
            dispose();

        }
    }



    public JButton creerBtn(String str){
        JButton btn;
        btn = new JButton(str);
        btn.setBackground(Color.BLACK);
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Arial", Font.BOLD, 24));
        btn.setPreferredSize(new Dimension(250, 60));
        btn.addActionListener(this);
        return btn;

    }
}