package org.example.services.company.mapper;

import org.example.services.company.CompanyDTO;
import org.example.services.company.model.Company;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CompanyMapper {
    CompanyDTO companyToCompanyDTO(Company company);
    Company companyDTOToCompany(CompanyDTO companyDTO);
}
