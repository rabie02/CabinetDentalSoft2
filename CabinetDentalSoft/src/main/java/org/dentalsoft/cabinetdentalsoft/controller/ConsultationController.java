package org.dentalsoft.cabinetdentalsoft.controller;
import org.dentalsoft.cabinetdentalsoft.entities.*;
import org.dentalsoft.cabinetdentalsoft.enums.StatutPaiement;
import org.dentalsoft.cabinetdentalsoft.repos.*;
import org.dentalsoft.cabinetdentalsoft.service.servicesDeclaration.ConsultationService;
import org.dentalsoft.cabinetdentalsoft.service.servicesDeclaration.DossierMedicalService;
import org.dentalsoft.cabinetdentalsoft.service.servicesDeclaration.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
@Controller
public class ConsultationController {

    @Autowired
    private ConsultationService consultationService;

    @PostMapping("/submit-consultation")
    public RedirectView ajouterConsultation(@RequestParam Long patientId, @ModelAttribute Consultation consultation) {
        try {
            consultationService.ajouterConsultation(patientId, consultation);
            return new RedirectView("/dossierMedical/" + consultation.getDossierMedical().getNumeroDossier());
        } catch (IllegalArgumentException e) {
            // Handle error
            return new RedirectView("/error?message=" + e.getMessage());
        }
    }
    @PostMapping("/edit-consultation")
    public RedirectView modifierConsultation(@RequestParam Long consultationId, @ModelAttribute Consultation consultationDetails) {
        try {
            consultationService.modifierConsultation(consultationId, consultationDetails);
            return new RedirectView("/dossierMedical/" + consultationDetails.getDossierMedical().getNumeroDossier());
        } catch (IllegalArgumentException e) {
            // Handle error
            return new RedirectView("/error?message=" + e.getMessage());
        }
    }

    @PostMapping("/delete-consultation")
    public RedirectView supprimerConsultation(@RequestParam Long consultationId) {
        try {
            consultationService.supprimerConsultation(consultationId);
            return new RedirectView("/dossierMedical");
        } catch (IllegalArgumentException e) {
            // Handle error
            return new RedirectView("/error?message=" + e.getMessage());
        }
    }
}
