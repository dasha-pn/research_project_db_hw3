package org.example.research_project.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;

@Table("research_project")
public class ResearchProject {

    @Id
    @Column("project_internal_code")
    private String projectInternalCode;

    @Column("grant_agreement_number")
    private String grantAgreementNumber;

    @Column("project_title")
    private String projectTitle;

    @Column("start_date")
    private LocalDate startDate;

    @Column("project_status")
    private String projectStatus;

    @Column("host_org_name")
    private String hostOrgName;

    public ResearchProject() {
    }

    public ResearchProject(String projectInternalCode,
                           String grantAgreementNumber,
                           String projectTitle,
                           LocalDate startDate,
                           String projectStatus,
                           String hostOrgName) {
        this.projectInternalCode = projectInternalCode;
        this.grantAgreementNumber = grantAgreementNumber;
        this.projectTitle = projectTitle;
        this.startDate = startDate;
        this.projectStatus = projectStatus;
        this.hostOrgName = hostOrgName;
    }

    public String getProjectInternalCode() {
        return projectInternalCode;
    }

    public void setProjectInternalCode(String projectInternalCode) {
        this.projectInternalCode = projectInternalCode;
    }

    public String getGrantAgreementNumber() {
        return grantAgreementNumber;
    }

    public void setGrantAgreementNumber(String grantAgreementNumber) {
        this.grantAgreementNumber = grantAgreementNumber;
    }

    public String getProjectTitle() {
        return projectTitle;
    }

    public void setProjectTitle(String projectTitle) {
        this.projectTitle = projectTitle;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public String getProjectStatus() {
        return projectStatus;
    }

    public void setProjectStatus(String projectStatus) {
        this.projectStatus = projectStatus;
    }

    public String getHostOrgName() {
        return hostOrgName;
    }

    public void setHostOrgName(String hostOrgName) {
        this.hostOrgName = hostOrgName;
    }
}