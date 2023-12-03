
# HackathonGrowthGenie Backend Application

Aims to enhance the financial well-being of account holders. The application is be accessible through both mobile and web interfaces, catering to a diverse user base.
This Repository contains the backend application of the wealth management app covering both theme 1 and theme 2.
## Technology Stack
- Java 8
- Spring Boot 2.7.17
- MySql 5

## Theme 1 EndPoints
- Loan EndPoints
```
GET : /api/loans/{customerId}
```
```
GET : /api/loans/{loanId}
```
```
GET : /api/loans/{loanType}
```
```
GET : /api/loans/{loanStatus}
```
- Account EndPoints
```
GET : /api/accounts/customer/{customerId}
```
```
PUT : /api/accounts
```
```
PUT : /api/accounts/{accountId}
```
```
DELETE : /api/accounts/{accountId}
```

## Theme 2 EndPoints
- Identify Popular Investments
```
GET : /api/investmentAccount/popularInvestments
```
- Top Performing Investments
```
GET : /api/investmentAccount/roi/topinvestments/{type}/{n}
type: [Stocks, MutualFunds, FixedDeposits, All]
```
- Identify High-Net worth Investors
```
GET : /api/investmentAccount/roi/topinvestors/{pageSize}
```
- Historical Returns
```
GET : /api/investmentAccount/historical/{customerId}
```
- Tax Assessment
```
GET : /api/investmentAccount/taxCalculation/{customerId}
```
## Environment Setup :
- IDE : [Intellij](https://www.jetbrains.com/idea/download/download-thanks.html?platform=windows&code=IIC)
- Java Development Kit : [jdk 1.8](https://www.oracle.com/webapps/redirect/signon?nexturl=https://download.oracle.com/otn/java/jdk/8u202-b08/1961070e4c9b4e26a04e7f5a083f551e/jdk-8u202-windows-x64.exe)
- Database : [MySql5](https://dev.mysql.com/downloads/mysql/5.7.html)
