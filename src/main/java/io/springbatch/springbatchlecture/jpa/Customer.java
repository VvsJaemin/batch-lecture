package io.springbatch.springbatchlecture.jpa;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Getter
@Setter
@Entity
public class Customer {

    @Id
    @GeneratedValue
    private Long id;
    private String username;
    private int age;

    @OneToOne(mappedBy = "customer")
    private Address address;

}
