package org.dentalsoft.cabinetdentalsoft.controller;

import org.dentalsoft.cabinetdentalsoft.entities.DossierMedical;
import org.dentalsoft.cabinetdentalsoft.service.servicesDeclaration.DossierMedicalService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dossierMedical")
public class DossierMedicalController {

    private final DossierMedicalService dossierMedicalService;

    public DossierMedicalController(DossierMedicalService dossierMedicalService) {
        this.dossierMedicalService = dossierMedicalService;
    }

    @GetMapping("/{numeroDossier}")
    public String afficherDossierMedical(@PathVariable("numeroDossier") Long numeroDossier, Model model) {
        // Récupérer le dossier médical correspondant au numéro de dossier
        DossierMedical dossierMedical = dossierMedicalService.findByNumeroDossier(numeroDossier);

        if (dossierMedical != null) {
            // Ajouter le dossier médical au modèle pour l'afficher dans la vue
            model.addAttribute("dossierMedical", dossierMedical);
            model.addAttribute("patient", dossierMedical.getPatient());
            model.addAttribute("consultations", dossierMedical.getConsultations());
            model.addAttribute("factures", dossierMedical.getSituationFinanciere());
            // Ajouter un message à afficher dans la vue
            model.addAttribute("message", "Dossier médical trouvé avec succès!");
            // Retourner le nom de la vue à afficher (par exemple, "dossierMedicalDetail")
            return "dossierMedical";
        } else {
            // Si aucun dossier médical correspondant n'est trouvé, retourner une vue d'erreur
            model.addAttribute("message", "Aucun dossier médical trouvé avec ce numéro.");
            return "error";
        }
    }

}
