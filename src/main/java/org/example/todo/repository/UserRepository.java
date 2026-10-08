package org.example.todo.repository;

import org.example.todo.entity.ToUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<ToUser, Long> {

}
