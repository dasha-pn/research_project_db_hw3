package org.example.research_project.model;

public record FundingUsageReport(
        String fundingSource,
        double totalBudget,
        double spendBudget,
        double remainingBudget
) {
}