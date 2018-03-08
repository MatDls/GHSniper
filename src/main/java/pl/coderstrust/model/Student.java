package pl.coderstrust.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

import java.net.URL;

@Data
@Entity
@Table(name = "student")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Student implements Comparable<Student> {
  

  @Id
  private Long id;
  
  @Column(name = "name")
  private String name;
  
  @Column(nullable = false,name = "login")
  private String login;
  
  @Column(name = "repositoryUrl")
  private URL repositoryUrl;
  
  public Student(Long id, String name, String login, URL repositoryUrl) {
    this.id = id;
    this.name = name;
    this.login = login;
    this.repositoryUrl = repositoryUrl;
  }
  
  public Student() {
  }
  
  @Override
  public int compareTo(Student student) {
    Long res = this.getId()-student.getId();
    if (res > 0) {
      return 1;
    } else if (res < 0) {
      return -1;
    } else {
      return 0;
    }
  }
}
