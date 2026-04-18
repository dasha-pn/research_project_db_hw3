package org.example.research_project.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDate;

@Table("task_update")
public class TaskUpdate {

    @Id
    @Column("update_id")
    private Integer updateId;

    @Column("project_internal_code")
    private String projectInternalCode;

    @Column("project_task")
    private String projectTask;

    @Column("task_status")
    private String taskStatus;

    @Column("completion_date")
    private LocalDate completionDate;

    @Column("task_progress_percent")
    private BigDecimal taskProgressPercent;

    public TaskUpdate() {
    }

    public Integer getUpdateId() {
        return updateId;
    }

    public void setUpdateId(Integer updateId) {
        this.updateId = updateId;
    }

    public String getProjectInternalCode() {
        return projectInternalCode;
    }

    public void setProjectInternalCode(String projectInternalCode) {
        this.projectInternalCode = projectInternalCode;
    }

    public String getProjectTask() {
        return projectTask;
    }

    public void setProjectTask(String projectTask) {
        this.projectTask = projectTask;
    }

    public String getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }

    public LocalDate getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(LocalDate completionDate) {
        this.completionDate = completionDate;
    }

    public BigDecimal getTaskProgressPercent() {
        return taskProgressPercent;
    }

    public void setTaskProgressPercent(BigDecimal taskProgressPercent) {
        this.taskProgressPercent = taskProgressPercent;
    }
}
