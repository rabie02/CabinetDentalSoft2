package org.dentalsoft.cabinetdentalsoft.service;

import org.dentalsoft.cabinetdentalsoft.entities.*;
import org.dentalsoft.cabinetdentalsoft.enums.*;
import org.dentalsoft.cabinetdentalsoft.repos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DataService {

    @Autowired
    private ActeRepository acteRepository;

    @Autowired
    private AntecedantMedRepository antecendentMedicalRepository;

    @Autowired
    private ConsultationRepository consultationRepository;

    @Autowired
    private DentisteRepository dentisteRepository;

    @Autowired
    private DossierMedicalRepository dossierMedicalRepository;

    @Autowired
    private FactureRepository factureRepository;

    @Autowired
    private InterventionMedecinRepository interventionMedecinRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private SituationFinancierRepository situationFinanciereRepository;

    @Autowired
    private UtilisateurRepository utilisateurRepository;
    @Autowired
    private CaisseRepository caisseRepository;

    @PostConstruct
    public void insertData() {
        insertActeData();
        insertAntecendentMedicalData();
        insertCaisseData();
        insertDentisteData();
        // Ajoutez des appels pour insérer des données pour chaque entité
    }
    private void insertActeData() {
        Acte acte1 = new Acte(null, 200.0, "Consultation Générale", CategorieActe.DIAGNOSTIC, null);
        Acte acte2 = new Acte(null, 150.0, "Détartrage", CategorieActe.PREVENTION, null);
        //acteRepository.saveAll(List.of(acte1, acte2));

        // Insertion pour AntecendentMedical
        AntecendentMedical antecedent1 = new AntecendentMedical(null, null, "Diabète", CategorieAntecedentsMedicaux.MALADIE_CHRONIQUE);
        AntecendentMedical antecedent2 = new AntecendentMedical(null, null, "Allergie aux antibiotiques", CategorieAntecedentsMedicaux.ALLERGIE);
        //antecendentMedicalRepository.saveAll(List.of(antecedent1, antecedent2));
    }

    private void insertAntecendentMedicalData() {
        AntecendentMedical antecedent1 = new AntecendentMedical(null, null, "Diabète", CategorieAntecedentsMedicaux.MALADIE_CHRONIQUE);
        AntecendentMedical antecedent2 = new AntecendentMedical(null, null, "Allergie aux antibiotiques", CategorieAntecedentsMedicaux.ALLERGIE);
        //antecendentMedicalRepository.saveAll(List.of(antecedent1, antecedent2));
    }

    private void insertCaisseData() {
        Caisse caisse1 = new Caisse(null, 0.0, 0.0, 0.0, null);
        //caisseRepository.save(caisse1);
    }
    private void insertDentisteData() {
        Dentiste dentiste1 = new Dentiste();
        dentiste1.setNom("Rabie2");
        dentiste1.setPrenom("Outkidout2");
        dentiste1.setAdresse("Sala al jadida");
        dentiste1.setEmail("rabie@exemple.com");
        dentiste1.setTelephone("0123456789");
        dentiste1.setCin("As8014");
        dentiste1.setSexe("Homme");
        dentiste1.setMotDePass("123");
        dentiste1.setNomUtilisateur("Outkidout2");
        dentiste1.setSalaireDeBase(5000.0);
        dentiste1.setDateRetourConge(LocalDate.of(2024, 5, 1));
        dentiste1.setSpecialite(Specialite.ENDODONTIE);
        dentiste1.setAssurance(Assurance.CIMR);
        dentiste1.setStatusActuel(StatusEmploye.Actif);
        Map<DayOfWeek, Disponibilite> disponibilites = new HashMap<>();
        disponibilites.put(DayOfWeek.MONDAY, Disponibilite.Matin);
        disponibilites.put(DayOfWeek.TUESDAY, Disponibilite.Apres_Midi);
        disponibilites.put(DayOfWeek.WEDNESDAY, Disponibilite.Toute_La_Journee);
        dentiste1.setDisponibilites(disponibilites);
        //dentisteRepository.save(dentiste1);
    }
    private void insertDossierMedicalData() {
        DossierMedical dossierMedical1 = new DossierMedical();
        dossierMedical1.setNumeroDossier(11l);
        dossierMedical1.setDateCreation(LocalDate.now());
        dossierMedical1.setStatutPaiement(StatutPaiement.EN_ATTENTE);
        // Vous pouvez ajouter d'autres attributs selon les besoins
        //dossierMedicalRepository.save(dossierMedical1);
    }

}
