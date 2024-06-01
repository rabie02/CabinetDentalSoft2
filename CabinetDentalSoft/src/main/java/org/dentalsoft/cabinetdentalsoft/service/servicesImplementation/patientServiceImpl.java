package org.dentalsoft.cabinetdentalsoft.service.servicesImplementation;

import org.dentalsoft.cabinetdentalsoft.entities.*;
import org.dentalsoft.cabinetdentalsoft.enums.GroupeSanguin;
import org.dentalsoft.cabinetdentalsoft.enums.TypeConsultation;
import org.dentalsoft.cabinetdentalsoft.repos.ConsultationRepository;
import org.dentalsoft.cabinetdentalsoft.repos.DossierMedicalRepository;
import org.dentalsoft.cabinetdentalsoft.repos.PatientRepository;
import org.dentalsoft.cabinetdentalsoft.repos.SituationFinancierRepository;
import org.dentalsoft.cabinetdentalsoft.service.servicesDeclaration.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class patientServiceImpl implements PatientService {


    @Autowired
    PatientRepository patientRepository;
    @Autowired
    SituationFinancierRepository situationFinancierRepository;
    @Autowired
    DossierMedicalRepository dossierMedicalRepository;
    @Autowired
    ConsultationRepository consultationRepository;


    @Override
    public List<Patient> getAllPatient() {
        return patientRepository.findAll();
    }

    @Override
    public List<Patient> getDentistPatients(Long dentistId) {
        return getAllPatient().stream().filter(p -> p.getDossierMedical().getMedecinTraitant().getId() == dentistId).toList();
    }

    @Override
    public List<Patient> getFilteredTodayPatients(Long dentistId) {
        return getDentistPatients(dentistId).stream().filter(p -> p.getDossierMedical().getConsultations().get(0).getDateConsultation().equals(LocalDate.now())).toList();
    }

    @Override
    public Patient addPatient(Utilisateur user, String nom, String prenom, String dateNaissance, String sexe,
                              String mutuelle, String groupeSanguin, String profession) {

        // this is the  defalut
        SituationFinanciere situationFinanciere = new SituationFinanciere();
        situationFinanciere.setDateCreation(LocalDate.now());
        situationFinanciere =  situationFinancierRepository.save(situationFinanciere);

        DossierMedical dossierMedical = new DossierMedical();
        dossierMedical.setNumeroDossier(UUID.randomUUID().getLeastSignificantBits());
        dossierMedical.setDateCreation(LocalDate.now());
        dossierMedical.setMedecinTraitant((Dentiste) user);
        dossierMedical.setSituationFinanciere(situationFinanciere);
        dossierMedical = dossierMedicalRepository.save(dossierMedical);

        Consultation consultation = new Consultation();
        consultation.setDateConsultation(LocalDate.now());
        consultation.setTypeConsultation(TypeConsultation.CONSULTATION_GENERALE);
        consultation.setDossierMedical(dossierMedical);
        consultationRepository.save(consultation);

        Patient patient = new Patient();
        patient.setDossierMedical(dossierMedical);
        patient.setNom(nom);
        patient.setPrenom(prenom);
        patient.setSexe(sexe);
        String[] parses = dateNaissance.split("-");
        patient.setDateNaissance( LocalDate.of(Integer.parseInt(parses[0]), Integer.parseInt(parses[1]), Integer.parseInt(parses[2])));
        patient.setGroupeSanguin(GroupeSanguin.valueOf(groupeSanguin));
        patient.setProfession(profession);


        return patientRepository.save(patient);

    }





}
