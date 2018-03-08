package pl.coderstrust.endpoint;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import junitparams.JUnitParamsRunner;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kohsuke.github.GHBranch;
import org.kohsuke.github.GHIssueState;
import org.kohsuke.github.GHPullRequest;
import org.kohsuke.github.GHPullRequestReview;
import org.kohsuke.github.GHPullRequestReviewState;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GHUser;
import org.kohsuke.github.GitHub;
import org.kohsuke.github.PagedIterable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(JUnitParamsRunner.class)
public class DataFetcherTest {

  String apiUrl = "https://api.github.com";

  private String otherUser = "CodersTrustPL";
  private DataFetcher endPoint;
  private GitHub gitHub;
  private String userLogin;
  
  @Before
  public void doBeforeTest() throws IOException {
    gitHub = DataFetcher.getGtiHubConnection();
    userLogin = gitHub.getMyself().getLogin();
  }
  
  @Test
  public void shouldNotThrowException() {
    assertEquals(apiUrl, gitHub.getApiUrl());
  }
  
  @Test(expected = org.kohsuke.github.HttpException.class)
  public void shouldThrowException() throws IOException {
    //given
    DataFetcher badEndPoint = new DataFetcher(
        userLogin, "someBadPassword");
    GitHub badGitHubCredentials = DataFetcher.getGtiHubConnection();
  
    //when
    Map<String, GHRepository> repositories = badGitHubCredentials
        .getUser(userLogin).getRepositories();
  }
  
  @Test
  public void shouldGetUsername() throws IOException {
    // when
    GHUser user = gitHub.getUser(userLogin);
    
    // then
    assertEquals(userLogin, user.getLogin());
  }
  
  @Test
  public void shouldGetOtherUsername() throws IOException {
    // when
    GHUser other = gitHub.getUser(otherUser);
    
    // then
    assertEquals(otherUser, other.getLogin());
  }
  
  @Test
  public void shouldListRepositories() throws IOException {
    //when
    Map<String, GHRepository> repositories = gitHub
        .getUser(userLogin).getRepositories();
  
    //then
    assertTrue(repositories.containsKey("Settings_etc"));
  }
  
  
  @Test
  public void shouldShowActivePullRequest() throws IOException {
    // when
    GHRepository repository = gitHub
        .getUser(userLogin).getRepository("Settings_etc");
    GHPullRequest pullRequest = repository.getPullRequest(1);
  
    // then
    assertEquals("New settings", pullRequest.getTitle());
  }
  
  
  
  
  @Test
  @Ignore
  //TODO How to get data from list of pull requests?
  //TODO Current solution fetches data for each pull individually - HTTP looping takes time.
  public void shouldGetAllPalsFromGroupRepo() throws IOException {
    GHRepository groupRepo = gitHub.getUser("CodersTrustPL")
        .getRepository("solutions-3-mateusz-d");
    List<GHPullRequest> prList = groupRepo.getPullRequests(GHIssueState.ALL);
  
    for (int loop = 0; loop < prList.size(); loop++) {
      System.out.println("\n" + loop);
      System.out.println("getHtmlUrl();         " + prList.get(loop).getHtmlUrl());
      System.out.println("getUser().getName();  " + prList.get(loop).getUser().getName());
      System.out.println("getTitle();           " + prList.get(loop).getTitle());
      System.out.println("getNumber();          " + prList.get(loop).getNumber());
      System.out.println("getId();              " + prList.get(loop).getId());
      System.out.println("getHead().getRef();   " + prList.get(loop).getHead().getRef());
      System.out.println("getState();           " + prList.get(loop).getState());
      System.out.println("isMerged();           " + prList.get(loop).isMerged());
      System.out.println("isMerged();           " + groupRepo.getCollaborators());
//      System.out.println(gitHub.createOrGetAuth(gitHub.getUser("MatDls").getId(),gitHub.  ));
    
      
      System.out.println("getReviewComments();  " + prList.get(loop).getReviewComments());
      PagedIterable<GHPullRequestReview> reviews = prList.get(loop).listReviews();
      List<String> reviewerList = new ArrayList<>();
      List<GHPullRequestReviewState> reviewStateList = new ArrayList<>();
      for (GHPullRequestReview review : reviews) {
        reviewStateList.add(review.getState());
        reviewerList.add(review.getUser().getName());
      }
      System.out.println("review.getState();    " + reviewStateList);
      System.out.println("review.getUser();     " + reviewerList);
    
    }
  }
  
  @Test
  public void fun() throws IOException {
  }
  
}