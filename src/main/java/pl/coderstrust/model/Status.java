package pl.coderstrust.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Data;
import org.kohsuke.github.GHIssueState;
import org.kohsuke.github.GHPullRequest;
import org.springframework.data.annotation.Id;

import java.net.URL;


@Data
@Entity
@Table(name = "status")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Status implements Comparable<Status> {
  
  @Id
  private Long id;
  
  @Column(name = "studentId")
  private Long studentId;
  @Column(name = "taskId")
  private Long taskId;
  @Column(name = "pullUrl")
  private URL pullUrl;
  @Column(name = "status")
  private TaskStatus taskStatus;
  
  // 1. Branch don't exists   -Not started
  // 2. Commits 1+            -Started
  // 3. PR exists             -Release
  // 4. Review: request ch.   -Iteration
  // 5. Review: Approved      -
  
  
  public Status(Student student, Task task) {
    this.studentId = student.getId();
    this.taskId = task.getId();
    this.id =studentId+taskId;
    
  }
  
  //TODO kohsuke objects stay in endpoint package!
  public Status(GHPullRequest pullRequest) {
    if (pullRequest == null) {
//      this.status = "No Pull Request";
    } else if (pullRequest.getState() == GHIssueState.OPEN) {
//      this.status = "Pull Active";
    } else if (pullRequest.getState() == GHIssueState.CLOSED) {
//      this.status = "Pull closed";
    }
  }
  
  @Override
  public int compareTo(Status status) {
    Long res = this.getId()-status.getId();
    if (res > 0) {
      return 1;
    } else if (res < 0) {
      return -1;
    } else {
      return 0;
    }
  }
}
