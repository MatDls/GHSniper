package pl.coderstrust.logic;

import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GHUser;
import org.kohsuke.github.GitHub;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderstrust.db.StudentRepository;
import pl.coderstrust.endpoint.DataFetcher;
import pl.coderstrust.model.Student;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class StudentProcessor {
  
  private static GitHub gitHub;
  
  private static Logger logger = LoggerFactory.getLogger(StudentProcessor.class);
  
  @Autowired
  private StudentRepository repository;
  
  public StudentProcessor(DataFetcher dataFetcher) {
    gitHub = DataFetcher.getGtiHubConnection();
  }
  
  public Student save(String login) {
    return repository.save(getStudentByLogin(login));
  }
  
  public List<Student> findAll() {
    return repository.findAll();
  }
  
  public List<Long> findAllIds() {
    List<Student> studentList = this.findAll();
    List<Long> ids = new ArrayList<>();
    studentList.forEach(student -> ids.add(student.getId()));
    return ids;
  }
  
  public Student getOne(Long id) {
    return repository.getOne(id);
  }
  
  public void delete(String login) {
    repository.delete(getStudentByLogin(login));
  }
  
  public Map<String, GHRepository> repositoryList(String token) {
    return DataFetcher.getReposByLogin(token);
  }
  
  
  
  private Student getStudentByLogin(String ghLogin) {
    GHUser user;
    Long id = null;
    String name = null;
    try {
      user = gitHub.getUser(ghLogin);
      id = user.getId();
      name = user.getName();
    } catch (IOException e) {
      logger.error("Not Valid GitHub login: " + ghLogin);
    }
    if (name == null || name.equals("")) {
      name = "~n/a~";
    }
    return new Student(id, name, ghLogin, null);
  }
  
  
}
