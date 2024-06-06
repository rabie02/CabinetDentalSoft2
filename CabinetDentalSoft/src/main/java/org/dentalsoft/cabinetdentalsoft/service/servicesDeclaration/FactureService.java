package org.dentalsoft.cabinetdentalsoft.service.servicesDeclaration;

import org.dentalsoft.cabinetdentalsoft.entities.Facture;

import java.util.List;

public interface FactureService {
    public Facture saveFacture(Long consultationID,Facture facture);

    List<Facture> findAll();
    //public List<Facture> getAllFactures();
    //public void deleteFacture(Long id);
}
