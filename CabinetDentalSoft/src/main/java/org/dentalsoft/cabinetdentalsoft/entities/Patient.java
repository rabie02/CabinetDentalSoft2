package org.dentalsoft.cabinetdentalsoft.entities;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.dentalsoft.cabinetdentalsoft.enums.GroupeSanguin;
import org.dentalsoft.cabinetdentalsoft.enums.Mutuelle;

import java.time.LocalDate;
import java.util.List;

@Entity 
@Data
@AllArgsConstructor 
@NoArgsConstructor
public class Patient extends Personne{
    
    private LocalDate dateNaissance;
    private Mutuelle mutuelle;
    private GroupeSanguin groupeSanguin;
    private String profession;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToMany
    private List<AntecendentMedical> antecendentMedicals;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToOne
    private DossierMedical dossierMedical;
    
}
