package org.dentalsoft.cabinetdentalsoft.controller;

import org.dentalsoft.cabinetdentalsoft.entities.Facture;
import org.dentalsoft.cabinetdentalsoft.enums.TypePaiement;
import org.dentalsoft.cabinetdentalsoft.service.servicesDeclaration.FactureService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/factures")
public class FactureController {

    @Autowired
    private FactureService factureService;

    @PostMapping("/submit-facture")
    public Facture createFacture(@RequestParam Long consultationId,
                                 @RequestParam Double montantTotal,
                                 @RequestParam TypePaiement typePaiement,
                                 @RequestParam String etatPaiement) {
        Facture facture = new Facture();
        facture.setMontantTotal(montantTotal);
        facture.setTypePaiement(typePaiement);
        facture.setDateFacturation(LocalDate.now());

        if ("payé".equalsIgnoreCase(etatPaiement)) {
            facture.setMontantPaye(montantTotal);
            facture.setMontantRestant(0.0);
        } else {
            facture.setMontantPaye(0.0);
            facture.setMontantRestant(montantTotal);
        }

        return factureService.saveFacture(consultationId, facture);
    }

    // Ajoutez d'autres endpoints si nécessaire
}