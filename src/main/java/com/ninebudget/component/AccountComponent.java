package com.ninebudget.component;

import com.ninebudget.model.Account;
import com.ninebudget.model.Component;
import com.ninebudget.model.ComponentException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AccountComponent implements Component<Account> {
    protected static final Logger log = LogManager.getLogger(AccountComponent.class);

    @Override
    public Account get(Account object) throws ComponentException {
        return null;
    }

    @Override
    public void update(Account object) throws ComponentException {

    }

    @Override
    public void create(Account object) throws ComponentException {

    }
}
