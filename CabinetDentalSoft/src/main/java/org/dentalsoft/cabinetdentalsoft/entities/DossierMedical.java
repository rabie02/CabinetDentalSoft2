package org.dentalsoft.cabinetdentalsoft.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.dentalsoft.cabinetdentalsoft.enums.StatutPaiement;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DossierMedical {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long numeroDossier; // Changed data type to Long
    private LocalDate dateCreation;
    @Enumerated(EnumType.STRING)
    private StatutPaiement statutPaiement;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "dossierMedical")
    private List<Consultation> consultations;
    @OneToOne(mappedBy = "dossierMedical",cascade = CascadeType.ALL, orphanRemoval = true)
    private Patient patient;
    @ManyToOne
    private Dentiste medecinTraitant;
    @OneToOne
    private SituationFinanciere situationFinanciere;
    @Override
    public String toString() {
        return "DossierMedical{" +
                "consultations=" + consultations.stream().map(Consultation::getIdConsultation).collect(Collectors.toList()) +
                '}';
    }
}