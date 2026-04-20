package org.example.research_project.model;

public record ProjectProgressSummary(
        String projectInternalCode,
        String projectTitle,
        int totalTasks,
        int completedTasks,
        int inProgressTasks,
        int pendingTasks,
        double overallProgressPercent
) {
}