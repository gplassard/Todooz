package fr.todooz.web.controller;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.todooz.domain.Task;
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

	@RequestMapping("/search")
	public String search(String query, Model model) {
		model.addAttribute("tasks", taskService.findByQuery(query));
		return "index";
	}

	@RequestMapping("/tag/{tag}")
	public String tag(@PathVariable String tag, Model model) {
		model.addAttribute("tasks", taskService.findByTag(tag));
		return "index";
	}

	@PostConstruct
	public void bootstrap() {
		if (taskService.count() == 0) {
			Task tache1 = new Task();
			tache1.setDate(new Date());
			tache1.setId(1L);
			tache1.setTags("bientot,manger");
			tache1.setText("On a faim!!!!");
			tache1.setTitle("toto");
			taskService.save(tache1);

			Task tache2 = new Task();
			tache2.setDate(new Date());
			tache2.setId(2L);
			tache2.setTags("lorem ipsum");
			tache2.setText("pfiouuuuuu");
			tache2.setTitle("tutu");
			taskService.save(tache2);

			Task tache3 = new Task();
			tache3.setDate(new Date());
			tache3.setId(3L);
			tache3.setTags("Java c'est bien!");
			tache3.setText("youhou");
			tache3.setTitle("ohohoho");
			taskService.save(tache3);
		}
	}
}