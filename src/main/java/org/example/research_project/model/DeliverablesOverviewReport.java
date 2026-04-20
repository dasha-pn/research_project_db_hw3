package org.example.research_project.model;

public record DeliverablesOverviewReport(
        String projectInternalCode,
        int totalDeliverables,
        int acceptedDeliverables,
        int pendingDeliverables
) {
}