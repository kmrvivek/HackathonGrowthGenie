package com.hackathon.growthgenie.model;

public enum LoanType {

  Business("Business Loan"), Credit("Credit Card Loan"), Auto("Auto Loan"), Student(
      "Student Loan"), Personal("Personal Loan"), Home("Home Loan");

  private String type;

  LoanType(String type) {
    this.type = type;
  }

  public String getType() {
    return type;
  }

}
