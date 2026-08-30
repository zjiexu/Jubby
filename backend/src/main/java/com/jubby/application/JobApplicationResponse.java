package com.jubby.application;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class JobApplicationResponse {
  
  private Long id;
  private String company;
  private String position;
  private String location;
  private String salary;
  private String jobUrl;
  private ApplicationStatus status;
  private LocalDate applicationDate;
  private String notes;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;

  public JobApplicationResponse(
    Long id,
    String company,
    String position,
    String location,
    String salary,
    String jobUrl,
    ApplicationStatus status,
    LocalDate applicationDate,
    String notes,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
  ) {
    this.id = id;
    this.company = company;
    this.position = position;
    this.location = location;
    this.salary = salary;
    this.jobUrl = jobUrl;
    this.status = status;
    this.applicationDate = applicationDate;
    this.notes = notes;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }

  public static JobApplicationResponse from(JobApplication application) {
    return new JobApplicationResponse(
      application.getId(),
      application.getCompany(),
      application.getPosition(),
      application.getLocation(),
      application.getSalary(),
      application.getJobUrl(),
      application.getStatus(),
      application.getApplicationDate(),
      application.getNotes(),
      application.getCreatedAt(),
      application.getUpdatedAt()
    );
  }

  public Long getId() {
    return id;
  }

  public String getCompany() {
    return company;
  }

  public String getPosition() {
    return position;
  }

  public String getLocation() {
    return location;
  }

  public String getSalary() {
    return salary;
  }

  public String getJobUrl() {
    return jobUrl;
  }

  public ApplicationStatus getStatus() {
    return status;
  }

  public LocalDate getApplicationDate() {
    return applicationDate;
  }

  public String getNotes() {
    return notes;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public LocalDateTime getUpdatedAt() {
    return updatedAt;
  }
}
