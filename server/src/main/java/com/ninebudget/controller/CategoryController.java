package com.ninebudget.controller;

import com.ninebudget.model.Category;
import com.ninebudget.model.CategoryOperations;
import com.ninebudget.model.ServiceException;
import com.ninebudget.model.V1APIController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.validation.Valid;
import java.util.List;

@V1APIController
public class CategoryController implements CategoryOperations {
    private static final Logger log = LogManager.getLogger(CategoryController.class);

    @Override
    public List<Category> getAll() throws ServiceException {
        return null;
    }

    @Override
    public Category get(int id) throws ServiceException {
        return null;
    }

    @Override
    public void update(@Valid Category category, int id) throws ServiceException {

    }

    @Override
    public void create(@Valid Category category, int id) throws ServiceException {

    }

    @Override
    public void delete(int id) throws ServiceException {

    }
}
