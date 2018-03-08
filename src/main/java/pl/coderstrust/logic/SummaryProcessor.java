package pl.coderstrust.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderstrust.model.Status;
import pl.coderstrust.model.Student;
import pl.coderstrust.model.SummaryTable;
import pl.coderstrust.model.Task;

import java.util.List;

@Service
public class SummaryProcessor {
  
  private StudentProcessor studentProcessor;
  private TaskProcessor taskProcessor;
  
  @Autowired
  public SummaryProcessor(StudentProcessor studentProcessor,
      TaskProcessor taskProcessor) {
    this.studentProcessor = studentProcessor;
    this.taskProcessor = taskProcessor;
  }
  
  public Status[][] buildSummary() {
    return createFullEmptySummary();
  }
  
  private Status[][] createFullEmptySummary() {
    List<Student> studentIdList = studentProcessor.findAll();
    List<Task> taskIdList = taskProcessor.findAll();
    return new SummaryTable(studentIdList, taskIdList).getStatusTable();
  }
}
