package controller;

import model.Categorie_Chambre;
import model.Chambre;
import model.Hotel;
import view.ChambreView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class ChambreControleur {

    private final ChambreView view;
    private final Hotel hotel;

    public ChambreControleur(ChambreView view, Hotel hotel) {
        this.view = view;
        this.hotel = hotel;
        chargerChambres();
    }

    public void chargerChambres() {
        view.clearTable();
        for (Chambre c : hotel.getChambres()) {
            view.ajouterChambreDansTable(c);
        }
    }

    public void ajouterChambre() {
        try {
            int numero = Integer.parseInt(view.getNumero());
            String type = view.getType();
            String etat = view.getEtat();

            // Vérifie que le type est valide dans l'enum
            Categorie_Chambre categorie = Categorie_Chambre.valueOf(type);

            // Vérification doublon
            for (Chambre c : hotel.getChambres()) {
                if (c.getID_chambre() == numero) {
                    JOptionPane.showMessageDialog(null, "Une chambre avec ce numéro existe déjà.");
                    return;
                }
            }

            Chambre chambre = new Chambre(numero, categorie, etat);
            hotel.ajouterChambre(chambre);
            chargerChambres();
            view.resetChamps();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Veuillez entrer un numéro valide.");
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, "Catégorie invalide.");
        }
    }

    public void supprimerChambre() {
        int selectedRow = view.getTable().getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Veuillez sélectionner une chambre à supprimer.");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(null,
                "Êtes-vous sûr de vouloir supprimer cette chambre ?", "Confirmation",
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            int numero = Integer.parseInt(view.getModel().getValueAt(selectedRow, 0).toString());
            hotel.getChambres().removeIf(c -> c.getID_chambre() == numero);
            chargerChambres();
        }
    }

    public void rechercherChambre() {
        String numeroStr = view.getNumero().trim();

        if (numeroStr.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Veuillez entrer un numéro de chambre à rechercher.");
            return;
        }

        try {
            int numero = Integer.parseInt(numeroStr);
            DefaultTableModel model = view.getModel();
            model.setRowCount(0);

            boolean trouve = false;
            for (Chambre c : hotel.getChambres()) {
                if (c.getID_chambre() == numero) {
                    model.addRow(new Object[]{
                            c.getID_chambre(),
                            c.getCategorie().name(),
                            c.getPrix(),
                            c.getEtat()
                    });
                    trouve = true;
                    break;
                }
            }

            if (!trouve) {
                JOptionPane.showMessageDialog(null, "Aucune chambre trouvée avec ce numéro.");
                chargerChambres(); // Réaffiche tout
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Veuillez entrer un numéro valide.");
        }
    }

    public void modifierChambre() {
        int selectedRow = view.getTable().getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Veuillez sélectionner une chambre à modifier.");
            return;
        }

        try {
            int nouveauNumero = Integer.parseInt(view.getNumero());
            String type = view.getType();
            String etat = view.getEtat();

            Categorie_Chambre categorie = Categorie_Chambre.valueOf(type);

            int ancienNumero = Integer.parseInt(view.getModel().getValueAt(selectedRow, 0).toString());
            for (Chambre c : hotel.getChambres()) {
                if (c.getID_chambre() == ancienNumero) {
                    c.setID_chambre(nouveauNumero);
                    c.setCategorie(categorie);
                    c.setEtat(etat);
                    break;
                }
            }

            chargerChambres();
            JOptionPane.showMessageDialog(null, "Chambre modifiée avec succès.");

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Veuillez entrer un numéro valide.");
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, "Catégorie invalide.");
        }
    }

    public List<Chambre> getChambres() {
        return hotel.getChambres();
    }


}
