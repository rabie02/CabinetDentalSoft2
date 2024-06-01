package org.dentalsoft.cabinetdentalsoft.service.servicesDeclaration;

import org.dentalsoft.cabinetdentalsoft.entities.Consultation;
import org.dentalsoft.cabinetdentalsoft.entities.DossierMedical;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface DossierMedicalService {

    List<DossierMedical> findAll();
    DossierMedical findByNumeroDossier(Long  numeroDossier);

    Consultation findConsultationById(Long consultationId); // Ajoutez cette méthode pour récupérer une consultation par son ID

    List<Consultation> findConsultationsByDossierMedical(DossierMedical dossierMedical); // Ajoutez cette méthode pour récupérer les consultations associées à un dossier médical

}
