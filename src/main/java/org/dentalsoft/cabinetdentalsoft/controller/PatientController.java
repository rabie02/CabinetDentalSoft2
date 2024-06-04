package org.dentalsoft.cabinetdentalsoft.controller;

import org.dentalsoft.cabinetdentalsoft.entities.*;
import org.dentalsoft.cabinetdentalsoft.enums.StatutPaiement;
import org.dentalsoft.cabinetdentalsoft.repos.*;
import org.dentalsoft.cabinetdentalsoft.service.servicesDeclaration.DossierMedicalService;
import org.dentalsoft.cabinetdentalsoft.service.servicesDeclaration.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
public class PatientController {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private DossierMedicalRepository dossierMedicalRepository;

    @Autowired
    private SituationFinancierRepository situationFinanciereRepository;

    @Autowired
    private ConsultationRepository consultationRepository;
    @Autowired
    private PersonneRepository personneRepository;
    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private DossierMedicalService dossierMedicalService;
    @Autowired
    private PatientService patientService;
    @Autowired
    SituationFinancierRepository situationFinancierRepository;
    @Autowired
    public PatientController(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }
    @RequestMapping(value = "/submit_patient", method = RequestMethod.POST, consumes = "application/x-www-form-urlencoded")
    public RedirectView ajouterPatient(@ModelAttribute Patient patient) {
        // Save the patient first
        patientRepository.save(patient);

        // Create the medical record
        DossierMedical dossierMedical = new DossierMedical();
        dossierMedical.setDateCreation(LocalDate.now());
        dossierMedical.setStatutPaiement(StatutPaiement.EN_ATTENTE); // Set a default value for statutPaiement if necessary

        // Link the patient to the medical record
        dossierMedical.setPatient(patient);

        // Save the dossier medical
        dossierMedicalRepository.save(dossierMedical);
        // Step 5: Update the patient with the dossier medical
        patient.setDossierMedical(dossierMedical);
        patientRepository.save(patient);

        // Create and save the financial situation
        SituationFinanciere situationFinanciere = new SituationFinanciere();
        situationFinanciere.setDateCreation(LocalDate.now());
        situationFinanciere.setMontantGlobalRestant(0.0);
        situationFinanciere.setMontantGlobalPaye(0.0);
        situationFinanciere.setDossierMedical(dossierMedical);
        situationFinanciereRepository.save(situationFinanciere);
        return new RedirectView("/ajouterPatient");

    }

    @PostMapping("/deletePatient/{id}")
    public RedirectView deletePatient( @ModelAttribute("user") Utilisateur user2,@PathVariable("id") Long id, Model model) {
        // Recherchez le patient par ID
        if(user2!=null){
            Patient patient = patientRepository.findById(id).orElse(null);
            if (patient != null) {
                // Recherchez la personne associée et supprimez-la
                Personne personne = personneRepository.findById(patient.getId()).orElse(null);
                if (personne != null) {
                    personneRepository.delete(personne);
                }
                // Recherchez le dossier médical du patient et supprimez les consultations associées
                DossierMedical dossierMedical = patient.getDossierMedical();
                if (dossierMedical != null) {
                    List<Consultation> consultations = consultationRepository.findAllByDossierMedical(dossierMedical);
                    consultationRepository.deleteAll(consultations);

                    // Supprimez la situation financière associée
                    SituationFinanciere situationFinanciere = dossierMedical.getSituationFinanciere();
                    if (situationFinanciere != null) {
                        situationFinanciereRepository.delete(situationFinanciere);
                    }

                    // Supprimez le dossier médical
                    dossierMedicalRepository.delete(dossierMedical);
                }
                // Supprimez le patient
                patientRepository.delete(patient);
            }
            // Rechargez la liste des patients pour l'utilisateur
            model.addAttribute("patients", patientRepository.findAll());

            // Rediriger vers la même page
            return new RedirectView("/patients");
        }else {
            return new RedirectView("/login");
        }

    }


    @GetMapping("/detail/{id}")
    public String showPatientDetail(@PathVariable("id") Long id, Model model) {
        // Recherchez le patient par ID
        Patient patient = patientRepository.findById(id).orElse(null);

        // Vérifiez si le patient existe
        if (patient == null) {
            // Gérer le cas où le patient n'est pas trouvé
            return "patientNotFound"; // Créez une vue pour afficher un message d'erreur approprié
        }
        // Ajoutez le patient au modèle
        model.addAttribute("patient", patient);

        // Renvoyer la vue de détail du patient
        return "patientDetail"; // Assurez-vous que ce nom correspond au nom de votre fichier HTML pour les détails du patient
    }

    @GetMapping("/{id}")
    public String getPatient(@PathVariable Long id, Model model) {
        Optional<Patient> patientOptional = patientRepository.findById(id);
        if (patientOptional.isPresent()) {
            Patient patient = patientOptional.get();
            model.addAttribute("patient", patient);
            return "patient";
        } else {
            return "error"; // Gérer le cas où le patient n'est pas trouvé
        }
    }





}
