package pl.coderstrust.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.coderstrust.logic.StudentProcessor;
import pl.coderstrust.model.Student;

@RestController
@RequestMapping("/student")
public class StudentController {
  
  @Autowired
  private StudentProcessor processor;
  
  
  @PostMapping(value = "/")
  public Student save(@RequestParam String gitHubLogin) {
    return processor.save(gitHubLogin);
  }
  
  @GetMapping("/")
  public Iterable<Student> findAll() {
    return processor.findAll();
  }
  
  @GetMapping("/{id}")
  public Student get(Long id) {
    return processor.getOne(id);
  }
  
  @DeleteMapping(value = "/{gitHubLogin}")
  public void deleteByLogin(String gitHubLogin) {
    processor.delete(gitHubLogin);
  }
  
}
