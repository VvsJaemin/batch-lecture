package io.springbatch.springbatchlecture.jpa;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Getter
@Setter
@Entity
public class Address {

    @Id
    @GeneratedValue
    private Long id;
    private String location;

    @OneToOne
    @JoinColumn(name = "custom_id")
    private Customer customer;
}
