package com.jubby.dashboard;

import com.jubby.application.ApplicationStatus;
import com.jubby.application.JobApplicationRepository;
import org.springframework.stereotype.Service;

@Service
public class DashboardService {
  
  private final JobApplicationRepository jobApplicationRepository;

  public DashboardService(JobApplicationRepository jobApplicationRepository) {
    this.jobApplicationRepository = jobApplicationRepository;
  }

  public DashboardStatsResponse getStats() {
    return new DashboardStatsResponse(
      jobApplicationRepository.count(),
      jobApplicationRepository.countByStatus(ApplicationStatus.SAVED),
      jobApplicationRepository.countByStatus(ApplicationStatus.APPLIED),
      jobApplicationRepository.countByStatus(ApplicationStatus.INTERVIEW),
      jobApplicationRepository.countByStatus(ApplicationStatus.OFFER),
      jobApplicationRepository.countByStatus(ApplicationStatus.REJECTED)
    );
  }
}
