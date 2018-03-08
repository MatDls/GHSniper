package pl.coderstrust.model;

import lombok.Data;

import java.util.Collections;
import java.util.List;


@Data
public class SummaryTable {
  
  private List<Student> studentList;
  private List<Task> taskList;
  private Status[][] statusTable;
  
  public SummaryTable(List<Student> studentList, List<Task> taskList) {
    this.studentList = studentList;
    Collections.sort(this.studentList);
    this.taskList = taskList;
    Collections.sort(this.taskList);
    this.statusTable = generateEmptyStatusTable(this.studentList, this.taskList);
  }
  
  
  /**
   * Create single EMPTY table of statuses for Student/Task lists
   */
  private Status[][] generateEmptyStatusTable(List<Student> studentList, List<Task> taskList) {
    Status[][] emptyStatusTable = new Status[studentList.size()][taskList.size()];
    for(Student student:studentList){
      for(Task task: taskList){
        createEmptyStatusForStudentTask(student,task,emptyStatusTable);
      }
    }
    return emptyStatusTable;
  }
  
  /**
   * Create single EMPTY status for Student/Task
   */
  private void createEmptyStatusForStudentTask(Student student, Task task, Status[][] statusTable) {
    Status status = new Status(student,task);
    statusTable[studentList.indexOf(student)][taskList.indexOf(task)] =status;
  }
  
  
}
