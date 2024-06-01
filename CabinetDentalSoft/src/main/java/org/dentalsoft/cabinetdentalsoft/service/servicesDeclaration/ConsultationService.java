package org.dentalsoft.cabinetdentalsoft.service.servicesDeclaration;

import org.dentalsoft.cabinetdentalsoft.entities.Consultation;
import org.dentalsoft.cabinetdentalsoft.enums.TypeConsultation;

import java.time.LocalDate;

public interface ConsultationService {

    Consultation ajouterConsultation(Long patientId, Consultation consultation);
     Consultation modifierConsultation(Long id, Consultation consultationDetails);
     void supprimerConsultation(Long id);
}
