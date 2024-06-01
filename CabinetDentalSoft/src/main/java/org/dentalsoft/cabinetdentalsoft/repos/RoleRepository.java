package org.dentalsoft.cabinetdentalsoft.repos;

import org.dentalsoft.cabinetdentalsoft.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,String> {
    Role findByNomRole(String nomRole);
}
