package org.example.research_project.controller;

import org.example.research_project.repository.ReportRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReportController {

    private final ReportRepository reportRepository;

    public ReportController(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    @GetMapping("/reports")
    public String reportsHome() {
        return "reports/index";
    }

    @GetMapping("/reports/project-progress")
    public String projectProgressReport(Model model) {
        model.addAttribute("reports", reportRepository.findProjectProgressSummaries());
        return "reports/project-progress";
    }
}