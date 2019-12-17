package com.ninebudget.controller;

import com.ninebudget.component.AccountComponent;
import com.ninebudget.component.BudgetComponent;
import com.ninebudget.model.Account;
import com.ninebudget.model.AccountOperations;
import com.ninebudget.model.Budget;
import com.ninebudget.model.ComponentException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController implements AccountOperations {
    private static final Logger log = LogManager.getLogger(AccountController.class);

    @Autowired
    private AccountComponent accountComponent;

    @Override
    public List<Account> getAll() {
        log.info("Retrieving Account: " + 2);
        List<Account> accounts = new ArrayList<>();

        try {
            accounts.add(accountComponent.get(new Account(2)));
        } catch (ComponentException e) {
            log.error(e);
        }

        return accounts;
    }

    @Override
    public Account get(int id) {
        log.info("Retrieving Account: " + id);

        try {
            return accountComponent.get(new Account(id));
        } catch (ComponentException e) {
            log.error(e);
        }

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
