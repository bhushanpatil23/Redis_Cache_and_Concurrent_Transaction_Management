package com.bhushan.caching.cachingApp.services;

import com.bhushan.caching.cachingApp.entities.Employee;
import com.bhushan.caching.cachingApp.entities.SalaryAccount;

public interface SalaryAccountService {
    void createAccount(Employee employee);

    SalaryAccount incrementBalance(Long accountId);
}
