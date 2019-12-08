package com.ninebudget.component;

import com.ninebudget.model.Component;
import com.ninebudget.model.ComponentException;
import com.ninebudget.model.Institution;

public class InstitutionComponent implements Component<Institution> {
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

    @Override
    public void deactivate(Institution object) throws ComponentException {

    }
}
