package com.softuni.restControllers;

public class Error {

    private String errorName;
    private String description;

    public String getErrorName() {
        return errorName;
    }

    public Error setErrorName(String errorName) {
        this.errorName = errorName;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Error setDescription(String description) {
        this.description = description;
        return this;
    }
}
