package fr.todooz.web.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.todooz.service.TaskService;

@Controller
public class IndexController {
	  @Inject
	  private TaskService taskService;

	  @RequestMapping({ "/", "/index" })
	  public String index(Model model) {
	    model.addAttribute("tasks", taskService.findAll());

	    return "index";
	  }
}