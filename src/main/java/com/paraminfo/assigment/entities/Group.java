package com.paraminfo.assigment.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "groups")
public @Data class Group {
    @Id
    @SequenceGenerator(name = "group_id_sequence", sequenceName = "group_id_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Group name is required")
    @Column(nullable = false, unique = true)
    private String name;

    private String description;

    @ManyToMany(mappedBy = "groups")
    private Set<User> users = new HashSet<>();
}