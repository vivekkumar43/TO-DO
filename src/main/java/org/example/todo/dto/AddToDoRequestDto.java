package org.example.todo.dto;

import lombok.Data;

@Data
public class AddToDoRequestDto {

    private String title;
    private String description;
    private boolean complete;
}
