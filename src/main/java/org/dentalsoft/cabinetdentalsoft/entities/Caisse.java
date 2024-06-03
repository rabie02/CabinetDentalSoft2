package org.dentalsoft.cabinetdentalsoft.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Caisse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCaisse ;
    private Double recetteDuJours;
    private Double recetteDuMois;
    private Double recetteDeLAnnee;

    @OneToMany(mappedBy = "caisse")
    private List<SituationFinanciere> situationFinancieres;

}