package com.hackathon.growthgenie.model;

public enum AccountType {
  BROKERAGE_ACCOUNT("Brokerage Account"), CERTIFICATE_OF_DEPOSIT(
      "Certificate Of Deposit (CD)"), CHECKING_ACCOUNT(
          "Checking Account"), INDIVIDUAL_RETIERMENT_ARRAGEMENT(
              "Individual Retirement Arragement (IRA)"), MONEY_MARKET_ACCOUNT(
                  "Money Market Account"), SAVINGS_ACCOUNT("Savings Account");


  String type;

  AccountType(String type) {
    this.type = type;
  }
}
