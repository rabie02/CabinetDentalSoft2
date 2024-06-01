package org.dentalsoft.cabinetdentalsoft.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InterventionMedecin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idIntervention;
    private String noteMedecin;
    private Double prixPatient;
    private Long dent;
    @ManyToOne
    private Consultation consultation;
    @ManyToOne
    private Acte acte;

}
