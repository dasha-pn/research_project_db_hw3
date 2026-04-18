package org.example.research_project.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;

@Table("deliverable_submission")
public class DeliverableSubmission {

    @Id
    @Column("submission_id")
    private Integer submissionId;

    @Column("project_internal_code")
    private String projectInternalCode;

    @Column("deliverable_planned")
    private String deliverablePlanned;

    @Column("submission_date")
    private LocalDate submissionDate;

    @Column("status")
    private String status;

    @Column("file_reference")
    private String fileReference;

    public DeliverableSubmission() {
    }

    public Integer getSubmissionId() {
        return submissionId;
    }

    public void setSubmissionId(Integer submissionId) {
        this.submissionId = submissionId;
    }

    public String getProjectInternalCode() {
        return projectInternalCode;
    }

    public void setProjectInternalCode(String projectInternalCode) {
        this.projectInternalCode = projectInternalCode;
    }

    public String getDeliverablePlanned() {
        return deliverablePlanned;
    }

    public void setDeliverablePlanned(String deliverablePlanned) {
        this.deliverablePlanned = deliverablePlanned;
    }

    public LocalDate getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(LocalDate submissionDate) {
        this.submissionDate = submissionDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFileReference() {
        return fileReference;
    }

    public void setFileReference(String fileReference) {
        this.fileReference = fileReference;
    }
}