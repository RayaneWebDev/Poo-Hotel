package controller;

import model.Accueil;
import view.GererServiceView;

import javax.swing.*;
import java.util.Date;
import java.util.List;

public class GererServiceController {

    private final GererServiceView view;
    private final List<Accueil> services;
    private int nextId = 1; // Start service ID from 1

    public GererServiceController(GererServiceView view, List<Accueil> services) {
        this.view = view;
        this.services = services;

        // Attach Action Listeners to the Buttons
        view.getBtnAjouter().addActionListener(e -> ajouterService());
        view.getBtnModifier().addActionListener(e -> modifierService());
        view.getBtnSupprimer().addActionListener(e -> supprimerService());

        // Enable/Disable buttons based on selection
        view.getTable().getSelectionModel().addListSelectionListener(e -> updateButtonState());
        updateButtonState();
    }

    // ✅ Method to Add a New Service
    private void ajouterService() {
        String nomService = view.getNomService();
        if (nomService.isEmpty()) {
            view.showMessage("Le nom du service ne peut pas être vide.");
            return;
        }

        // Create a new service and add it to the list
        Accueil newService = new Accueil(nextId++, nomService, new Date());
        services.add(newService);

        // Refresh the table view
        view.refreshTable(services);
        view.clearInput();
        view.showMessage("Service ajouté avec succès.");
    }

    // ✅ Method to Modify an Existing Service
    private void modifierService() {
        int selectedRow = view.getTable().getSelectedRow();
        if (selectedRow == -1) {
            view.showMessage("Sélectionnez un service à modifier.");
            return;
        }

        String newNom = view.getNomService();
        if (newNom.isEmpty()) {
            view.showMessage("Le nom ne peut pas être vide.");
            return;
        }

        // Get the selected service ID from the table
        int serviceId = (int) view.getTable().getValueAt(selectedRow, 0);

        // Find and update the service in the list
        for (Accueil service : services) {
            if (service.getID_service() == serviceId) {
                service.setNom_service(newNom);
                break;
            }
        }

        // Refresh the table view
        view.refreshTable(services);
        view.clearInput();
        view.showMessage("Service modifié avec succès.");
    }

    // ✅ Method to Remove a Service
    private void supprimerService() {
        int selectedRow = view.getTable().getSelectedRow();
        if (selectedRow == -1) {
            view.showMessage("Sélectionnez un service à supprimer.");
            return;
        }

        // Get the selected service ID
        int serviceId = (int) view.getTable().getValueAt(selectedRow, 0);

        // Remove the service from the list
        services.removeIf(service -> service.getID_service() == serviceId);

        // Refresh the table view
        view.refreshTable(services);
        view.showMessage("Service supprimé avec succès.");
    }

    // ✅ Update Button State (Disable if No Row Selected)
    private void updateButtonState() {
        boolean rowSelected = view.getTable().getSelectedRow() >= 0;
        view.getBtnModifier().setEnabled(rowSelected);
        view.getBtnSupprimer().setEnabled(rowSelected);
    }
}
