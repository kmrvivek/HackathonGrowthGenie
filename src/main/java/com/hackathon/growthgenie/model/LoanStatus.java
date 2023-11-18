package com.hackathon.growthgenie.model;

public enum LoanStatus {

  Approved("Approved"), Rejected("Rejected"), Pending("Pending");


  private String status;

  LoanStatus(String status) {
    this.status = status;
  }

  public String getStatus() {
    return status;
  }

}
