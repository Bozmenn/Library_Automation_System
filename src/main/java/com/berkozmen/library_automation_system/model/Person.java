package com.berkozmen.library_automation_system.model;

import lombok.Data;

import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;

@Data
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Person {

    private String name;
    private String surname;
    private String email;
}
