package org.dentalsoft.cabinetdentalsoft.service.servicesDeclaration;

import org.dentalsoft.cabinetdentalsoft.entities.Acte;
import org.dentalsoft.cabinetdentalsoft.entities.Consultation;
import org.dentalsoft.cabinetdentalsoft.enums.CategorieActe;
import org.dentalsoft.cabinetdentalsoft.enums.TypeConsultation;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
@Service
public interface ConsultationService {
     Consultation ajouterConsultation(Long patientId, Consultation consultation, Long acteId, Long dent, Double prixPatient);

    //Consultation ajouterConsultation(Long patientId, Consultation consultation);
     Consultation modifierConsultation(Long consultationId, Consultation consultation, Long acteId, Long dent, Double prixPatient);
     void supprimerConsultation(Long id);
    List<Consultation> findConsultationsByDossierMedicalNumeroDossier(Long dossierMedicalId);
    Consultation getConsultationById(Long id);
}
