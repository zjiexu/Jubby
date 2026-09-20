package com.jubby.application;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class JobApplicationServiceTest {
  
  @Mock
  private JobApplicationRepository jobApplicationRepository;

  @InjectMocks
  private JobApplicationService jobApplicationService;

  @Test
  void createSavesJobApplicationFromRequest() {
    JobApplicationRequest request = new JobApplicationRequest();
    request.setCompany("Google");
    request.setPosition("Software Engineer");
    request.setLocation("Mountain View, CA");
    request.setSalary("$120,000 - $150,000");
    request.setJobUrl("https://careers.google.com");
    request.setStatus(ApplicationStatus.APPLIED);
    request.setApplicationDate(LocalDate.of(2026, 9, 20));
    request.setNotes("Applied online.");

    when(jobApplicationRepository.save(any(JobApplication.class)))
      .thenAnswer(invocation -> invocation.getArgument(0));

    JobApplication result = jobApplicationService.create(request);

    assertEquals("Google", result.getCompany());
    assertEquals("Software Engineer", result.getPosition());
    assertEquals(ApplicationStatus.APPLIED, result.getStatus());
    assertEquals(LocalDate.of(2026, 9, 20), result.getApplicationDate());

    verify(jobApplicationRepository).save(any(JobApplication.class));
  }

  @Test
  void updateReturnsUpdatedApplicationWhenFound() {
    JobApplication existingApplication = new JobApplication();
    existingApplication.setCompany("Google");
    existingApplication.setPosition("Software Engineer");
    existingApplication.setStatus(ApplicationStatus.APPLIED);

    JobApplicationRequest request = new JobApplicationRequest();
    request.setCompany("Google");
    request.setPosition("Backend Engineer");
    request.setLocation("Remote");
    request.setSalary("$130,000 - $160,000");
    request.setJobUrl("https://careers.google.com");
    request.setStatus(ApplicationStatus.INTERVIEW);
    request.setApplicationDate(LocalDate.of(2026, 9, 20));
    request.setNotes("Recruiter scheduled interview.");

    when(jobApplicationRepository.findById(1L)).thenReturn(Optional.of(existingApplication));
    when(jobApplicationRepository.save(existingApplication)).thenReturn(existingApplication);

    Optional<JobApplication> result = jobApplicationService.update(1L, request);

    assertTrue(result.isPresent());
    assertEquals("Backend Engineer", result.get().getPosition());
    assertEquals("Remote", result.get().getLocation());
    assertEquals(ApplicationStatus.INTERVIEW, result.get().getStatus());

    verify(jobApplicationRepository).findById(1L);
    verify(jobApplicationRepository).save(existingApplication);
  }

  @Test
  void deleteByIdReturnsFalseWhenApplicationDoesNotExist() {
    when(jobApplicationRepository.existsById(99L)).thenReturn(false);

    boolean deleted = jobApplicationService.deleteById(99L);

    assertFalse(deleted);
    verify(jobApplicationRepository).existsById(99L);
    verify(jobApplicationRepository, never()).deleteById(any());
  }
}
