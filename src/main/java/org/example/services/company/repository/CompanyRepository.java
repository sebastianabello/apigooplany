package org.example.services.company.repository;

import org.example.services.company.CompanyDTO;
import org.example.services.company.model.Company;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CompanyRepository extends CrudRepository<Company, Long> {

    @Query("""
           SELECT new org.example.services.company.CompanyDTO(c.id, c.name, c.address)
           FROM Company c
           WHERE c.id = :id
           """)
    CompanyDTO findDTOById(Long id);
}
