package com.hackathon.growthgenie.model;

public enum LoanStatus {

  Approved(0, "Approved"), Rejected(1, "Rejected"), Pending(2, "Pending");

  private int id;
  private String status;

  LoanStatus(int id, String status) {
    this.id = id;
    this.status = status;
  }

  public String getStatus() {
    return status;
  }

  public int getId() {
    return id;
  }

}
