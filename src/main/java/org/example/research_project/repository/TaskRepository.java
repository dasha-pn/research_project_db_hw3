package org.example.research_project.repository;

import org.example.research_project.model.Task;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface TaskRepository extends Repository<Task, String> {

    @Query("""
        SELECT project_task, project_internal_code
        FROM task
        ORDER BY project_internal_code, project_task
        """)
    List<Task> findAllTasks();
}