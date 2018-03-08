package pl.coderstrust.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.coderstrust.logic.SummaryProcessor;
import pl.coderstrust.model.Status;

@RestController
@RequestMapping("/summary")
public class SummaryController {
  
  @Autowired
  SummaryProcessor processor;
  
  //TODO generate status list for each student (Map<StudentId, List<Student's task status>)
  
  @GetMapping("/")
  public Status[][] generateSummary() {
    return processor.buildSummary();
  }
  
  
}
