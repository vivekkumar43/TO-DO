package org.example.todo.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ToDoCreate {

    private Long touser_id;
    private String title;
    private String description;

}
