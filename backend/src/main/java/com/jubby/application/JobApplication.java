package com.jubby.application;

import java.time.LocalDate;
import java.time.LocalDateTime;
import jakarta.persistence.*;

@Entity
@Table(name = "job_applications")
public class JobApplication {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String company;
  private String position;
  private String location;
  private String salary;
  private String jobUrl;

  @Enumerated(EnumType.STRING)
  private ApplicationStatus status;
  
  private LocalDate applicationDate;
  private String notes;

  @Column(nullable = false, updatable = false)
  private LocalDateTime createdAt;

  @Column(nullable = false)
  private LocalDateTime updatedAt;

  public JobApplication() {

  }

  public JobApplication(
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

  @PrePersist
  public void onCreate() {
    LocalDateTime now = LocalDateTime.now();
    this.createdAt = now;
    this.updatedAt = now;
  }

  @PreUpdate
  public void onUpdate() {
    this.updatedAt = LocalDateTime.now();
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getCompany() {
    return company;
  }

  public void setCompany(String company) {
    this.company = company;
  }

  public String getPosition() {
    return position;
  }

  public void setPosition(String position) {
    this.position = position;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public String getSalary() {
    return salary;
  }

  public void setSalary(String salary) {
    this.salary = salary;
  }

  public String getJobUrl() {
    return jobUrl;
  }

  public void setJobUrl(String jobUrl) {
    this.jobUrl = jobUrl;
  }

  public ApplicationStatus getStatus() {
    return status;
  }

  public void setStatus(ApplicationStatus status) {
    this.status = status;
  }

  public LocalDate getApplicationDate() {
    return applicationDate;
  }

  public void setApplicationDate(LocalDate applicationDate) {
    this.applicationDate = applicationDate;
  }

  public String getNotes() {
    return notes;
  }

  public void setNotes(String notes) {
    this.notes = notes;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public LocalDateTime getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(LocalDateTime updatedAt) {
    this.updatedAt = updatedAt;
  }
}
