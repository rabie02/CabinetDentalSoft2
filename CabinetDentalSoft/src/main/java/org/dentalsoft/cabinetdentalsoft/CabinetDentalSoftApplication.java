package org.dentalsoft.cabinetdentalsoft;

import org.dentalsoft.cabinetdentalsoft.entities.*;
import org.dentalsoft.cabinetdentalsoft.enums.*;
import org.dentalsoft.cabinetdentalsoft.repos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class CabinetDentalSoftApplication {

	public static void main(String[] args) {

		SpringApplication.run(CabinetDentalSoftApplication.class, args);
	}

	@Component
	public class DataLoader implements CommandLineRunner {

		@Autowired
		UtilisateurRepository utilisateurRepository;
		@Autowired
		PatientRepository patientRepository;
		@Autowired
		DentisteRepository dentisteRepository;
		@Autowired
		RoleRepository roleRepository;
		@Autowired
		DossierMedicalRepository dossierMedicalRepository;
		@Autowired
		ConsultationRepository consultationRepository;
		@Autowired
		SituationFinancierRepository situationFinancierRepository;

		@Override
		public void run(String... args) throws Exception {


		}
	}
}
