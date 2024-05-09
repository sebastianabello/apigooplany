package org.example.services.company;

public interface CompanyExternalAPI {

    CompanyDTO findByIdWithEvents(Long companyId);
    CompanyDTO findByIdWithEventsAndUser(Long companyId);
    CompanyDTO add(CompanyDTO company);
    void remove(Long id);
}
