package org.example.research_project.repository;

import org.example.research_project.model.TaskUpdate;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskUpdateRepository extends CrudRepository<TaskUpdate, Integer> {

    @Query("""
        SELECT update_id,
               project_internal_code,
               project_task,
               task_status,
               completion_date,
               task_progress_percent
        FROM task_update
        ORDER BY update_id
        """)
    List<TaskUpdate> findAllTaskUpdates();
}