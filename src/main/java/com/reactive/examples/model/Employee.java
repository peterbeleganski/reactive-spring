package com.reactive.examples.model;

import lombok.Data;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author Petar Beleganski
 */
@Document
@Data
public class Employee {

    @Id
    @UniqueElements
    private String id;

    private String name;

    private String department;
}
