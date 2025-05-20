package view;

import controller.MenageController;
import model.Chambre;
import model.FemmeDeMenage;
import model.Menage;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class GestionMenagesView extends JPanel {
    private JTable table;
    private DefaultTableModel model;
    private JTextField tfDate, tfHeure, tfChambre;
    private JComboBox<FemmeDeMenage> comboFemmes;
    private JComboBox<Chambre> comboChambre;
    private MenageController controller;

    public GestionMenagesView() {
        setLayout(new BorderLayout());
        initUI();
    }

    public GestionMenagesView(MenageController controller) {
        this();
        this.controller = controller;
        controller.loadMenages(this);
    }

    private void initUI() {
        model = new DefaultTableModel(new Object[]{"ID", "Date", "Heure", "Chambre", "Femme de ménage"}, 0);
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(0, 300)); // Limite la hauteur visible de la table
        add(scrollPane, BorderLayout.CENTER);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        panel.setPreferredSize(new Dimension(0, 100)); // Empêche d’être poussé hors écran

        tfDate = new JTextField(8);
        tfHeure = new JTextField(6);
        tfChambre = new JTextField(5);
        comboFemmes = new JComboBox<>();
        comboChambre = new JComboBox<>();

        panel.add(new JLabel("Date (YYYY-MM-DD):"));
        panel.add(tfDate);

        panel.add(new JLabel("Heure:"));
        panel.add(tfHeure);

        panel.add(new JLabel("Chambre:"));
        panel.add(tfChambre);

        panel.add(new JLabel("Femme de ménage:"));
        panel.add(comboFemmes);

        JButton btnAjouter = new JButton("Ajouter");
        JButton btnSupprimer = new JButton("Supprimer");
        JButton btnRechercher = new JButton("Rechercher");

        btnAjouter.addActionListener(e -> controller.ajouterMenageDepuisVue(this));
        btnSupprimer.addActionListener(e -> controller.supprimerMenageDepuisVue(this));
        btnRechercher.addActionListener(e -> controller.rechercherMenageDepuisVue(this));

        panel.add(btnAjouter);
        panel.add(btnSupprimer);
        panel.add(btnRechercher);

        add(panel, BorderLayout.SOUTH);
    }


    public void setController(MenageController controller) {
        this.controller = controller;
        controller.loadMenages(this);
    }

    public String getDate() {
        return tfDate.getText();
    }

    public String getHeure() {
        return tfHeure.getText();
    }

    public String getChambre() {
        return tfChambre.getText();
    }

    public FemmeDeMenage getSelectedFemme() {
        return (FemmeDeMenage) comboFemmes.getSelectedItem();
    }
    public Chambre getSelectedChambre() {
        return (Chambre) comboChambre.getSelectedItem();
    }


    public JTable getTable() {
        return table;
    }

    public DefaultTableModel getModel() {
        return model;
    }

    public JComboBox<FemmeDeMenage> getComboFemmes() {
        return comboFemmes;
    }

    public void loadFemmes(List<FemmeDeMenage> femmes) {
        comboFemmes.removeAllItems();
        for (FemmeDeMenage f : femmes) {
            comboFemmes.addItem(f);
        }
    }
    public void loadChambres(List<Chambre> chambres) {
        comboChambre.removeAllItems();
        for (Chambre c : chambres) {
            comboChambre.addItem(c);
        }
    }

}
