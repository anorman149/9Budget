package com.ninebudget.controller;

import com.ninebudget.dao.AccountDAO;
import com.ninebudget.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class AuthController {
    @Autowired
    private UserDAO userDAO;

    @Autowired
    private AccountDAO accountDAO;


}
