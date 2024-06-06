package org.dentalsoft.cabinetdentalsoft.repos;

import org.dentalsoft.cabinetdentalsoft.entities.Consultation;
import org.dentalsoft.cabinetdentalsoft.entities.DossierMedical;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ConsultationRepository extends JpaRepository<Consultation,Long> {
    List<Consultation> findAllByDossierMedical(DossierMedical dossierMedical);
    List<Consultation> findConsultationsByDossierMedicalNumeroDossier(Long dossierMedicalId);
    Optional<Consultation> findByIdConsultation(Long id);

}
