package org.example.research_project.repository;

import org.example.research_project.model.Deliverable;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface DeliverableRepository extends Repository<Deliverable, String> {

    @Query("""
        SELECT project_internal_code, deliverable_planned
        FROM deliverable
        ORDER BY project_internal_code, deliverable_planned
        """)
    List<Deliverable> findAllDeliverables();
}