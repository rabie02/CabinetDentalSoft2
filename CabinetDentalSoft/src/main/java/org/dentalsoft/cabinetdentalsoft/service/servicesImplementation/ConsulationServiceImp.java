package org.dentalsoft.cabinetdentalsoft.service.servicesImplementation;

import org.dentalsoft.cabinetdentalsoft.entities.Consultation;
import org.dentalsoft.cabinetdentalsoft.entities.DossierMedical;
import org.dentalsoft.cabinetdentalsoft.entities.Patient;
import org.dentalsoft.cabinetdentalsoft.repos.ActeRepository;
import org.dentalsoft.cabinetdentalsoft.repos.ConsultationRepository;
import org.dentalsoft.cabinetdentalsoft.repos.PatientRepository;
import org.dentalsoft.cabinetdentalsoft.service.servicesDeclaration.ConsultationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConsulationServiceImp implements ConsultationService {

    @Autowired
    private ConsultationRepository consultationRepository;

    @Autowired
    private PatientRepository patientRepository;


    @Override
    public Consultation ajouterConsultation(Long patientId, Consultation consultation) {
        Patient patient = patientRepository.findById(patientId).orElse(null);

        if (patient == null) {
            throw new IllegalArgumentException("Patient non trouvé avec l'ID: " + patientId);
        }

        DossierMedical dossierMedical = patient.getDossierMedical();
        if (dossierMedical == null) {
            throw new IllegalArgumentException("Dossier médical non trouvé pour le patient ID: " + patientId);
        }

        consultation.setDossierMedical(dossierMedical);

        return consultationRepository.save(consultation);
    }

    @Override
    public Consultation modifierConsultation(Long id, Consultation consultationDetails) {
        Consultation consultation = consultationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Consultation non trouvée avec l'ID: " + id));

        consultation.setTypeConsultation(consultationDetails.getTypeConsultation());
        consultation.setDateConsultation(consultationDetails.getDateConsultation());
        //consultation.setFactures(consultationDetails.getFactures());
        // Mettre à jour d'autres champs nécessaires

        return consultationRepository.save(consultation);
    }

    @Override
    public void supprimerConsultation(Long id) {
        Consultation consultation = consultationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Consultation non trouvée avec l'ID: " + id));

        consultationRepository.delete(consultation);
    }
}
