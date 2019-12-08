package com.ninebudget.component;

import com.ninebudget.model.Account;
import com.ninebudget.model.Component;
import com.ninebudget.model.ComponentException;

public class AccountComponent implements Component<Account> {
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

    @Override
    public void deactivate(Account object) throws ComponentException {

    }
}
