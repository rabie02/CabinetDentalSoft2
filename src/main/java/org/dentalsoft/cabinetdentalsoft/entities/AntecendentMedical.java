package org.dentalsoft.cabinetdentalsoft.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.dentalsoft.cabinetdentalsoft.enums.CategorieAntecedentsMedicaux;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AntecendentMedical {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToMany(mappedBy = "antecendentMedicals")
    private List<Patient> patientsAvecCeAntecendentMedicale ;

    private String libelle;
    @Enumerated(value = EnumType.STRING)
    private CategorieAntecedentsMedicaux categorie;

}
