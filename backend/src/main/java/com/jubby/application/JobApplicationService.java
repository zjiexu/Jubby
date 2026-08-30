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

  public JobApplication create(JobApplication application) {
    if (application.getStatus() == null) {
      application.setStatus(ApplicationStatus.SAVED);
    }

    return jobApplicationRepository.save(application);
  }

  public Optional<JobApplication> update(Long id, JobApplication updatedApplication) {
    return jobApplicationRepository.findById(id).map(existingApplication -> {
      existingApplication.setCompany(updatedApplication.getCompany());
      existingApplication.setPosition(updatedApplication.getPosition());
      existingApplication.setLocation(updatedApplication.getLocation());
      existingApplication.setSalary(updatedApplication.getSalary());
      existingApplication.setJobUrl(updatedApplication.getJobUrl());
      existingApplication.setStatus(updatedApplication.getStatus());
      existingApplication.setApplicatonDate(updatedApplication.getApplicationDate());
      existingApplication.setNotes(updatedApplication.getNotes());

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
