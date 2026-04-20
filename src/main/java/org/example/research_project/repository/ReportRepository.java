package org.example.research_project.repository;

import org.example.research_project.model.DeliverablesOverviewReport;
import org.example.research_project.model.FundingUsageReport;
import org.example.research_project.model.ProjectProgressSummary;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReportRepository {

    private final JdbcClient jdbcClient;

    public ReportRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public List<ProjectProgressSummary> findProjectProgressSummaries() {
        String sql = """
                SELECT
                    rp.project_internal_code AS projectInternalCode,
                    rp.project_title AS projectTitle,
                    COUNT(tu.project_task) AS totalTasks,
                    SUM(CASE WHEN tu.task_status = 'completed' THEN 1 ELSE 0 END) AS completedTasks,
                    SUM(CASE WHEN tu.task_status = 'in_progress' THEN 1 ELSE 0 END) AS inProgressTasks,
                    SUM(CASE WHEN tu.task_status = 'pending' THEN 1 ELSE 0 END) AS pendingTasks,
                    COALESCE(ROUND(AVG(tu.task_progress_percent), 2), 0) AS overallProgressPercent
                FROM research_project rp
                LEFT JOIN task_update tu
                    ON rp.project_internal_code = tu.project_internal_code
                GROUP BY rp.project_internal_code, rp.project_title
                ORDER BY rp.project_internal_code
                """;

        return jdbcClient.sql(sql)
                .query(ProjectProgressSummary.class)
                .list();
    }

    public List<FundingUsageReport> findFundingUsageReports() {
        String sql = """
                SELECT
                    funding_source AS fundingSource,
                    total_budget AS totalBudget,
                    spend_budget AS spendBudget,
                    (total_budget - spend_budget) AS remainingBudget
                FROM funding_source
                ORDER BY funding_source
                """;

        return jdbcClient.sql(sql)
                .query(FundingUsageReport.class)
                .list();
    }

    public List<DeliverablesOverviewReport> findDeliverablesOverviewReports() {
        String sql = """
                SELECT
                    ds.project_internal_code AS projectInternalCode,
                    COUNT(*) AS totalDeliverables,
                    SUM(CASE WHEN ds.status = 'accepted' THEN 1 ELSE 0 END) AS acceptedDeliverables,
                    SUM(CASE WHEN ds.status = 'pending' THEN 1 ELSE 0 END) AS pendingDeliverables
                FROM deliverable_submission ds
                GROUP BY ds.project_internal_code
                ORDER BY ds.project_internal_code
                """;

        return jdbcClient.sql(sql)
                .query(DeliverablesOverviewReport.class)
                .list();
    }
}