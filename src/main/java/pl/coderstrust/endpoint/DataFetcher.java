package pl.coderstrust.endpoint;

import org.kohsuke.github.GHIssueState;
import org.kohsuke.github.GHPullRequest;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GHUser;
import org.kohsuke.github.GitHub;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pl.coderstrust.model.Student;
import pl.coderstrust.model.Task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class DataFetcher {
  private String userLogin;
  private String userPassword;
  private static GitHub gitHub;
  private static Logger logger = LoggerFactory.getLogger(DataFetcher.class);
  
  
  /**
   * Connection is instantiated on application run.
   *
   * @param userLogin - TODO define on login
   * @param userPassword -TODO define on login
   */
  DataFetcher(
      @Value("${pl.ct.ep.EPA.userLogin}") String userLogin,
      @Value("${pl.ct.ep.EPA.userPassword}") String userPassword) {
    logger.debug("DataFetcher initiated");
    this.userLogin = userLogin;
    this.userPassword = userPassword;
    try {
      
      gitHub = GitHub.connectUsingPassword(userLogin, userPassword);
    } catch (IOException e) {
      logger.debug("Connected to GitHub @" + "\"" + this.userLogin + "\"");
    }
  }
  
  public static long getGitHubUserId(String gitHubLogin) {
    try {
      return gitHub.getUser(gitHubLogin).getId();
    } catch (IOException e) {
      logger.error("Couldn't get GitHub user: " + gitHubLogin, e);
      return 0L;
    }
  }
  
  public static GitHub getGtiHubConnection() {
    return gitHub;
  }
  
  public static Map<String, GHRepository> getReposByLogin(String login) {
    try {
      return gitHub.getUser(login).getRepositories();
    } catch (IOException e) {
      logger.error("Couldn't get GitHub user: " + login, e);
      return null;
    }
  }
  
  public Map<String, GHRepository> getUserRepository(String repositoryName) {
    Map<String, GHRepository> repositoryMap = new HashMap<>();
    try {
      repositoryMap.put(repositoryName, gitHub.getUser(userLogin).getRepository(repositoryName));
    } catch (IOException e) {
      logger.error("Couldn't get GitHub user: " + userLogin, e);
    }
    return repositoryMap;
  }
  
  public Map<String, GHRepository> getUserRepository() throws IOException {
    return gitHub.getUser(userLogin).getRepositories();
  }
  
  
  public static GHRepository matchRepoByStudent(Map<String, GHRepository> repositoryMap,
      Student student) {
    GHRepository theRepository = new GHRepository();
  
    List<GHRepository> repositories = new ArrayList<>(repositoryMap.values());
    try {
      for (GHRepository repository : repositories) {
        
        Set<GHUser> collaborators = repository.getCollaborators();
        for (GHUser user : collaborators) {
          if (user.getLogin().equals(student.getLogin())) {
            theRepository = repository;
          }
        }
      }
    } catch (IOException e) {
      logger.error("Student not found among all repositories/collaborators" + e);
    }
    return theRepository;
  }
  
  
  public static List<GHPullRequest> getPullByTaskName(GHRepository repository, Task task) {
    List<GHPullRequest> pullRequestList = new ArrayList<>();
    
    try {
      List<GHPullRequest> pullRequests = repository.getPullRequests(GHIssueState.ALL);
      for(GHPullRequest pullRequest:pullRequests){
        if(pullRequest.getHead().getRef().equals(task.getName())){
          pullRequestList.add(pullRequest);
        }
      }
      
      
    } catch (IOException e) {
      logger.error("Task not found among all repository braches/collaborators" + e);
    }
    return pullRequestList;
  }
  
}
