package com.ninebudget.component;

import com.ninebudget.model.Category;
import com.ninebudget.model.Component;
import com.ninebudget.model.ComponentException;

public class CategoryComponent implements Component<Category> {
    @Override
    public Category get(Category object) throws ComponentException {
        return null;
    }

    @Override
    public void update(Category object) throws ComponentException {

    }

    @Override
    public void create(Category object) throws ComponentException {

    }

    @Override
    public void deactivate(Category object) throws ComponentException {

    }
}
