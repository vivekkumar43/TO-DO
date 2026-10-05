package org.example.todo.repository;

import org.example.todo.dto.AllMatching;
import org.example.todo.entity.ToDo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ToDoRepository extends JpaRepository<ToDo, Long> {

    @Query("select u from ToDo u where u.description LIKE %?1%")
    List<ToDo> searchOnDescription(String searchString);


    @Query("select p from ToDo p where CONCAT(p.description,p.title) ilike %?1%")
    List<ToDo>searchallByKeyword(String keyword);
}
