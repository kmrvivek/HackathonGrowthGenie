package com.hackathon.growthgenie.model;

public enum AccountStatus {
  ACTIVE("Active"), CLOSED("Closed"), FROZEN("Frozen");

  String type;

  AccountStatus(String type) {
    this.type = type;
  }
}
