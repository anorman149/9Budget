package com.ninebudget.controller;

import com.ninebudget.model.Institution;
import com.ninebudget.model.InstitutionOperations;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/institutions")
public class InstitutionController implements InstitutionOperations {
    private static final Logger log = LogManager.getLogger(InstitutionController.class);

    @Override
    public List<Institution> getAll() {
        return null;
    }

    @Override
    public Institution get(int id) {
        return null;
    }

    @Override
    public void update(Institution institution, int id) {

    }

    @Override
    public void create(Institution institution, int id) {

    }

    @Override
    public void delete(int id) {

    }
}
