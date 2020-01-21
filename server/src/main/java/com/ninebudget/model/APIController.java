package com.ninebudget.model;

import org.springframework.core.annotation.AliasFor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@RestController
@RequestMapping(value = "/api/v1")
public @interface APIController {
    @AliasFor(annotation = RestController.class)
    String value() default "";
}
