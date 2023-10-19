# tbd
## Overview:
a Cross-Currency Banking System which provides functions like foreign exchange and money transfer

## Sub-Domains:
### 1. User Authentication
- **Objective:** Securely manage user registrations, logins, and sessions.
- **Features:**
    - Registration (Contact Information)
    - Login and Logout
    - Bank Account Setup and Verification
    - accountID Generation

### 2. Money Transfer
- **Objective:** Transfer money to another account domestically or globally
- **Features:**
    - API Integration: currency will be automatically converted according to the currency of the accounts
    - Transfer money using their accountID
      
### 3. Foreign Exchange
- **Objective:** There is a page stating the real-time foreign currencies helping the users track the currency rate
- **Features:**
    - real-time currency rate
    - API Integration: the user can enter the currency they want to exchange

### 4. Security
- **Objective:** Protects user accounts' safety and helps users manage their transactions and their accounts
- **Features:**
    - Access Code Set-up

### 5. Account Balance
- **Objective:** Help users keep track of their account balance and manage the transactions
- **Features:**
  - View Balance
  - Update Balance: the balance will be updated when there is a new transaction

### 6. Notification
- **Objective:** Notifies users when there is a transaction
- **Features:**
  - Email Notification: the user can receive an email when there is a new transaction to their account or from their account 

## Link to API
(https://polygon.io/docs/forex/get_v1_conversion__from___to)

## Sample Usage 
<img width="1412" alt="Screenshot 2023-10-19 at 17 22 38" src="https://github.com/jameschenyung/csc207project/assets/78540611/eb95fa00-185e-47f7-8b97-a48fe0c467f1">

## Example output of our java code
```
200
{"converted":72.88,"from":"CAD","initialAmount":100,"last":{"ask":0.728852349091121,"bid":0.728804541909905,"exchange":48,"timestamp":1697748283000},"request_id":"25e6c4982df1d1f9ffd0aa0d1c821a7e","status":"success","symbol":"CAD/USD","to":"USD"}
```
