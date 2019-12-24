package com.ninebudget.controller;

import com.ninebudget.component.AccountComponent;
import com.ninebudget.model.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@V1APIController
public class AccountController implements AccountOperations {
    private static final Logger log = LogManager.getLogger(AccountController.class);

    @Autowired
    private AccountComponent accountComponent;

    @Override
    public List<Account> getAll() throws ServiceException {
        log.info("Retrieving Account: " + 2);
        List<Account> accounts = new ArrayList<>();

        try {
            accounts.add(accountComponent.get(new Account(2)));
        } catch (ComponentException e) {
            log.error(e);//TODO add more
        }

        return accounts;
    }

    @Override
    public Account get(int id) throws ServiceException {
        log.info("Retrieving Account: " + id);

        try {
            return accountComponent.get(new Account(id));
        } catch (ComponentException e) {
            log.error(e);//TODO add more
        }

        return null;
    }

    @Override
    public void update(Account account) throws ServiceException {

    }

    @Override
    public void create(Account account) throws ServiceException {

    }

    @Override
    public void delete(int id) throws ServiceException {

    }
}
