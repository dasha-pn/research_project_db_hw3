package org.example.research_project.service;

import org.example.research_project.model.ProjectProgressSummary;
import org.example.research_project.repository.ReportRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportService {

    private final ReportRepository reportRepository;

    public ReportService(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    public List<ProjectProgressSummary> getProjectProgressSummaries() {
        return reportRepository.findProjectProgressSummaries();
    }
}