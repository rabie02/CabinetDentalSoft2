package org.dentalsoft.cabinetdentalsoft.repos;

import org.dentalsoft.cabinetdentalsoft.entities.Consultation;
import org.dentalsoft.cabinetdentalsoft.entities.InterventionMedecin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface InterventionMedecinRepository extends JpaRepository<InterventionMedecin,Long> {
    @Query("SELECT i FROM InterventionMedecin i WHERE i.consultation.idConsultation = :consultationId")
    Optional<InterventionMedecin> findByConsultationId(@Param("consultationId") Long consultationId);

    List<InterventionMedecin> findByConsultation(Consultation consultation);
    List<InterventionMedecin> findByConsultationIn(List<Consultation> consultations);
}
