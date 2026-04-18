package org.example.research_project.repository;

import org.example.research_project.model.DeliverableSubmission;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeliverableSubmissionRepository extends CrudRepository<DeliverableSubmission, Integer> {

    @Query("""
        SELECT submission_id,
               project_internal_code,
               deliverable_planned,
               submission_date,
               status,
               file_reference
        FROM deliverable_submission
        ORDER BY submission_id
        """)
    List<DeliverableSubmission> findAllSubmissions();
}