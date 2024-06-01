package org.dentalsoft.cabinetdentalsoft.repos;

import org.dentalsoft.cabinetdentalsoft.entities.Dentiste;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DentisteRepository extends JpaRepository<Dentiste,Long> {

    List<Dentiste> findAll();
    List<Dentiste> findByEmail(String email);;
    Dentiste findByNom(String nom);
    Optional<Dentiste> findById(Long id);

}
