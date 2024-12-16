package com.demo.todo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.demo.todo.model.Todo;
import com.demo.todo.service.TodoService;

@Controller
public class TodoController {

	@Autowired
	TodoService service;
	
	@GetMapping({"/","viewTodoList"})
	public String viewAllTodoItems(Model model, @ModelAttribute("message") String message) {
		model.addAttribute("list", service.getAllTodoItems());
		model.addAttribute("msg", message);
		return "ViewTodoList";
	}
	
	@GetMapping("/updateTodoStatus/{id}")
	public String updateTodoStatus(@PathVariable Long id,RedirectAttributes redirectAttributes) {
		if(service.updateStatus(id)) {
			redirectAttributes.addFlashAttribute("message", "Update Success");
			return "redirect:/viewTodoList";
		}
		redirectAttributes.addFlashAttribute("message", "Update Failure");
		return "redirect:/viewTodoList";
	}
	
	@GetMapping("/addTodoItem")
	public String addTodoItem(Model model) {
		model.addAttribute("todo", new Todo());
		return "AddTodoItem";
	}
	
	@PostMapping("/saveTodoItem")
	public String saveTodoItem(Todo todo,RedirectAttributes redirectAttributes) {
		if(service.saveOrUpdateTodoItem(todo)) {
			redirectAttributes.addFlashAttribute("message", "Save Success");
			return "redirect:/viewTodoList";
		}
		redirectAttributes.addFlashAttribute("message", "Save Failure");
		return "redirect:/addTodoItem";
	}
	
	@GetMapping("/editTodoItem/{id}")
	public String editTodoItem(@PathVariable Long id,Model model) {
		model.addAttribute("todo", service.getTodoItemById(id));
		return "EditTodoItem";
	}
	
	@PostMapping("editSaveTodoItem")
	public String editSaveTodoItem(Todo todo, RedirectAttributes redirectAttributes) {
		if(service.saveOrUpdateTodoItem(todo)) {
			redirectAttributes.addFlashAttribute("message", "Edit Success");
			return "redirect:/viewTodoList";
		}
		redirectAttributes.addFlashAttribute("message", "Edit Failure");
		return "redirect:/editTodoItem/"+todo.getId();
	}
	
	@GetMapping("deleteTodoItem/{id}")
	public String deleteTodoItem(@PathVariable Long id, RedirectAttributes redirectAttributes) {
		if(service.deleteTodoItem(id)) {
			redirectAttributes.addFlashAttribute("message", "Delete Success");
			return "redirect:/viewTodoList";
		}
		redirectAttributes.addFlashAttribute("message", "Delete Failure");
		return "redirect:/viewTodoList";
	}
}
