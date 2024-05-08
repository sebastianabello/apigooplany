package org.example.services.company.management;

import lombok.RequiredArgsConstructor;
import org.example.services.company.CompanyDTO;
import org.example.services.company.CompanyExternalAPI;
import org.example.services.company.mapper.CompanyMapper;
import org.example.services.company.repository.CompanyRepository;
import org.example.services.event.EventInternalAPI;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompanyManagement implements CompanyExternalAPI {

    private final CompanyRepository repository;
    private final EventInternalAPI eventInternalAPI;
    private final CompanyMapper mapper;


    @Override
    public CompanyDTO findByIdWithEvents(Long companyId) {
        return null;
    }

    @Override
    public CompanyDTO findByIdWithEventsAndUser(Long companyId) {
        return null;
    }

    @Override
    public CompanyDTO add(CompanyDTO company) {
        return null;
    }

    @Override
    public void remove(Long id) {

    }
}
