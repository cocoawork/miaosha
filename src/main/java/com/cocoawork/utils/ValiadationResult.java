package com.cocoawork.utils;

import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class ValiadationResult {

    private Boolean hasErrors = false;

    private Map<String, String>errors = new HashMap<>();

    public Boolean getHasErrors() {
        return hasErrors;
    }

    public void setHasErrors(Boolean hasErrors) {
        this.hasErrors = hasErrors;
    }


    public void setErrors(Map<String, String> errors) {
        this.errors = errors;
    }


    public Map<String, String> getErrors() {
        return errors;
    }


    public String getErrorsString() {

        Object[] es = errors.values().toArray();
        StringBuilder sb = new StringBuilder();
        for (Object error: es) {
            String s = (String)error.toString();
            sb.append(s);
            sb.append(",");
        }
        return sb.toString();
    }
}
