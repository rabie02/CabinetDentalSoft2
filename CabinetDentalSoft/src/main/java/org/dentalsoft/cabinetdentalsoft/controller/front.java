package org.dentalsoft.cabinetdentalsoft.controller;


import jakarta.transaction.Transactional;
import org.dentalsoft.cabinetdentalsoft.entities.Patient;
import org.dentalsoft.cabinetdentalsoft.entities.Utilisateur;
import org.dentalsoft.cabinetdentalsoft.repos.PatientRepository;
import org.dentalsoft.cabinetdentalsoft.repos.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@SessionAttributes("user")
public class front {
    @Autowired
    PatientRepository patientRepository;
    @Autowired
    UtilisateurRepository utilisateurRepository;
    @GetMapping("/profile")
    public String profile(@ModelAttribute("user") Utilisateur user, Model model){
        if (user != null) {
            // Récupérer les informations de l'utilisateur connecté depuis la base de données
            Utilisateur utilisateurConnecte = utilisateurRepository.findById(user.getId()).orElse(null);
            // Vérifier si l'utilisateur existe dans la base de données
            if (utilisateurConnecte != null) {
                // Ajouter les informations de l'utilisateur au modèle
                model.addAttribute("utilisateur", utilisateurConnecte);
            } else {
                // Gérer le cas où l'utilisateur n'est pas trouvé dans la base de données
                // Peut-être rediriger vers une page d'erreur ou afficher un message approprié
            }
            return "profile";
        } else {
            // L'utilisateur n'est pas authentifié, rediriger vers la page de connexion
            return "redirect:/login";
        }
    }

    @GetMapping("/modiferProfile")
    public String modifierProfile(@ModelAttribute("user") Utilisateur user,Model model){
        if (user != null){
            model.addAttribute("user", user);
            System.out.println(user.getEmail());
            return "modifierProfile";
        }
        else {
            // L'utilisateur n'est pas authentifié, redirige vers la page de connexion
            return "redirect:/login";
        }
    }

    @GetMapping("/ajouterPatient")
    public String ajouterPatient(@ModelAttribute("user") Utilisateur user,Model model){
        if (user != null){

        }
        else {
            // L'utilisateur n'est pas authentifié, redirige vers la page de connexion
            return "redirect:/login";
        }
        model.addAttribute("user", user);
        System.out.println(user.getEmail());
        return "ajouterpatient";
    }
    @GetMapping("/caisse")
    public String caisse(@ModelAttribute("user") Utilisateur user,Model model){
        if (user != null){

        }
        else {
            // L'utilisateur n'est pas authentifié, redirige vers la page de connexion
            return "redirect:/login";
        }
        model.addAttribute("user", user);
        System.out.println(user.getEmail());
        return "caisse";
    }

    @PostMapping("/enregisterProfile")
    @Transactional
    public String redirectToProfile(@ModelAttribute("user") Utilisateur user,Model model){
        if (user != null){

        }
        else {
            // L'utilisateur n'est pas authentifié, redirige vers la page de connexion
            return "redirect:/login";
        }
        System.out.println(user.getAdresse());
        utilisateurRepository.save(user);
        model.addAttribute("user", user);
        return "profile";
    }
    @GetMapping("/patientIndex")
    public String patientIndex(@ModelAttribute("user") Utilisateur user) {
        if (user != null){
            return "patientIndex";
        }
        else {
            // L'utilisateur n'est pas authentifié, redirige vers la page de connexion
            return "redirect:/login";
        }
        // Retourne le nom de la vue correspondante
    }
    @GetMapping("/agenda")
    public String agenda(@ModelAttribute("user") Utilisateur user) {
        if (user != null){
            return "agenda";
        }
        else {
            // L'utilisateur n'est pas authentifié, redirige vers la page de connexion
            return "redirect:/login";
        }
       // Retourne le nom de la vue correspondante
    }
    @GetMapping("/Detail")
    public String Detail(@ModelAttribute("user") Utilisateur user) {
        if (user != null){
            return "Detail";
        }
        else {
            // L'utilisateur n'est pas authentifié, redirige vers la page de connexion
            return "redirect:/login";
        }
         // Retourne le nom de la vue correspondante
    }
    @GetMapping("/gestionPatient2")
    public String gestionPatient2(@ModelAttribute("user") Utilisateur user) {
        if (user != null){
            return "gestionPatient2";
        }
        else {
            // L'utilisateur n'est pas authentifié, redirige vers la page de connexion
            return "redirect:/login";
        }
    }
    @GetMapping("/dossierMedical")
    public String dossierMedical(@ModelAttribute("user") Utilisateur user) {
        if (user != null){
            return "dossierMedical";
        }
        else {
            // L'utilisateur n'est pas authentifié, redirige vers la page de connexion
            return "redirect:/login";
        }
    }
    @GetMapping("/login")
    public String login() {

        return "login"; // Retourne le nom de la vue correspondante
    }

    @GetMapping("/index")
    public String index(@ModelAttribute("user") Utilisateur user, Model model) {
        if (user != null) {
            // Récupérer les informations de l'utilisateur connecté depuis la base de données
            Utilisateur utilisateurConnecte = utilisateurRepository.findById(user.getId()).orElse(null);
            // Vérifier si l'utilisateur existe dans la base de données
            if (utilisateurConnecte != null) {
                // Ajouter les informations de l'utilisateur au modèle
                model.addAttribute("utilisateur", utilisateurConnecte);
            } else {
                // Gérer le cas où l'utilisateur n'est pas trouvé dans la base de données
                // Peut-être rediriger vers une page d'erreur ou afficher un message approprié
            }
            return "index";
        } else {
            // L'utilisateur n'est pas authentifié, rediriger vers la page de connexion
            return "redirect:/login";
        }
    }
    @GetMapping("/ajouterpatient")
    public String ajouterpatient(@ModelAttribute("user") Utilisateur user) {
        if (user != null) {
            return "ajouterpatient";
        } else {
            // L'utilisateur n'est pas authentifié, rediriger vers la page de connexion
            return "redirect:/login";
        }
    }
    @GetMapping("/patients")
    public String getPatients(@ModelAttribute("user") Utilisateur user,Model model) {
        if(user!=null){
            List<Patient> patients = patientRepository.findAll();
            model.addAttribute("patients", patients);
            return "gestionPatient2";
        }else {
            return "redirect:/login";
        }
    }
    @RequestMapping("/home")
    public String loginSubmit(){
        return "/pages/landing_page";
    }
    @RequestMapping("/login2")
    public String loginPage(){
        return "auth-login";
    }

}
