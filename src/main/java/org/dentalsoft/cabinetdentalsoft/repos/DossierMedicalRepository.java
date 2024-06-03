package org.dentalsoft.cabinetdentalsoft.repos;

import org.dentalsoft.cabinetdentalsoft.entities.DossierMedical;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DossierMedicalRepository extends JpaRepository<DossierMedical,String> {
    List<DossierMedical> findByMedecinTraitantId(Long id);

    DossierMedical findByNumeroDossier(Long numeroDossier);
    DossierMedical findByPatientId(Long id);
}
