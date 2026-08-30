package com.jubby.application;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.jubby.application.JobApplicationService;

import java.util.List;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/applications")
public class JobApplicationController {

  private final JobApplicationService jobApplicationService;

  public JobApplicationController(JobApplicationService jobApplicationService) {
    this.jobApplicationService = jobApplicationService;
  }

  @GetMapping
  public List<JobApplication> getAllApplications() {
    return jobApplicationService.findAll();
  }

  @GetMapping("/{id}")
  public ResponseEntity<JobApplication> getApplicationById(@PathVariable Long id) {
    return jobApplicationService.findById(id)
      .map(ResponseEntity::ok)
      .orElse(ResponseEntity.notFound().build());
  }

  @PostMapping
  public JobApplication createApplication(@Valid @RequestBody JobApplicationRequest request) {
    return jobApplicationService.create(request);
  }

  @PutMapping("/{id}")
  public ResponseEntity<JobApplication> updateApplication(
    @PathVariable Long id,
    @Valid @RequestBody JobApplicationRequest request
  ) {
    return jobApplicationService.update(id, request)
      .map(ResponseEntity::ok)
      .orElse(ResponseEntity.notFound().build());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteApplication(@PathVariable Long id) {
    boolean deleted = jobApplicationService.deleteById(id);

    if (!deleted) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.noContent().build();
  }
}
