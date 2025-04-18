package com.example.service;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.repository.AccountRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;

@Service
public class AccountService {

    AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account addAccount(Account account) throws Exception {
        if (!(account.getUsername().isBlank())
        && account.getPassword().length() >= 4
        && accountRepository.findByUsername(account.getUsername()) == null) {
            return accountRepository.save(account);
        } else if (accountRepository.findByUsername(account.getUsername()) != null) {
            throw new Exception("Username already exists");
        }
        return null;
    }

    public Account login(Account account) {
        if (accountRepository.verifyAccount(account.getUsername(), account.getPassword()) != null) {
            return accountRepository.verifyAccount(account.getUsername(), account.getPassword());
        }
        return null;
    }
}
