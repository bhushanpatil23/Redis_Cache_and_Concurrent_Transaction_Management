package com.bhushan.caching.cachingApp.services.impl;

import com.bhushan.caching.cachingApp.entities.Employee;
import com.bhushan.caching.cachingApp.entities.SalaryAccount;
import com.bhushan.caching.cachingApp.exceptions.ResourceNotFoundException;
import com.bhushan.caching.cachingApp.repositories.SalaryAccountRepository;
import com.bhushan.caching.cachingApp.services.SalaryAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.REQUIRED)
public class SalaryAccountServiceImpl implements SalaryAccountService {

    private final SalaryAccountRepository salaryAccountRepository;

    @Override
    public void createAccount(Employee employee) {


        SalaryAccount salaryAccount = SalaryAccount.builder()
                .employee(employee)
                .balance(BigDecimal.ZERO)
                .build();

        salaryAccountRepository.save(salaryAccount);

    }

    @Override
    @Transactional
    public SalaryAccount incrementBalance(Long accountId) {
        SalaryAccount salaryAccount = salaryAccountRepository.findById(accountId)
                .orElseThrow(()-> new RuntimeException("Account not found with ID "+accountId));

        BigDecimal prevBalance = salaryAccount.getBalance();
        BigDecimal newBalance = prevBalance.add(BigDecimal.valueOf(1L));

        salaryAccount.setBalance(newBalance);
        return salaryAccountRepository.save(salaryAccount);
    }
}