package org.dentalsoft.cabinetdentalsoft.repos;

import org.dentalsoft.cabinetdentalsoft.entities.Consultation;
import org.dentalsoft.cabinetdentalsoft.entities.DossierMedical;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConsultationRepository extends JpaRepository<Consultation,Long> {
    List<Consultation> findAllByDossierMedical(DossierMedical dossierMedical);
}
