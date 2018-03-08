package pl.coderstrust.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.coderstrust.logic.TaskProcessor;
import pl.coderstrust.model.Task;

@RestController
@RequestMapping("/task")
public class TaskController {

  @Autowired
  private TaskProcessor processor;
  
  @PostMapping(value = "/")
  public Task save(@RequestParam String name) {
    return processor.save(name);
  }
  
  @GetMapping("/")
  public Iterable<Task> findAll() {
    return processor.findAll();
  }
  
  @GetMapping("/{id}")
  public Task get(Integer id) {
    return processor.getOne(id);
  }
  
  @DeleteMapping("/{name}")
  public void delete(String name) {
    processor.delete(name);
  }
}
