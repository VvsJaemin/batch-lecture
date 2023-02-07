package io.springbatch.springbatchlecture.jdbc;

import lombok.Data;

@Data
public class Customer {
    private Long id;
    private String firstName;
    private String lastName;

    private String birthdate;

    public Customer(Long id, String firstName, String lastName, String birthdate) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdate = birthdate;
    }
}
