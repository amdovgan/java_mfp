
package ru.stqa.mfp.rest;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.jayway.restassured.RestAssured;
import org.testng.SkipException;

import java.io.IOException;
import java.util.Set;

import static com.jayway.restassured.RestAssured.authentication;
import static com.jayway.restassured.RestAssured.basic;

public class TestBase {

  public void init() {
    authentication = basic("288f44776e7bec4bf44fdfeb1e646490", "");
  }

  public Set<Issue> getIssues() throws IOException {
    String json = RestAssured.get("http://bugify.stqa.ru/api/issues.json").asString();
    JsonElement parsed = new JsonParser().parse(json);
    JsonElement issues = parsed.getAsJsonObject().get("issues");
    return new Gson().fromJson(issues, new TypeToken<Set<Issue>>(){}.getType());
  }

  public int createIssue(Issue newIssue) throws IOException {
    String json = RestAssured.given()
            .parameter("subject", newIssue.getSubject())
            .parameter("description", newIssue.getDescription())
            .post("http://bugify.stqa.ru/api/issues.json").asString();
    JsonElement parsed = new JsonParser().parse(json);
    return parsed.getAsJsonObject().get("issue_id").getAsInt();
  }


  boolean isIssueOpen(int issueId) {
    String json = RestAssured.get("http://bugify.stqa.ru/api/issues/" + issueId + ".json").asString();
    JsonElement parsed = new JsonParser().parse(json);
    JsonArray issues = parsed.getAsJsonObject().getAsJsonArray("issues");
    String state_name = issues.get(0).getAsJsonObject().get("state_name").getAsString();
    System.out.println(state_name);
    if ((state_name.equals("Resolved"))||(state_name.equals("Closed"))) {
      return false;
    } else return true;
  }

  public void skipIfNotFixed(int issueId) throws IOException {
    if (isIssueOpen(issueId)) {
      throw new SkipException("Ignored because of issue " + issueId);
    }
  }

}
