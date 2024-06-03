package org.dentalsoft.cabinetdentalsoft.service.servicesImplementation;


import lombok.AllArgsConstructor;
import org.dentalsoft.cabinetdentalsoft.entities.Utilisateur;
import org.dentalsoft.cabinetdentalsoft.repos.UtilisateurRepository;
import org.dentalsoft.cabinetdentalsoft.service.servicesDeclaration.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UtilisateurServiceImp implements UtilisateurService {

    @Autowired
    UtilisateurRepository utilisateurRepository;
    @Autowired 
    PersonneServiceImp personneServiceImp;


    @Override
    public Utilisateur checkUtilisateur(String email, String passowrd) {

            System.out.println(email);
            Utilisateur utilisateur = utilisateurRepository.findByEmail(email);

            if (utilisateur == null)
            {
                System.out.println("this email doenst exist");
                return null;
            }

            System.out.println("this is the inserted password :  " + passowrd);
            System.out.println("this is the user password  : " + utilisateur.getMotDePass());
            if (utilisateur != null && utilisateur.getMotDePass().equals(passowrd) == true)
                return utilisateur;
            
            System.out.println("the passwor doesnt match");  
            return null;
    }
    
    
    
}
