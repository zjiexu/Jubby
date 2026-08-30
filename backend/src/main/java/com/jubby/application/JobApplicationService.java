package com.jubby.application;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobApplicationService {

  private final JobApplicationRepository jobApplicationRepository;

  public JobApplicationService(JobApplicationRepository jobApplicationRepository) {
    this.jobApplicationRepository = jobApplicationRepository;
  }

  public List<JobApplication> findAll() {
    return jobApplicationRepository.findAll();
  }

  public Optional<JobApplication> findById(Long id) {
    return jobApplicationRepository.findById(id);
  }

  public JobApplication create(JobApplicationRequest request) {
    JobApplication application = new JobApplication();

    application.setCompany(request.getCompany());
    application.setPosition(request.getPosition());
    application.setLocation(request.getLocation());
    application.setSalary(request.getSalary());
    application.setJobUrl(request.getJobUrl());
    application.setStatus(request.getStatus());
    application.setApplicationDate(request.getApplicationDate());
    application.setNotes(request.getNotes());

    return jobApplicationRepository.save(application);
  }

  public Optional<JobApplication> update(Long id, JobApplicationRequest request) {
    return jobApplicationRepository.findById(id).map(existingApplication -> {
      existingApplication.setCompany(request.getCompany());
      existingApplication.setPosition(request.getPosition());
      existingApplication.setLocation(request.getLocation());
      existingApplication.setSalary(request.getSalary());
      existingApplication.setJobUrl(request.getJobUrl());
      existingApplication.setStatus(request.getStatus());
      existingApplication.setApplicationDate(request.getApplicationDate());
      existingApplication.setNotes(request.getNotes());

      return jobApplicationRepository.save(existingApplication);
    });
  }

  public boolean deleteById(Long id) {
    if (!jobApplicationRepository.existsById(id)) {
      return false;
    }

    jobApplicationRepository.deleteById(id);
    return true;
  }
}
