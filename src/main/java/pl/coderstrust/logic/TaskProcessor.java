package pl.coderstrust.logic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderstrust.db.TaskRepository;
import pl.coderstrust.endpoint.DataFetcher;
import pl.coderstrust.model.Task;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskProcessor {
  
  private static Logger logger = LoggerFactory.getLogger(TaskProcessor.class);
  
  @Autowired
  private TaskRepository repository;
  
  public TaskProcessor(DataFetcher dataFetcher) {
  }
  
  public Task save(String name) {
    return repository.save(new Task(name));
  }
  
  public List<Task> findAll() {
    return new ArrayList<>(repository.findAll());
  }
  
  public List<Long> findAllIds() {
    List<Task> taskList = this.findAll();
    List<Long> ids = new ArrayList<>();
    taskList.forEach(task -> ids.add(task.getId()));
    return ids;
  }
  
  
  public Task getOne(long id) {
    return repository.getOne(id);
  }
  
  public void delete(String name) {
    repository.delete(findTaskByName(name));
  }
  
  
  private Task findTaskByName(String name) {
    Task task = new Task();
    List<Task> tasks = this.findAll();
    for (Task candidate : tasks) {
      if (candidate.getName().equals(name)) {
        task = candidate;
      }
    }
    return task;
  }
}
