package org.dentalsoft.cabinetdentalsoft.service.servicesDeclaration;


import org.dentalsoft.cabinetdentalsoft.entities.Personne;

public interface PersonneService {

    Personne findPersonneByEmail(String email);
    void deletePersonneById(Long id);
    Personne findPersonneById(Long id);

    
}
