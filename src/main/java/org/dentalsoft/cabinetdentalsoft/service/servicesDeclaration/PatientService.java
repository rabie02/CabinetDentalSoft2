package org.dentalsoft.cabinetdentalsoft.service.servicesDeclaration;

import org.dentalsoft.cabinetdentalsoft.entities.Patient;
import org.dentalsoft.cabinetdentalsoft.entities.Utilisateur;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PatientService {
    List<Patient> getAllPatient();
    List<Patient> getDentistPatients(Long dentistId);
    List<Patient> getFilteredTodayPatients(Long dentistId);
    Patient   addPatient(Utilisateur user, String nom, String prenom, String dateNaissance,
                         String sexe,
                         String mutuelle,
                         String groupeSanguin,
                         String profession
    );
}
