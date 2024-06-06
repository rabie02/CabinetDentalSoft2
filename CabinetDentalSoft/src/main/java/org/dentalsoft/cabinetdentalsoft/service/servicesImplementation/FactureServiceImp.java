package org.dentalsoft.cabinetdentalsoft.service.servicesImplementation;



import org.dentalsoft.cabinetdentalsoft.entities.Consultation;
import org.dentalsoft.cabinetdentalsoft.entities.Facture;
import org.dentalsoft.cabinetdentalsoft.entities.SituationFinanciere;
import org.dentalsoft.cabinetdentalsoft.repos.ConsultationRepository;
import org.dentalsoft.cabinetdentalsoft.repos.FactureRepository;
import org.dentalsoft.cabinetdentalsoft.repos.SituationFinancierRepository;
import org.dentalsoft.cabinetdentalsoft.service.servicesDeclaration.FactureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class FactureServiceImp implements FactureService {

    @Autowired
    private FactureRepository factureRepository;

    @Autowired
    private ConsultationRepository consultationRepository;

    public Facture saveFacture(Long consultationId, Facture facture) {
        Consultation consultation = consultationRepository.findById(consultationId)
                .orElseThrow(() -> new RuntimeException("Consultation not found"));
        facture.setConsultation(consultation);
        return factureRepository.save(facture);
    }

    @Override
    public List<Facture> findAll() {
        return factureRepository.findAll();
    }

    // Ajoutez d'autres méthodes métier si nécessaire
}
