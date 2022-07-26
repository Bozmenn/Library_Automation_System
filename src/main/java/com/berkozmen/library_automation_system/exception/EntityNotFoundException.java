package com.berkozmen.library_automation_system.exception;

public class EntityNotFoundException extends RuntimeException{
    public EntityNotFoundException(String entityName) {
        super("Related " + entityName + " not found!");
    }
}
