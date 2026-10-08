package org.example.todo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table
public class ToUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String email;
    private String password;


    @OneToMany(mappedBy = "touser", cascade = {CascadeType.REMOVE}, fetch = FetchType.EAGER)
    @ToString.Exclude
    private List<ToDo> toDos = new ArrayList<>();



}
