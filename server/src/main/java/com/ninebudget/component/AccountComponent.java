package com.ninebudget.component;

import com.ninebudget.dao.AccountDAO;
import com.ninebudget.model.Account;
import com.ninebudget.model.Component;
import com.ninebudget.model.ComponentException;
import com.ninebudget.model.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

public class AccountComponent implements Component<Account> {
    protected static final Logger log = LogManager.getLogger(AccountComponent.class);

    @Autowired
    private AccountDAO accountDAO;

    @Override
    public Account get(Account account) throws ComponentException {
        try {
            return accountDAO.get(account);
        } catch (DAOException e) {
            log.error(e);
        }

        return null;
    }

    @Override
    public void update(Account account) throws ComponentException {

    }

    @Override
    public void create(Account account) throws ComponentException {

    }
}
