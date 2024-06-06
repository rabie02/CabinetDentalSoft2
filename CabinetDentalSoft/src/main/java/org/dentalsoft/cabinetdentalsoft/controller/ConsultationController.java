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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
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
    public String modifierConsultation(@RequestParam Long consultationId,
                                       @RequestParam Long acteId,
                                       @RequestParam Long dent,
                                       @RequestParam Double prixPatient,
                                       @ModelAttribute Consultation consultation) {
        try {
            // Assurez-vous de récupérer la consultation à modifier
            Consultation consultationToUpdate = consultationService.getConsultationById(consultationId);
            if (consultationToUpdate != null && consultationToUpdate.getDossierMedical() != null) {
                // Mettez à jour la consultation
                consultationService.modifierConsultation(consultationId, dent, prixPatient);
                // Redirigez vers la page du dossier médical


                return "redirect:/dossierMedical/" + consultationToUpdate.getDossierMedical().getNumeroDossier();
            } else {
                // Gérer le cas où la consultation à modifier ou son dossier médical est null
                return "error"; // ou redirigez vers une page d'erreur appropriée
            }
        } catch (IllegalArgumentException e) {
            // Gérer les erreurs d'argument invalide
            return "error"; // ou redirigez vers une page d'erreur appropriée
        }
    }


    @PostMapping("/delete-consultation")
    public String supprimerConsultation(@RequestParam Long consultationId, RedirectAttributes redirectAttributes) {
        try {
            Consultation consultation = consultationService.getConsultationById(consultationId);
            if (consultation != null && consultation.getDossierMedical() != null) {
                Long numeroDossier = consultation.getDossierMedical().getNumeroDossier();
                consultationService.supprimerConsultation(consultationId);
                redirectAttributes.addFlashAttribute("successMessage", "Consultation supprimée avec succès");
                // Rediriger vers la page du dossier médical associé
                return "redirect:/dossierMedical/" + numeroDossier;
            } else {
                redirectAttributes.addFlashAttribute("errorMessage", "Impossible de supprimer la consultation : dossier médical non trouvé");
                return "redirect:/dossierMedical";
            }
        } catch (Exception e) {
            // Handle error
            redirectAttributes.addFlashAttribute("errorMessage", "Une erreur s'est produite lors de la suppression de la consultation : " + e.getMessage());
            return "redirect:/dossierMedical";
        }
    }
    @GetMapping("/consultations")
    public String getConsultations(@ModelAttribute("user") Utilisateur user, Model model) {
        if(user != null){
            List<Consultation> consultations = consultationRepository.findAll();
            model.addAttribute("consultations", consultations);
            return "dossierMedical";
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping("/{id}")
    public String getConsultationById(@PathVariable Long id) {
        Consultation consultation = consultationService.getConsultationById(id);
        // Faites quelque chose avec la consultation récupérée, comme l'afficher dans une vue
        return "dossierMedical"; // Nom de la vue Thymeleaf
    }
}
