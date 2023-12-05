# GlobeWallet
## Overview:
a Cross-Currency Banking System which provides functions like foreign exchange and money transfer

## Sub-Domains:
### 1. User Authentication
- **Objective:** Securely manage user registrations, logins, and sessions.
- **Features:**
    - Registration (Contact Information)
    - Login and Logout
    - Bank Account Setup
    - accountID Generation
    - Welcome email

### 2. Money Transfer
- **Objective:** Transfer money to another account domestically or globally
- **Features:**
    - API Integration: currency will be automatically converted according to the currency of the accounts
    - Transfer money using their accountID
    - Transaction email notification
      
### 3. Foreign Exchange
- **Objective:** There is a page stating the real-time foreign currencies helping the users track the currency rate
- **Features:**
    - real-time currency rate
    - API Integration: the user can enter the currency they want to exchange

### 4. Security
- **Objective:** Protects user accounts' safety and helps users manage their transactions and their accounts
- **Features:**
    - Transaction securityCode
    - Password validation

### 5. Account Balance
- **Objective:** Help users keep track of their account balance and manage the transactions
- **Features:**
  - View Balance
  - Update Balance: the balance will be updated when there is a new transaction

### 6. Notification
- **Objective:** Notifies users when there is a transaction or update about their account
- **Features:**
  - Email Notification: Welcome email, Transaction email, UpdateInformation email

## Link to API
(https://polygon.io/docs/forex/get_v1_conversion__from___to)
(https://javaee.github.io/javamail/#API_Documentation)

## Sample Usage 
<img width="1412" alt="Screenshot 2023-10-19 at 17 22 38" src="https://github.com/jameschenyung/csc207project/assets/78540611/eb95fa00-185e-47f7-8b97-a48fe0c467f1">
<img width="500" alt="Screenshot 2023-12-05 at 10 25 54" src="https://github.com/jameschenyung/GlobeWallet/assets/46597830/7421d7fa-b56c-4c2e-a34c-94d9e55ea798">
<img width="500" alt="Screenshot 2023-12-05 at 10 25 43" src="https://github.com/jameschenyung/GlobeWallet/assets/46597830/67737c8a-0b01-4d66-bc88-f94f080a1cc2">
<img width="500" alt="Screenshot 2023-12-05 at 10 25 31" src="https://github.com/jameschenyung/GlobeWallet/assets/46597830/4db1bb64-8894-4b3b-9910-573fc8e02dd0">
<img width="500" alt="Screenshot 2023-12-05 at 10 25 24" src="https://github.com/jameschenyung/GlobeWallet/assets/46597830/a24354c7-b155-43ac-847a-3f20355b76da">


## Example output of our java code
```
200
{"converted":72.88,"from":"CAD","initialAmount":100,"last":{"ask":0.728852349091121,"bid":0.728804541909905,"exchange":48,"timestamp":1697748283000},"request_id":"25e6c4982df1d1f9ffd0aa0d1c821a7e","status":"success","symbol":"CAD/USD","to":"USD"}
```
