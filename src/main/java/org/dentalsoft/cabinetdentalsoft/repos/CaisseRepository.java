package org.dentalsoft.cabinetdentalsoft.repos;

import org.dentalsoft.cabinetdentalsoft.entities.AntecendentMedical;
import org.dentalsoft.cabinetdentalsoft.entities.Caisse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CaisseRepository extends JpaRepository<AntecendentMedical,Long> {
    void save(Caisse caisse1);

}
