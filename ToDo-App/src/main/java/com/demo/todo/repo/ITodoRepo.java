package com.demo.todo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.todo.model.Todo;

@Repository
public interface ITodoRepo extends JpaRepository<Todo,Long>{

}
