package org.example.research_project.controller;

import org.example.research_project.model.Task;
import org.example.research_project.model.TaskUpdate;
import org.example.research_project.repository.TaskRepository;
import org.example.research_project.repository.TaskUpdateRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/task-updates")
public class TaskUpdateController {

    private final TaskUpdateRepository taskUpdateRepository;
    private final TaskRepository taskRepository;
    private final JdbcTemplate jdbcTemplate;

    public TaskUpdateController(TaskUpdateRepository taskUpdateRepository,
                                TaskRepository taskRepository,
                                JdbcTemplate jdbcTemplate) {
        this.taskUpdateRepository = taskUpdateRepository;
        this.taskRepository = taskRepository;
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping
    public String listTaskUpdates(Model model) {
        model.addAttribute("taskUpdates", taskUpdateRepository.findAllTaskUpdates());
        return "task-updates/list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("taskUpdate", new TaskUpdate());
        model.addAttribute("tasks", taskRepository.findAllTasks());
        model.addAttribute("statuses", new String[]{"pending", "in_progress", "completed"});
        return "task-updates/create";
    }

    @PostMapping("/create")
    public String createTaskUpdate(@ModelAttribute("taskUpdate") TaskUpdate taskUpdate,
                                   @RequestParam("taskReference") String taskReference) {
        String[] parts = taskReference.split("\\|");
        String projectCode = parts[0].trim();
        String projectTask = parts[1].trim();

        jdbcTemplate.update("""
            INSERT INTO task_update (
                update_id,
                project_internal_code,
                project_task,
                task_status,
                completion_date,
                task_progress_percent
            )
            VALUES (?, ?, ?, ?, ?, ?)
            """,
            taskUpdate.getUpdateId(),
            projectCode,
            projectTask,
            taskUpdate.getTaskStatus(),
            taskUpdate.getCompletionDate(),
            taskUpdate.getTaskProgressPercent()
        );

        return "redirect:/task-updates?created";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model) {
        TaskUpdate taskUpdate = taskUpdateRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid task update id: " + id));

        model.addAttribute("taskUpdate", taskUpdate);
        model.addAttribute("tasks", taskRepository.findAllTasks());
        model.addAttribute("statuses", new String[]{"pending", "in_progress", "completed"});
        return "task-updates/edit";
    }

    @PostMapping("/edit/{id}")
    public String updateTaskUpdate(@PathVariable("id") Integer id,
                                   @ModelAttribute("taskUpdate") TaskUpdate taskUpdate,
                                   @RequestParam("taskReference") String taskReference) {
        String[] parts = taskReference.split("\\|");
        String projectCode = parts[0].trim();
        String projectTask = parts[1].trim();

        jdbcTemplate.update("""
            UPDATE task_update
            SET project_internal_code = ?,
                project_task = ?,
                task_status = ?,
                completion_date = ?,
                task_progress_percent = ?
            WHERE update_id = ?
            """,
            projectCode,
            projectTask,
            taskUpdate.getTaskStatus(),
            taskUpdate.getCompletionDate(),
            taskUpdate.getTaskProgressPercent(),
            id
        );

        return "redirect:/task-updates?updated";
    }

    @GetMapping("/delete/{id}")
    public String showDeleteForm(@PathVariable("id") Integer id, Model model) {
        TaskUpdate taskUpdate = taskUpdateRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid task update id: " + id));

        model.addAttribute("taskUpdate", taskUpdate);
        return "task-updates/delete";
    }

    @PostMapping("/delete/{id}")
    public String deleteTaskUpdate(@PathVariable("id") Integer id) {
        jdbcTemplate.update("""
            DELETE FROM task_update
            WHERE update_id = ?
            """, id);

        return "redirect:/task-updates?deleted";
    }
}