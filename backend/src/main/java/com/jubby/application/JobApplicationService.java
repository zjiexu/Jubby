package com.jubby.application;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class JobApplicationService {

  private final List<JobApplication> applications = new ArrayList<>();
  private final AtomicLong nextId = new AtomicLong(1);

  public List<JobApplication> findAll() {
    return applications;
  }

  public Optional<JobApplication> findById(Long id) {
    return applications.stream()
      .filter(application -> application.getId().equals(id))
      .findFirst();
  }

  public JobApplication create(JobApplication application) {
    LocalDateTime now = LocalDateTime.now();

    application.setId(nextId.getAndIncrement());
    application.setCreatedAt(now);
    application.setUpdatedAt(now);

    if (application.getStatus() == null) {
      application.setStatus(ApplicationStatus.SAVED);
    }

    applications.add(application);
    return application;
  }

  public Optional<JobApplication> update(Long id, JobApplication updatedApplication) {
    return findById(id).map(existingApplication -> {
      existingApplication.setCompany(updatedApplication.getCompany());
      existingApplication.setPosition(updatedApplication.getPosition());
      existingApplication.setLocation(updatedApplication.getLocation());
      existingApplication.setSalary(updatedApplication.getSalary());
      existingApplication.setJobUrl(updatedApplication.getJobUrl());
      existingApplication.setStatus(updatedApplication.getStatus());
      existingApplication.setApplicatonDate(updatedApplication.getApplicationDate());
      existingApplication.setNotes(updatedApplication.getNotes());
      existingApplication.setUpdatedAt(LocalDateTime.now());

      return existingApplication;
    });
  }

  public boolean deleteById(Long id) {
    return applications.removeIf(application -> application.getId().equals(id));
  }
}
