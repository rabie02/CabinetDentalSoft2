package org.dentalsoft.cabinetdentalsoft.service.servicesImplementation;

import org.dentalsoft.cabinetdentalsoft.entities.Dentiste;
import org.dentalsoft.cabinetdentalsoft.entities.Patient;
import org.dentalsoft.cabinetdentalsoft.repos.DentisteRepository;
import org.dentalsoft.cabinetdentalsoft.repos.PatientRepository;
import org.dentalsoft.cabinetdentalsoft.service.servicesDeclaration.DentisteService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DentisteServiceImpl implements DentisteService {

    PatientRepository patientRepo;
    DentisteRepository dentisteRepo;

    @Override
    public List<Patient> findPatientsByDentisteId(Long id) {
        List<Patient> patients = new ArrayList<>();
        Dentiste dentiste = dentisteRepo.findById(id).orElse(null);
        if (dentiste==null)
            throw new RuntimeException("Dentiste introvable ");

        dentiste.getDossierMedicalList().forEach(dossierMedical ->
                patients.add(dossierMedical.getPatient())
        );
        return patients;

    }




    @Override
    public Dentiste trouve() {
        Optional<Dentiste> optionalDentiste = dentisteRepo.findAll().stream().findFirst();
        if (optionalDentiste.isPresent()) {
            return optionalDentiste.get();
        } else {
            throw new RuntimeException("Aucun dentiste trouvé");
        }
    }
}
