package org.example.research_project.repository;

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
                    SUM(CASE WHEN tu.task_status = 'Completed' THEN 1 ELSE 0 END) AS completedTasks,
                    SUM(CASE WHEN tu.task_status = 'In Progress' THEN 1 ELSE 0 END) AS inProgressTasks,
                    SUM(CASE WHEN tu.task_status = 'Pending' THEN 1 ELSE 0 END) AS pendingTasks,
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
}