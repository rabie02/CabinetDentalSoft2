package org.dentalsoft.cabinetdentalsoft.service.servicesDeclaration;


import org.dentalsoft.cabinetdentalsoft.entities.Utilisateur;

public interface UtilisateurService {

    Utilisateur checkUtilisateur(String email, String passowrd);

}
