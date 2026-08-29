package com.jubby.application;

import org.springframework.stereotype.Service;

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
}
