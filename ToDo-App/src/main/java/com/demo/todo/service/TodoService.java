package com.demo.todo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;

import com.demo.todo.model.Todo;
import com.demo.todo.repo.ITodoRepo;

@Service
public class TodoService {
	
	@Autowired
	ITodoRepo repo;

	public List<Todo> getAllTodoItems(){
		ArrayList<Todo> todolist=new ArrayList<>();
		repo.findAll().forEach(Todo->todolist.add(Todo));
		return todolist;
		
	}
	
	public Todo getTodoItemById(Long id){
		return repo.findById(id).get();
	}
	
	public boolean updateStatus(Long id){
		Todo todo=getTodoItemById(id);
		todo.setStatus("Completed");
		return saveOrUpdateTodoItem(todo);
	}
	
	public boolean saveOrUpdateTodoItem(Todo todo){
		Todo updatedObj=repo.save(todo);
		if(getTodoItemById(updatedObj.getId())!=null) {
			return true;
		}
		return false;
	}
	
	public boolean deleteTodoItem(Long id){
		repo.deleteById(id);
		if(repo.findById(id).isEmpty()) {
			return true;
		}
		return false;
	}
	
}
