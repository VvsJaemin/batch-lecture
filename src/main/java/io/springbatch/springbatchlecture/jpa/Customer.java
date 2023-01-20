package io.springbatch.springbatchlecture.jpa;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class Customer {

    @Id
    @GeneratedValue
    private Long id;
    private String firstname;
    private String lastname;

    private String birthdate;
}
