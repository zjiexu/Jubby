package com.jubby.application;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobApplicationRepository extends JpaRepository<JobApplication, Long> {

  List<JobApplication> findByCompanyContainingIgnoreCaseOrPositionContainingIgnoreCase(
    String company,
    String position
  );

  List<JobApplication> findByStatus(ApplicationStatus status);
}
