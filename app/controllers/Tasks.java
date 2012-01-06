package controllers;

import play.data.validation.Valid;
import play.mvc.Controller;

import models.Task;

import java.util.List;

/**
 * @author i.orlov
 */

public class Tasks extends Controller {

  public static List<Task> getTasks() {
  	List<Task> tasks = Task.all().fetch();
  	return tasks;
  }

  public static void editTask(Long id) {
  	Task task = Task.findById(id);
  	render(task);
  }

  public static void saveTask(@Valid Task task) {
    if (validation.hasErrors()) {
      render("@editTask", task);
    }
    
    task.save();
    refreshPage();
  }

  public static void addTask(@Valid Task task) {
    if (validation.hasErrors()) {
      flash.error("Can't create task. Incorrect fields.");
    } else {
      task.create();
    }
    refreshPage();
  }

  public static void deleteTask(Long id) {
    Task task = Task.findById(id);
    task.delete();
    refreshPage();
  }

  private static void refreshPage() {
    Application.search("");
  }
}
