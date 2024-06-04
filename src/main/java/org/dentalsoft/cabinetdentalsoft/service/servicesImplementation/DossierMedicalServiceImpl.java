package org.dentalsoft.cabinetdentalsoft.service.servicesImplementation;

import org.dentalsoft.cabinetdentalsoft.entities.Consultation;
import org.dentalsoft.cabinetdentalsoft.entities.DossierMedical;
import org.dentalsoft.cabinetdentalsoft.repos.ConsultationRepository;
import org.dentalsoft.cabinetdentalsoft.repos.DossierMedicalRepository;
import org.dentalsoft.cabinetdentalsoft.service.servicesDeclaration.DossierMedicalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DossierMedicalServiceImpl implements DossierMedicalService {
    private final DossierMedicalRepository dossierMedicalRepository;
    private final ConsultationRepository consultationRepository;

    @Autowired
    public DossierMedicalServiceImpl(DossierMedicalRepository dossierMedicalRepository, ConsultationRepository consultationRepository) {
        this.dossierMedicalRepository = dossierMedicalRepository;
        this.consultationRepository = consultationRepository;
    }

    @Override
    public List<DossierMedical> findAll() {
        return dossierMedicalRepository.findAll();
    }

    @Override
    public DossierMedical findByNumeroDossier(Long numeroDossier) {
        return dossierMedicalRepository.findByNumeroDossier(numeroDossier);
    }

    @Override
    public Consultation findConsultationById(Long consultationId) {
        return consultationRepository.findById(consultationId).orElse(null);
    }

    @Override
    public List<Consultation> findConsultationsByDossierMedical(DossierMedical dossierMedical) {
        return consultationRepository.findAllByDossierMedical(dossierMedical);
    }

}

