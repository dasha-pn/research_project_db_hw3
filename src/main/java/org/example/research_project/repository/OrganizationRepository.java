package org.example.research_project.repository;

import org.example.research_project.model.Organization;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrganizationRepository extends CrudRepository<Organization, String> {

    @Query("""
        SELECT org_name, org_website, org_country
        FROM organization
        ORDER BY org_name
        """)
    List<Organization> findAllOrganizations();
}