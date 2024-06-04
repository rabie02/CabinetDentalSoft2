package org.dentalsoft.cabinetdentalsoft.service.servicesImplementation;

import org.dentalsoft.cabinetdentalsoft.entities.*;
import org.dentalsoft.cabinetdentalsoft.enums.CategorieActe;
import org.dentalsoft.cabinetdentalsoft.enums.TypeConsultation;
import org.dentalsoft.cabinetdentalsoft.repos.ActeRepository;
import org.dentalsoft.cabinetdentalsoft.repos.ConsultationRepository;
import org.dentalsoft.cabinetdentalsoft.repos.InterventionMedecinRepository;
import org.dentalsoft.cabinetdentalsoft.repos.PatientRepository;
import org.dentalsoft.cabinetdentalsoft.service.servicesDeclaration.ConsultationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ConsulationServiceImp implements ConsultationService {

    @Autowired
    private ConsultationRepository consultationRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private InterventionMedecinRepository interventionMedecinRepository;

    @Autowired
    private ActeRepository acteRepository;

    @Override
    public Consultation ajouterConsultation(Long patientId, Consultation consultation, Long acteId, Long dent, Double prixPatient) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new IllegalArgumentException("Patient non trouvé avec l'ID: " + patientId));

        DossierMedical dossierMedical = patient.getDossierMedical();
        if (dossierMedical == null) {
            throw new IllegalArgumentException("Dossier médical non trouvé pour le patient ID: " + patientId);
        }

        consultation.setDossierMedical(dossierMedical);
        consultation.setTypeConsultation(TypeConsultation.CONSULTATION_GENERALE);
        consultation.setDateConsultation(LocalDate.now());

        // Save consultation
        Consultation savedConsultation = consultationRepository.save(consultation);

        // Create and save InterventionMedecin
        Acte acte = acteRepository.findById(acteId)
                .orElseThrow(() -> new IllegalArgumentException("Acte non trouvé avec l'ID: " + acteId));

        InterventionMedecin interventionMedecin = new InterventionMedecin();
        interventionMedecin.setActe(acte);
        interventionMedecin.setDent(dent);
        interventionMedecin.setPrixPatient(prixPatient);
        interventionMedecin.setConsultation(savedConsultation);
        interventionMedecin.setNoteMedecin(acte.getCategorieActe().toString()); // Assuming noteMedecin is an enum

        interventionMedecinRepository.save(interventionMedecin);

        return savedConsultation;
    }


    @Override
    public Consultation modifierConsultation(Long consultationId, Consultation consultation, Long acteId, Long dent, Double prixPatient) {
        Consultation existingConsultation = consultationRepository.findById(consultationId)
                .orElseThrow(() -> new IllegalArgumentException("Consultation non trouvée avec l'ID: " + consultationId));

        existingConsultation.setDateConsultation(consultation.getDateConsultation());
        existingConsultation.setTypeConsultation(consultation.getTypeConsultation());

        Consultation updatedConsultation = consultationRepository.save(existingConsultation);

        // Update InterventionMedecin if necessary
        InterventionMedecin interventionMedecin = interventionMedecinRepository.findByConsultationId(consultationId)
                .orElseThrow(() -> new IllegalArgumentException("Intervention non trouvée pour la consultation ID: " + consultationId));

        Acte acte = acteRepository.findById(acteId)
                .orElseThrow(() -> new IllegalArgumentException("Acte non trouvé avec l'ID: " + acteId));

        interventionMedecin.setActe(acte);
        interventionMedecin.setDent(dent);
        interventionMedecin.setPrixPatient(prixPatient);
        interventionMedecin.setConsultation(updatedConsultation);
        interventionMedecin.setNoteMedecin(acte.getCategorieActe().toString());
        interventionMedecinRepository.save(interventionMedecin);

        return updatedConsultation;
    }

    @Override
    public void supprimerConsultation(Long consultationId) {
        Consultation consultation = consultationRepository.findById(consultationId)
                .orElseThrow(() -> new IllegalArgumentException("Consultation non trouvée avec l'ID: " + consultationId));

        // Retrieve associated InterventionMedecin records
        List<InterventionMedecin> interventions = interventionMedecinRepository.findByConsultation(consultation);

        // Delete each associated InterventionMedecin record
        for (InterventionMedecin intervention : interventions) {
            interventionMedecinRepository.delete(intervention);
        }

        // Delete consultation
        consultationRepository.delete(consultation);
    }

}
