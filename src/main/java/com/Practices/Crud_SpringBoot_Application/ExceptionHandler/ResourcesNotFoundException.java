package com.Practices.Crud_SpringBoot_Application.ExceptionHandler;

public class ResourcesNotFoundException extends RuntimeException {
    public ResourcesNotFoundException(String message) {
        super(message);
    }

}
