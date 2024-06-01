package com.mtec.companiescrud.controllers;

import com.mtec.companiescrud.entities.Company;
import com.mtec.companiescrud.service.CompanyService;
import io.micrometer.core.annotation.Timed;
import io.micrometer.observation.annotation.Observed;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.NoSuchElementException;

@RestController
@AllArgsConstructor
@RequestMapping(path = "company")
@Slf4j
@Tag(name = "Companies resource")
public class CompanyController {
    private final CompanyService companyService;

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Empresa no encontrada");
    }

    @Operation(summary = "get a company given a company name")
    @GetMapping(path = "{name}")
    @Observed(name = "company.name")
    @Timed(value = "company.name")
    public ResponseEntity<Company> get(@PathVariable String name) {



        log.info("GET: company {}", name);
        Company company = this.companyService.readByName(name);
        return ResponseEntity.ok(company);
    }

    @Operation(summary = "save in DB a company given a company from body")
    @PostMapping
    @Observed(name = "company.save")
    @Timed(value = "company.save")
    public ResponseEntity<Company> post(@RequestBody Company company) {
        log.info("Post: company {}", company.getName());
        return ResponseEntity.created(
                        URI.create(this.companyService.create(company).getName()))
                .build();
    }

    @Operation(summary = "update in DB a company given a company from body")
    @PutMapping(path = "{name}")
    public ResponseEntity<Company> put(@RequestBody Company company,
                                       @PathVariable String name) {
        log.info("PUT: company {}", name);
        return ResponseEntity.ok(this.companyService.update(company, name));
    }

    @Operation(summary = "delete in DB a company given a company name")
    @DeleteMapping(path = "{name}")
    public ResponseEntity<?> delete(@PathVariable String name) {
        log.info("DELETE: company {}", name);
        this.companyService.delete(name);
        return ResponseEntity.noContent().build();
    }


}
