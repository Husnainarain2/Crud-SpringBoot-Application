package com.Practices.Crud_SpringBoot_Application.ExceptionHandler;

public class DuplicatedResouceException extends RuntimeException{
    public DuplicatedResouceException(String message)
    {
        super(message);
    }
}
