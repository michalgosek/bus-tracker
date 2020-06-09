package com.example.bustracker.persistence.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "roles")
public class ApplicationRoles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long id;
    private final String name;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    public ApplicationRoles(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
