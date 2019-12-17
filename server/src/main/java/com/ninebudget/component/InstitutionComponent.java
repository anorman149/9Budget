package com.ninebudget.component;

import com.ninebudget.model.Component;
import com.ninebudget.model.ComponentException;
import com.ninebudget.model.DAO;
import com.ninebudget.model.Institution;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class InstitutionComponent implements Component<Institution> {
    protected static final Logger log = LogManager.getLogger(InstitutionComponent.class);

    @Override
    public Institution get(Institution object) throws ComponentException {
        return null;
    }

    @Override
    public void update(Institution object) throws ComponentException {

    }

    @Override
    public void create(Institution object) throws ComponentException {

    }
}
