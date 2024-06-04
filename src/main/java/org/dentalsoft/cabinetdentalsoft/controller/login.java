package org.dentalsoft.cabinetdentalsoft.controller;


import org.dentalsoft.cabinetdentalsoft.entities.Patient;
import org.dentalsoft.cabinetdentalsoft.entities.Utilisateur;
import org.dentalsoft.cabinetdentalsoft.repos.PatientRepository;
import org.dentalsoft.cabinetdentalsoft.service.servicesDeclaration.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@SessionAttributes("user")
public class login {

    @Autowired
    UtilisateurService utilisateurService;
    @Autowired
    PatientRepository patientRepository;

    @GetMapping("/")
    public String login(){
        return "login";
    }


    @PostMapping("/register")
    public String register(@RequestParam String email, @RequestParam String password, Model model) {
        Utilisateur utilisateur = utilisateurService.checkUtilisateur(email, password);
        if (utilisateur == null) {
            System.out.println("The user is null");
            return "login"; // Rediriger vers la page de connexion en cas d'échec de la connexion
        }
        model.addAttribute("user", utilisateur);
        return "redirect:/index"; // Rediriger vers la page d'accueil en cas de succès de la connexion
    }

}
