package org.dentalsoft.cabinetdentalsoft.service.servicesDeclaration;

import org.dentalsoft.cabinetdentalsoft.entities.Dentiste;
import org.dentalsoft.cabinetdentalsoft.entities.Patient;

import java.util.List;

public interface DentisteService {
    List<Patient> findPatientsByDentisteId(Long id);


    Dentiste trouve();
}
