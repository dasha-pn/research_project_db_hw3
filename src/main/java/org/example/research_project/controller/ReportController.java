package org.example.research_project.controller;

import org.example.research_project.service.ReportService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/reports")
    public String reportsHome() {
        return "reports/index";
    }

    @GetMapping("/reports/project-progress")
    public String projectProgressReport(Model model) {
        model.addAttribute("reports", reportService.getProjectProgressSummaries());
        return "reports/project-progress";
    }

    @GetMapping("/reports/funding")
    public String fundingReport(Model model) {
        model.addAttribute("reports", reportService.getFundingUsageReports());
        return "reports/funding-report";
    }

    @GetMapping("/reports/deliverables")
    public String deliverablesReport(Model model) {
        model.addAttribute("reports", reportService.getDeliverablesOverviewReports());
        return "reports/deliverables-report";
    }
}