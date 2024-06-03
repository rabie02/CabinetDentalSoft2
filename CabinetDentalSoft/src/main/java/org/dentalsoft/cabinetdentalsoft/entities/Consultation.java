package org.dentalsoft.cabinetdentalsoft.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.dentalsoft.cabinetdentalsoft.enums.TypeConsultation;

import java.time.LocalDate;
import java.util.List;

@Entity         
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Consultation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idConsultation;
    private LocalDate dateConsultation;
    @Enumerated(value = EnumType.STRING)
    private TypeConsultation typeConsultation;
    @OneToMany(mappedBy = "consultation")
    private List<Facture> factures;
    @OneToMany(mappedBy = "consultation")
    private List<InterventionMedecin> interventions;
    @ManyToOne
    @JoinColumn(name = "dossier_medical_id")
    private DossierMedical dossierMedical;

}
