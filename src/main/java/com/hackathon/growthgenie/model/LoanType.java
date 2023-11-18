package com.hackathon.growthgenie.model;

public enum LoanType {

  Business(0, "Business Loan"), Credit(1, "Credit Card Loan"), Auto(2, "Auto Loan"), Student(3,
      "Student Loan"), Personal(4, "Personal Loan"), Home(5, "Home Loan");

  private int id;
  private String type;

  LoanType(int id, String type) {
    this.id = id;
    this.type = type;
  }

  public String getType() {
    return type;
  }

  public int getId() {
    return id;
  }

}
