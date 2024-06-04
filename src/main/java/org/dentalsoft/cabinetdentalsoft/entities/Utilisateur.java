package org.dentalsoft.cabinetdentalsoft.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Utilisateur extends Personne{
 
    private String motDePass;
    @Column(unique =  true)
    private String nomUtilisateur;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Role> role;
    public String getMotDePass() {
        return motDePass;
    }
}
