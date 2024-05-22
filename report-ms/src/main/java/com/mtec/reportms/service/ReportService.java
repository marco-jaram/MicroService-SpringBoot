package com.mtec.reportms.service;

public interface ReportService {
    String makeReport(String name);

    String saveReport(String nameReport);

    void deleteReport(String name);
}
