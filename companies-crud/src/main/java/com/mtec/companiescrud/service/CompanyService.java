package com.mtec.companiescrud.service;

import com.mtec.companiescrud.entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyService {

    Company create(Company company);
    Company readByName(String name);
    Company update(Company company, String name);
    void delete(String name);
}
