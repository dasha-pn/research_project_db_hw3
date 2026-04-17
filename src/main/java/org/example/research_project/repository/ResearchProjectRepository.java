package org.example.research_project.repository;

import org.example.research_project.model.ResearchProject;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResearchProjectRepository extends CrudRepository<ResearchProject, String> {

    @Query("""
        SELECT project_internal_code,
               grant_agreement_number,
               project_title,
               start_date,
               project_status,
               host_org_name
        FROM research_project
        ORDER BY project_internal_code
        """)
    List<ResearchProject> findAllProjects();
}