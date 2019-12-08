package com.ninebudget.controller;

import com.ninebudget.model.Category;
import com.ninebudget.model.CategoryOperations;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController implements CategoryOperations {
    private static final Logger log = LogManager.getLogger(CategoryController.class);

    @Override
    public List<Category> getAll() {
        return null;
    }

    @Override
    public Category get(int id) {
        return null;
    }

    @Override
    public void update(Category category, int id) {

    }

    @Override
    public void create(Category category, int id) {

    }

    @Override
    public void delete(int id) {

    }
}
