package org.dentalsoft.cabinetdentalsoft.controller;
import org.dentalsoft.cabinetdentalsoft.entities.*;
import org.dentalsoft.cabinetdentalsoft.enums.CategorieActe;
import org.dentalsoft.cabinetdentalsoft.enums.StatutPaiement;
import org.dentalsoft.cabinetdentalsoft.enums.TypeConsultation;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SessionAttributes("user")
@Controller
public class ConsultationController {


        @Autowired
        private ConsultationRepository consultationRepository;
        @Autowired
        private ConsultationService consultationService;

        @PostMapping("/submit-consultation")
        public RedirectView ajouterConsultation(
                @RequestParam Long patientId,
                @RequestParam Long acteId,
                @RequestParam Long dent,
                @RequestParam Double prixPatient,
                @ModelAttribute Consultation consultation) {
            try {
                consultation.setTypeConsultation(TypeConsultation.CONSULTATION_GENERALE);
                consultationService.ajouterConsultation(patientId, consultation, acteId, dent, prixPatient);
                return new RedirectView("/dossierMedical/" + consultation.getDossierMedical().getNumeroDossier());
            } catch (IllegalArgumentException e) {
                // Handle error
                return new RedirectView("/error?message=" + e.getMessage());
            }
        }

    @PostMapping("/edit-consultation")
    public RedirectView modifierConsultation(
            @RequestParam Long consultationId,
            @RequestParam Long acteId,
            @RequestParam Long dent,
            @RequestParam Double prixPatient,
            @ModelAttribute Consultation consultation) {
        try {
            consultationService.modifierConsultation(consultationId, consultation, acteId, dent, prixPatient);
            return new RedirectView("/dossierMedical/" + consultation.getDossierMedical().getNumeroDossier());
        } catch (IllegalArgumentException e) {
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
    @GetMapping("/consultations")
    public String getConsultations(@ModelAttribute("user") Utilisateur user,Model model) {
        if(user!=null){
            List<Consultation> consultations = consultationRepository.findAll();
            model.addAttribute("consultations", consultations);
            return "dossierMedical";
        }else {
            return "redirect:/login";
        }

    }
}
