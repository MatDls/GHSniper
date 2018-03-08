package pl.coderstrust.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;


@Data
@Entity
@Table(name = "task")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Task implements Comparable<Task> {
  
  
  @Id
  @GeneratedValue
  private Long id;
  
  @Column(nullable = false)
  private String name;
  
  public Task(Long id, String name) {
    this.id = id;
    this.name = name;
  }
  
  public Task(String name) {
    this.name = name;
  }
 
  public Task() {
  }

  @Override
  public int compareTo(Task task) {
    Long res = this.getId() - task.getId();
    if (res > 0) {
      return 1;
    } else if (res < 0) {
      return -1;
    } else {
      return 0;
    }
  }
}
