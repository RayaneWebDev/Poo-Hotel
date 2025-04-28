package view;

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

    public dashbord_receptionist(Receptionniste receptionniste) {
        setTitle("Dashboard Receptionist");
        setSize(1500, 800);
        setLocationRelativeTo(null); // Center on screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(new Color(30, 144, 255));
        leftPanel.setLayout(new GridLayout(5, 1, 0, 10));
        leftPanel.setPreferredSize(new Dimension(250, 800));

        consulter_btn = new JButton("Consulter les réservations");
        gerer_chambre_btn = new JButton("Gérer les chambres");
        gerer_plainte_btn = new JButton("Gérer les plaintes");
        exit_btn = new JButton("Exit");

        styleMenuButton(consulter_btn, true);
        styleMenuButton(gerer_chambre_btn, false);
        styleMenuButton(gerer_plainte_btn, false);
        styleExitButton(exit_btn);

        leftPanel.add(consulter_btn);
        leftPanel.add(gerer_chambre_btn);
        leftPanel.add(gerer_plainte_btn);
        leftPanel.add(Box.createVerticalStrut(200));
        leftPanel.add(exit_btn);

        add(leftPanel, BorderLayout.WEST);

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

    }
}
