package com.test.api.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Table(name = "users")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "u_id", nullable = false)
    private Long id;

    @Column(name = "u_first_name", nullable = false)
    private String firstName;

    @Column(name = "u_last_name", nullable = false)
    private String lastName;
}
