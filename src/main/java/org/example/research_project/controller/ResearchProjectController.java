package org.example.research_project.controller;

import org.example.research_project.model.ResearchProject;
import org.example.research_project.repository.OrganizationRepository;
import org.example.research_project.repository.ResearchProjectRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/projects")
public class ResearchProjectController {

    private final ResearchProjectRepository researchProjectRepository;
    private final OrganizationRepository organizationRepository;
    private final JdbcTemplate jdbcTemplate;

    public ResearchProjectController(ResearchProjectRepository researchProjectRepository,
                                     OrganizationRepository organizationRepository,
                                     JdbcTemplate jdbcTemplate) {
        this.researchProjectRepository = researchProjectRepository;
        this.organizationRepository = organizationRepository;
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping
    public String listProjects(Model model) {
        model.addAttribute("projects", researchProjectRepository.findAllProjects());
        return "projects/list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("project", new ResearchProject());
        model.addAttribute("organizations", organizationRepository.findAllOrganizations());
        model.addAttribute("statuses", new String[]{"active", "completed", "paused"});
        return "projects/create";
    }

    @PostMapping("/create")
    public String createProject(@ModelAttribute("project") ResearchProject project) {
        jdbcTemplate.update("""
            INSERT INTO research_project (
                project_internal_code,
                grant_agreement_number,
                project_title,
                start_date,
                project_status,
                host_org_name
            )
            VALUES (?, ?, ?, ?, ?, ?)
            """,
            project.getProjectInternalCode(),
            project.getGrantAgreementNumber(),
            project.getProjectTitle(),
            project.getStartDate(),
            project.getProjectStatus(),
            project.getHostOrgName()
        );

        return "redirect:/projects?created";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") String id, Model model) {
        ResearchProject project = researchProjectRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid project id: " + id));

        model.addAttribute("project", project);
        model.addAttribute("organizations", organizationRepository.findAllOrganizations());
        model.addAttribute("statuses", new String[]{"active", "completed", "paused"});
        return "projects/edit";
    }

    @PostMapping("/edit/{id}")
    public String updateProject(@PathVariable("id") String id,
                                @ModelAttribute("project") ResearchProject project) {
        jdbcTemplate.update("""
            UPDATE research_project
            SET grant_agreement_number = ?,
                project_title = ?,
                start_date = ?,
                project_status = ?,
                host_org_name = ?
            WHERE project_internal_code = ?
            """,
            project.getGrantAgreementNumber(),
            project.getProjectTitle(),
            project.getStartDate(),
            project.getProjectStatus(),
            project.getHostOrgName(),
            id
        );

        return "redirect:/projects?updated";
    }

    @GetMapping("/delete/{id}")
    public String showDeleteForm(@PathVariable("id") String id, Model model) {
        ResearchProject project = researchProjectRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid project id: " + id));

        model.addAttribute("project", project);
        return "projects/delete";
    }

    @PostMapping("/delete/{id}")
    public String deleteProject(@PathVariable("id") String id) {
        jdbcTemplate.update("""
            DELETE FROM research_project
            WHERE project_internal_code = ?
            """, id);

        return "redirect:/projects?deleted";
    }
}