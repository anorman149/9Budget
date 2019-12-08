package com.ninebudget.controller;

import com.ninebudget.model.Account;
import com.ninebudget.model.AccountOperations;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController implements AccountOperations {
    private static final Logger log = LogManager.getLogger(AccountController.class);

    @Override
    public List<Account> getAll() {
        return null;
    }

    @Override
    public Account get(int id) {
        return null;
    }

    @Override
    public void update(Account account, int id) {

    }

    @Override
    public void create(Account account, int id) {

    }

    @Override
    public void delete(int id) {

    }
}
