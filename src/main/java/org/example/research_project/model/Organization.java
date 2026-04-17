package org.example.research_project.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("organization")
public class Organization {

    @Id
    @Column("org_name")
    private String orgName;

    @Column("org_website")
    private String orgWebsite;

    @Column("org_country")
    private String orgCountry;

    public Organization() {
    }

    public Organization(String orgName, String orgWebsite, String orgCountry) {
        this.orgName = orgName;
        this.orgWebsite = orgWebsite;
        this.orgCountry = orgCountry;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrgWebsite() {
        return orgWebsite;
    }

    public void setOrgWebsite(String orgWebsite) {
        this.orgWebsite = orgWebsite;
    }

    public String getOrgCountry() {
        return orgCountry;
    }

    public void setOrgCountry(String orgCountry) {
        this.orgCountry = orgCountry;
    }
}