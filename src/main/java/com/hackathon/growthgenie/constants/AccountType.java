package com.hackathon.growthgenie.constants;

public enum AccountType {
    BROKERAGE_ACCOUNT(0, "Brokerage Account"),
    CERTIFICATE_OF_DEPOSIT(1, "Certificate Of Deposit (CD)"),
    CHECKING_ACCOUNT(2, "Checking Account"),
    INDIVIDUAL_RETIERMENT_ARRAGEMENT(3, "Individual Retirement Arragement (IRA)"),
    MONEY_MARKET_ACCOUNT(4, "Money Market Account"),
    SAVINGS_ACCOUNT(5, "Savings Account");

    int id;
    String type;

    AccountType(int id, String type) {
        this.id = id;
        this.type = type;
    }
}
