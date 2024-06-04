package org.dentalsoft.cabinetdentalsoft.service.servicesImplementation;


import lombok.AllArgsConstructor;
import org.dentalsoft.cabinetdentalsoft.entities.Personne;
import org.dentalsoft.cabinetdentalsoft.repos.PersonneRepository;
import org.dentalsoft.cabinetdentalsoft.service.servicesDeclaration.PersonneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class PersonneServiceImp  implements PersonneService {
    
    @Autowired
    PersonneRepository personneRepository;
    
    @Override
    public Personne findPersonneByEmail(String email) {
        Personne personne = personneRepository.findByEmail(email);
        return personne;
    }

    @Override
    public void deletePersonneById(Long id) {
        personneRepository.deleteById(id);
        System.out.println("deleting the personne id : " + id);
    }
    @Override
    public Personne findPersonneById(Long id) {
        Optional<Personne> personne = personneRepository.findById(id);
        return personne.orElse(null);
    }


    
}
