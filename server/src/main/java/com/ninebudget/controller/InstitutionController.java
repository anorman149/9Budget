package com.ninebudget.controller;

import com.ninebudget.model.Institution;
import com.ninebudget.model.InstitutionOperations;
import com.ninebudget.model.ServiceException;
import com.ninebudget.model.V1APIController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

@V1APIController
public class InstitutionController implements InstitutionOperations {
    private static final Logger log = LogManager.getLogger(InstitutionController.class);

    @Override
    public List<Institution> getAll() throws ServiceException {
        return null;
    }

    @Override
    public Institution get(int id) throws ServiceException {
        return null;
    }

    @Override
    public void update(Institution institution, int id) throws ServiceException {

    }

    @Override
    public void create(Institution institution, int id) throws ServiceException {

    }

    @Override
    public void delete(int id) throws ServiceException {

    }
}
