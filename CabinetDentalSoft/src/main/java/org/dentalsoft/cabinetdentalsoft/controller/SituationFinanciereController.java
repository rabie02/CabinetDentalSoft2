package org.dentalsoft.cabinetdentalsoft.controller;

import org.dentalsoft.cabinetdentalsoft.entities.Consultation;
import org.dentalsoft.cabinetdentalsoft.entities.Facture;
import org.dentalsoft.cabinetdentalsoft.entities.SituationFinanciere;
import org.dentalsoft.cabinetdentalsoft.entities.Utilisateur;
import org.dentalsoft.cabinetdentalsoft.enums.TypePaiement;
import org.dentalsoft.cabinetdentalsoft.repos.FactureRepository;
import org.dentalsoft.cabinetdentalsoft.repos.SituationFinancierRepository;
import org.dentalsoft.cabinetdentalsoft.service.servicesDeclaration.FactureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@SessionAttributes("user")
@Controller
public class SituationFinanciereController {
    @Autowired
    private FactureRepository factureRepository;

    @Autowired
    private SituationFinancierRepository situationFinanciereRepository;

    @GetMapping("factures/{id}")
    public String getFactures(@PathVariable Long id, Model model) {
        Optional<SituationFinanciere> situationFinanciereOpt = situationFinanciereRepository.findById(id);
        if (situationFinanciereOpt.isPresent()) {
            model.addAttribute("factures", situationFinanciereOpt.get().getFactures());
            return "redirect:/dossierMedical/#ex1 a";// Nom de la vue Thymeleaf pour afficher les factures
        } else {
            model.addAttribute("errorMessage", "Situation financière non trouvée");
            return "error";
        }
    }

    @PostMapping("/addFacture")
    public String addFacture(@RequestParam Long situationFinanciereId,
                             @RequestParam Double montantRestant,
                             @RequestParam Double montantPaye,
                             @RequestParam Double montantTotal,
                             @RequestParam TypePaiement typePaiement,
                             @RequestParam LocalDate dateFacturation,
                             RedirectAttributes redirectAttributes) {
        try {
            Optional<SituationFinanciere> situationFinanciereOpt = situationFinanciereRepository.findById(situationFinanciereId);
            if (situationFinanciereOpt.isPresent()) {
                Facture facture = new Facture();
                facture.setMontantRestant(montantRestant);
                facture.setMontantPaye(montantPaye);
                facture.setMontantTotal(montantTotal);
                facture.setTypePaiement(typePaiement);
                facture.setDateFacturation(dateFacturation);
                facture.setSituationFinanciere(situationFinanciereOpt.get());

                factureRepository.save(facture);
                redirectAttributes.addFlashAttribute("successMessage", "Facture ajoutée avec succès");
                return "redirect:/situationFinanciere/" + situationFinanciereId + "/factures";
            } else {
                redirectAttributes.addFlashAttribute("errorMessage", "Situation financière non trouvée");
                return "redirect:/situationFinanciere";
            }
        } catch (Exception e) {
            // Gérer les erreurs
            redirectAttributes.addFlashAttribute("errorMessage", "Une erreur s'est produite lors de l'ajout de la facture: " + e.getMessage());
            return "redirect:/situationFinanciere";
        }
    }

    @PostMapping("/factures/{factureId}/edit")
    public String editFacture(@PathVariable Long factureId, @RequestParam Double montantRestant, @RequestParam Double montantPaye,
                              @RequestParam Double montantTotal, @RequestParam TypePaiement typePaiement,
                              RedirectAttributes redirectAttributes) {
        Optional<Facture> factureOpt = factureRepository.findById(factureId);
        if (factureOpt.isPresent()) {
            Facture facture = factureOpt.get();
            facture.setMontantRestant(montantRestant);
            facture.setMontantPaye(montantPaye);
            facture.setMontantTotal(montantTotal);
            facture.setTypePaiement(typePaiement);

            factureRepository.save(facture);
            redirectAttributes.addFlashAttribute("successMessage", "Facture modifiée avec succès");
            return "redirect:/situationFinanciere/" + facture.getSituationFinanciere().getIdSituationFinanciere() + "/factures";
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Facture non trouvée");
            return "redirect:/situationFinanciere";
        }
    }

    @PostMapping("/factures/{factureId}/delete")
    public String deleteFacture(@PathVariable Long factureId, RedirectAttributes redirectAttributes) {
        Optional<Facture> factureOpt = factureRepository.findById(factureId);
        if (factureOpt.isPresent()) {
            Long situationFinanciereId = factureOpt.get().getSituationFinanciere().getIdSituationFinanciere();
            factureRepository.deleteById(factureId);
            redirectAttributes.addFlashAttribute("successMessage", "Facture supprimée avec succès");
            return "redirect:/situationFinanciere/" + situationFinanciereId + "/factures";
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Facture non trouvée");
            return "redirect:/situationFinanciere";
        }
    }
    private final FactureService factureService;

    @Autowired
    public SituationFinanciereController(FactureService factureService) {
        this.factureService = factureService;
    }


    @GetMapping("factures")
    public String getFactures(@ModelAttribute("user") Utilisateur user, Model model) {
        if(user != null){
            List<Facture> factures =factureService.findAll();
            model.addAttribute("consultations", factures);
            return "redirect:/dossierMedical/#ex1 a";
        } else {
            return "redirect:/login";
        }
    }

}
