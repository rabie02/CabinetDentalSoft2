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
    @Column(name = "id_intervention")
    private Long idIntervention;
    private String noteMedecin;
    private Double prixPatient;
    private Long dent;
    @ManyToOne
    @JoinColumn(name = "consultation_id")
    private Consultation consultation;
    @ManyToOne
    @JoinColumn(name = "acte_id")
    private Acte acte;

}
