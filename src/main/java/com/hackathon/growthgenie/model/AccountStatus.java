package com.hackathon.growthgenie.model;

public enum AccountStatus {
  ACTIVE(0, "Active"), CLOSED(1, "Closed"), FROZEN(2, "Frozen");

  int id;
  String type;

  AccountStatus(int id, String type) {
    this.id = id;
    this.type = type;
  }
}
