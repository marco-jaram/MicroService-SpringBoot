package com.mtec.reportms.service;

import com.mtec.reportms.repository.CompaniesRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class ReportServiceImpl implements ReportService {

    private final CompaniesRepository companiesRepository;



    @Override
    public String makeReport(String name) {
        return "";
    }

    @Override
    public String saveReport(String nameReport) {
        return "";
    }

    @Override
    public void deleteReport(String name) {

    }
}
