package org.dentalsoft.cabinetdentalsoft.controller;

import lombok.AllArgsConstructor;
import org.dentalsoft.cabinetdentalsoft.entities.Dentiste;
import org.dentalsoft.cabinetdentalsoft.entities.DossierMedical;
import org.dentalsoft.cabinetdentalsoft.entities.Patient;
import org.dentalsoft.cabinetdentalsoft.repos.DossierMedicalRepository;
import org.dentalsoft.cabinetdentalsoft.service.servicesDeclaration.DentisteService;
import org.dentalsoft.cabinetdentalsoft.service.servicesDeclaration.DossierMedicalService;
import org.dentalsoft.cabinetdentalsoft.service.servicesDeclaration.PatientService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@org.springframework.stereotype.Controller
public class Controller {

}
