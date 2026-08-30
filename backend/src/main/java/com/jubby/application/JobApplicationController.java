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
  public List<JobApplicationResponse> getAllApplications() {
    return jobApplicationService.findAll()
      .stream()
      .map(JobApplicationResponse::from)
      .toList();
  }

  @GetMapping("/{id}")
  public ResponseEntity<JobApplicationResponse> getApplicationById(@PathVariable Long id) {
    return jobApplicationService.findById(id)
      .map(JobApplicationResponse::from)
      .map(ResponseEntity::ok)
      .orElse(ResponseEntity.notFound().build());
  }

  @PostMapping
  public JobApplicationResponse createApplication(@Valid @RequestBody JobApplicationRequest request) {
    JobApplication application = jobApplicationService.create(request);
    return JobApplicationResponse.from(application);
  }

  @PutMapping("/{id}")
  public ResponseEntity<JobApplicationResponse> updateApplication(
    @PathVariable Long id,
    @Valid @RequestBody JobApplicationRequest request
  ) {
    return jobApplicationService.update(id, request)
      .map(JobApplicationResponse::from)
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
