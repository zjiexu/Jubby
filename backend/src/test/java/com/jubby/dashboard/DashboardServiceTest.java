package com.jubby.dashboard;

import com.jubby.application.ApplicationStatus;
import com.jubby.application.JobApplicationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DashboardServiceTest {
  
  @Mock
  private JobApplicationRepository jobApplicationRepository;

  @InjectMocks
  private DashboardService dashboardService;

  @Test
  void getStatsReturnsApplicationCountsByStatus() {
    when(jobApplicationRepository.count()).thenReturn(10L);
    when(jobApplicationRepository.countByStatus(ApplicationStatus.SAVED)).thenReturn(2L);
    when(jobApplicationRepository.countByStatus(ApplicationStatus.APPLIED)).thenReturn(4L);when(jobApplicationRepository.countByStatus(ApplicationStatus.INTERVIEW)).thenReturn(2L);
    when(jobApplicationRepository.countByStatus(ApplicationStatus.OFFER)).thenReturn(1L);
    when(jobApplicationRepository.countByStatus(ApplicationStatus.REJECTED)).thenReturn(1L);

    DashboardStatsResponse stats = dashboardService.getStats();

    assertEquals(10L, stats.getTotal());
    assertEquals(2L, stats.getSaved());
    assertEquals(4L, stats.getApplied());
    assertEquals(2L, stats.getInterview());
    assertEquals(1L, stats.getOffer());
    assertEquals(1L, stats.getRejected());
  }
}
