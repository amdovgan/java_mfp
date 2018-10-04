package ru.stqa.mfp.rest;

import com.jayway.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Set;

import static org.testng.Assert.assertEquals;

public class RestAssuredTestNext extends TestBase {

  @BeforeClass
  public void init() {
    RestAssured.authentication = RestAssured.basic("288f44776e7bec4bf44fdfeb1e646490", "");
  }

  @Test (enabled = true)
  public void testIssueStatus() throws IOException {
    skipIfNotFixed(10);
  }

  @Test (enabled = false )
  public void testCreateIssue() throws IOException {
    skipIfNotFixed(357);
    Set<Issue> oldIssues = getIssues();
    Issue newIssue = new Issue().withSubject("Tets issue").withDescription("NewNew test issue");
    int issueId = createIssue(newIssue);
    Set<Issue> newIssues = getIssues();
    oldIssues.add(newIssue.withId(issueId));
    assertEquals(newIssues, oldIssues);
  }


}
