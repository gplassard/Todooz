package fr.todooz.web.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.joda.time.DateMidnight;
import org.joda.time.Interval;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fr.todooz.domain.Task;
import fr.todooz.service.TagCloudService;
import fr.todooz.service.TaskService;

@Controller
public class IndexController {
	@Inject
	private TaskService taskService;
	@Inject
	private TagCloudService tagCloudService;

	@RequestMapping({ "/", "/index" })
	public String index(Model model) {
		model.addAttribute("tasks", taskService.findAll());
		addTags(model);
		return "index";
	}

	@RequestMapping("/search")
	public String search(String query, Model model) {
		model.addAttribute("tasks", taskService.findByQuery(query));
		addTags(model);
		return "index";
	}

	@RequestMapping("/tag/{tag}")
	public String tag(@PathVariable String tag, Model model) {
		model.addAttribute("tasks", taskService.findByTag(tag));
		addTags(model);
		return "index";
	}
	
	@RequestMapping("/today")
	public String today(Model model){
		model.addAttribute("tasks",taskService.findByInterval(todayInterval()));
		addTags(model);
		return "index";
	}
	
	@RequestMapping("/tomorrow")
	public String tomorrow(Model model){
		model.addAttribute("tasks",taskService.findByInterval(tomorrowInterval()));
		addTags(model);
		return "index";
	}
	
	@RequestMapping("/add")
	public String add(Model model) {
	    // on injecte une Task vierge dans le modèle
	    model.addAttribute("task", new Task());
	    addTags(model);
	    return "edit";
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String post(@ModelAttribute Task task, BindingResult result) {
	    if (result.hasErrors()) {
	        return "edit";
	    }
	    taskService.save(task);
	    return "redirect:/";
	}
	
	@RequestMapping("/edit/{id}")
	public String edit(@PathVariable Long id, Model model) {
	    model.addAttribute("task", taskService.findById(id));
	    return "edit";
	}
	
	@InitBinder
	public void initBinder(DataBinder binder) {
	    SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormatter, true));
	}
	
	private Interval todayInterval() {
	    DateMidnight today = new DateMidnight();
	    return new Interval(today, today.plusDays(1));
	}
	
	private Interval tomorrowInterval() {
	    DateMidnight today = new DateMidnight();

	    return new Interval(today.plusDays(1), today.plusDays(2));
	}

	public void addTags(Model model){
		List<String> tags = tagCloudService.buildTagCloud().getTags();
		model.addAttribute("tags",	tags);
	}
	
	@PostConstruct
	public void bootstrap() {
		if (taskService.count() == 0) {
			Task tache1 = new Task();
			tache1.setDate(new Date());
			tache1.setTags("bientot,manger");
			tache1.setText("On a faim!!!!");
			tache1.setTitle("toto");
			taskService.save(tache1);

			Task tache2 = new Task();
			tache2.setDate(new Date());
			tache2.setTags("lorem ipsum");
			tache2.setText("pfiouuuuuu");
			tache2.setTitle("tutu");
			taskService.save(tache2);

			Task tache3 = new Task();
			tache3.setDate(new Date());
			tache3.setTags("Java c'est bien!");
			tache3.setText("youhou");
			tache3.setTitle("ohohoho");
			taskService.save(tache3);
		}
	}
}