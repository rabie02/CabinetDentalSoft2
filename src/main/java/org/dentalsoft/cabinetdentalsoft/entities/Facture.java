package org.dentalsoft.cabinetdentalsoft.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.dentalsoft.cabinetdentalsoft.enums.TypePaiement;

import java.time.LocalDate;


@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Facture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFacture;
    private Double montantRestant;
    private Double montantPaye;

    private LocalDate dateFacturation;
    private Double montantTotal;
    private TypePaiement typePaiement;
    @ManyToOne
    private SituationFinanciere situationFinanciere;
    @ManyToOne
    private Consultation consultation;

}
