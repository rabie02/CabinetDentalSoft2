package org.dentalsoft.cabinetdentalsoft.entities;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.dentalsoft.cabinetdentalsoft.enums.CategorieActe;

import java.util.List;
@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Acte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_acte")
    private Long idActe;
    @Column(name = "prix_de_base")

    private Double prixDeBase;
    private String libelle;

    @Enumerated(EnumType.STRING)
    @Column(name = "categorie_acte")
    private CategorieActe categorieActe;
    @OneToMany(mappedBy = "acte")
    private List<InterventionMedecin> interventions;
}