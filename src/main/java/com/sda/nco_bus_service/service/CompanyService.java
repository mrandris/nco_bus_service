package com.sda.nco_bus_service.service;

import com.sda.nco_bus_service.model.Company;
import com.sda.nco_bus_service.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {
    @Autowired
    private CompanyRepository companyRepository;

    public List<Company> findAllCompanies() {
        return companyRepository.findAll();
    }

    public Company findById(Integer idCompany) {
        return companyRepository.findById(idCompany).get();
    }

    public void deleteCompany(Integer idCompany) {
        companyRepository.deleteById(idCompany);
    }

    public Company saveCompany(Company company) {
        return companyRepository.save(company);
    }

    public Company updateCompany(Company company) {
        return companyRepository.save(company);
    }
}
