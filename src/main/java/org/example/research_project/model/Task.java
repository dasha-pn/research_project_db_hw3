package org.example.research_project.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("task")
public class Task {

    @Id
    @Column("project_task")
    private String projectTask;

    @Column("project_internal_code")
    private String projectInternalCode;

    public Task() {
    }

    public Task(String projectTask, String projectInternalCode) {
        this.projectTask = projectTask;
        this.projectInternalCode = projectInternalCode;
    }

    public String getProjectTask() {
        return projectTask;
    }

    public void setProjectTask(String projectTask) {
        this.projectTask = projectTask;
    }

    public String getProjectInternalCode() {
        return projectInternalCode;
    }

    public void setProjectInternalCode(String projectInternalCode) {
        this.projectInternalCode = projectInternalCode;
    }

    public String getTaskKey() {
        return projectInternalCode + " | " + projectTask;
    }
}
