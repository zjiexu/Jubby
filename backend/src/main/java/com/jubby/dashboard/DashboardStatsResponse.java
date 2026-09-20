package com.jubby.dashboard;

public class DashboardStatsResponse {
  
  private long total;
  private long saved;
  private long applied;
  private long interview;
  private long offer;
  private long rejected;

  public DashboardStatsResponse(long total, long saved, long applied, long interview, long offer, long rejected) {
    this.total = total;
    this.saved = saved;
    this.applied = applied;
    this.interview = interview;
    this.offer = offer;
    this.rejected = rejected;
  }

  public long getTotal() {
    return total;
  }

  public long getSaved() {
    return saved;
  }

  public long getApplied() {
    return applied;
  }

  public long getInterview() {
    return interview;
  }

  public long getOffer() {
    return offer;
  }

  public long getRejected() {
    return rejected;
  }
}
