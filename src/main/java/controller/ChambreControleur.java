package controller;

import model.Categorie_Chambre;
import model.Chambre;
import view.GestionChambres;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;

public class ChambreControleur {

    private final GestionChambres view;
    private final List<Chambre> chambres;

    public ChambreControleur(GestionChambres view) {
        this.view = view;
        this.chambres = new ArrayList<>();
    }

    public void chargerChambres() {
        DefaultTableModel model = view.getModel();
        model.setRowCount(0); // Vider le tableau
        for (Chambre c : chambres) {
            model.addRow(new Object[]{
                    c.getID_chambre(),
                    c.getCategorie().name(),
                    c.getPrix(),
                    convertirEtat(c.getEtat())
            });
        }
    }

    public void ajouterChambre() {
        try {
            int numero = Integer.parseInt(view.getNumero());
            String type = view.getType();
            String etatStr = view.getEtat();

            Categorie_Chambre categorie = Categorie_Chambre.valueOf(type); // Doit correspondre au JComboBox
            int etat = convertirEtatDepuisString(etatStr);

            // Vérification doublon
            for (Chambre c : chambres) {
                if (c.getID_chambre() == numero) {
                    JOptionPane.showMessageDialog(null, "Une chambre avec ce numéro existe déjà.");
                    return;
                }
            }

            Chambre chambre = new Chambre(numero, categorie, etat);
            chambres.add(chambre);
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
            chambres.removeIf(c -> c.getID_chambre() == numero);
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
            for (Chambre c : chambres) {
                if (c.getID_chambre() == numero) {
                    model.addRow(new Object[]{
                            c.getID_chambre(),
                            c.getCategorie().name(),
                            c.getPrix(),
                            convertirEtat(c.getEtat())
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
            int numero = Integer.parseInt(view.getNumero());
            String type = view.getType();
            String etatStr = view.getEtat();

            // Convertir en Categorie_Chambre
            Categorie_Chambre categorie = switch (type) {
                case "Suite" -> Categorie_Chambre.SUITE_PRESIDENTIELLE;
                case "Double" -> Categorie_Chambre.SUITE_NORMALE;
                default -> Categorie_Chambre.SIMPLE;
            };

            int etat = switch (etatStr) {
                case "Disponible" -> 0;
                case "Occupée" -> 1;
                case "En nettoyage" -> 2;
                case "En maintenance" -> 3;
                case "Réservée" -> 4;
                default -> 0;
            };

            // Modifier dans la liste
            int ancienNumero = Integer.parseInt(view.getModel().getValueAt(selectedRow, 0).toString());
            for (Chambre c : chambres) {
                if (c.getID_chambre() == ancienNumero) {
                    c.setID_chambre(numero);
                    c.setCategorie(categorie);
                    c.setEtat(etat);
                    break;
                }
            }

            // Mettre à jour le tableau
            chargerChambres();
            JOptionPane.showMessageDialog(null, "Chambre modifiée avec succès.");

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Veuillez entrer un numéro valide.");
        }
    }


    private String convertirEtat(int etat) {
        return switch (etat) {
            case 0 -> "Disponible";
            case 1 -> "Occupée";
            case 2 -> "En nettoyage";
            case 3 -> "En maintenance";
            case 4 -> "Réservée";
            default -> "Inconnu";
        };
    }

    private int convertirEtatDepuisString(String etatStr) {
        return switch (etatStr) {
            case "Disponible" -> 0;
            case "Occupée" -> 1;
            case "En nettoyage" -> 2;
            case "En maintenance" -> 3;
            case "Réservée" -> 4;
            default -> 0;
        };
    }
}