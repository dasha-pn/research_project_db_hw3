package org.example.research_project.model;

import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("deliverable")
public class Deliverable {

    @Column("project_internal_code")
    private String projectInternalCode;

    @Column("deliverable_planned")
    private String deliverablePlanned;

    public Deliverable() {
    }

    public Deliverable(String projectInternalCode, String deliverablePlanned) {
        this.projectInternalCode = projectInternalCode;
        this.deliverablePlanned = deliverablePlanned;
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

    public String getDeliverableReference() {
        return projectInternalCode + " | " + deliverablePlanned;
    }
}