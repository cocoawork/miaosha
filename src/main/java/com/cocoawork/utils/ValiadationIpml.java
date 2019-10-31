package com.cocoawork.utils;


import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;


@Component
public class ValiadationIpml implements InitializingBean {

    private Validator validator;

    @Override
    public void afterPropertiesSet() throws Exception {
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
    }


    public ValiadationResult validateBean(Object bean) {
        ValiadationResult result = new ValiadationResult();
        Set<ConstraintViolation<Object>> set = validator.validate(bean);
        if (set.size() > 0) {
            for (ConstraintViolation<Object> constraintViolation : set) {
                result.setHasErrors(true);
                String errorMsg = constraintViolation.getMessage();
                String name = constraintViolation.getPropertyPath().toString();
                result.getErrors().put(name, errorMsg);
            }
        }
        return result;

    }


}
