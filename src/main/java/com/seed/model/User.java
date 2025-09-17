package com.seed.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.seed.dto.RestaurantDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String fullName;

    private String email;

    private String password;

    private USER_ROLE role;

    // why json ignore because whenever I fetch the user that time I don't need this list of order
    // for fetching all the users I'll write separate api
    // Suppose you have a class with sensitive or unnecessary data you don't want exposed in JSON output:
    //Suppose you have a class with sensitive or unnecessary data you don't want exposed in JSON output: Hiding sensitive fields like passwords, tokens, internal IDs ,,,Preventing unnecessary data in API responses

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
    private List<Order> orders = new ArrayList<>();

    @ElementCollection   // @ElementCollection in JPA is only supported for basic types or embeddable types, such as String, Integer, or classes annotated with @Embeddable.
    private List<RestaurantDto>favorites=new ArrayList<>();

    // Cascading - This ensures that creating, updating, and deleting child entities happen automatically when saving the parent.
    // when we want to delete user and its other information also deleted through cascade = CascadeType.ALL
    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Address> addresses=new ArrayList<>();


}
