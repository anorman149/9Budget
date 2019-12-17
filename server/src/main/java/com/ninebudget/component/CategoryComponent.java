package com.ninebudget.component;

import com.ninebudget.model.Category;
import com.ninebudget.model.Component;
import com.ninebudget.model.ComponentException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CategoryComponent implements Component<Category> {
    protected static final Logger log = LogManager.getLogger(CategoryComponent.class);

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
}
