package org.example.research_project.controller;

import org.example.research_project.model.DeliverableSubmission;
import org.example.research_project.repository.DeliverableRepository;
import org.example.research_project.repository.DeliverableSubmissionRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/deliverables")
public class DeliverableSubmissionController {

    private final DeliverableSubmissionRepository submissionRepository;
    private final DeliverableRepository deliverableRepository;
    private final JdbcTemplate jdbcTemplate;

    public DeliverableSubmissionController(DeliverableSubmissionRepository submissionRepository,
                                           DeliverableRepository deliverableRepository,
                                           JdbcTemplate jdbcTemplate) {
        this.submissionRepository = submissionRepository;
        this.deliverableRepository = deliverableRepository;
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("submissions", submissionRepository.findAllSubmissions());
        return "deliverables/list";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("submission", new DeliverableSubmission());
        model.addAttribute("deliverables", deliverableRepository.findAllDeliverables());
        model.addAttribute("statuses", new String[]{"pending", "accepted", "rejected"});
        return "deliverables/create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("submission") DeliverableSubmission submission,
                         @RequestParam("deliverableReference") String deliverableReference) {

        String[] parts = deliverableReference.split("\\|");
        String projectCode = parts[0].trim();
        String deliverablePlanned = parts[1].trim();

        jdbcTemplate.update("""
            INSERT INTO deliverable_submission (
                submission_id,
                project_internal_code,
                deliverable_planned,
                submission_date,
                status,
                file_reference
            )
            VALUES (?, ?, ?, ?, ?, ?)
            """,
            submission.getSubmissionId(),
            projectCode,
            deliverablePlanned,
            submission.getSubmissionDate(),
            submission.getStatus(),
            submission.getFileReference()
        );

        return "redirect:/deliverables?created";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Integer id, Model model) {
        DeliverableSubmission s = submissionRepository.findById(id).orElseThrow();

        model.addAttribute("submission", s);
        model.addAttribute("deliverables", deliverableRepository.findAllDeliverables());
        model.addAttribute("statuses", new String[]{"pending", "accepted", "rejected"});
        return "deliverables/edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@PathVariable Integer id,
                         @ModelAttribute("submission") DeliverableSubmission submission,
                         @RequestParam("deliverableReference") String deliverableReference) {

        String[] parts = deliverableReference.split("\\|");
        String projectCode = parts[0].trim();
        String deliverablePlanned = parts[1].trim();

        jdbcTemplate.update("""
            UPDATE deliverable_submission
            SET project_internal_code = ?,
                deliverable_planned = ?,
                submission_date = ?,
                status = ?,
                file_reference = ?
            WHERE submission_id = ?
            """,
            projectCode,
            deliverablePlanned,
            submission.getSubmissionDate(),
            submission.getStatus(),
            submission.getFileReference(),
            id
        );

        return "redirect:/deliverables?updated";
    }

    @GetMapping("/delete/{id}")
    public String deleteForm(@PathVariable Integer id, Model model) {
        DeliverableSubmission s = submissionRepository.findById(id).orElseThrow();
        model.addAttribute("submission", s);
        return "deliverables/delete";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        jdbcTemplate.update("""
            DELETE FROM deliverable_submission
            WHERE submission_id = ?
            """, id);

        return "redirect:/deliverables?deleted";
    }
}