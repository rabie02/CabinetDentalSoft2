package org.dentalsoft.cabinetdentalsoft.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class SituationFinanciere {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSituationFinanciere;
    private LocalDate dateCreation;
    private Double montantGlobalRestant;
    private Double montantGlobalPaye;
    @OneToMany(mappedBy = "situationFinanciere")
    private List<Facture> factures;
    @OneToOne
    private DossierMedical dossierMedical;
    @ManyToOne
    private Caisse caisse;
}
