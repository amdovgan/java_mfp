package ru.stqa.mfp.github;

import com.google.common.collect.ImmutableMap;
import com.jcabi.github.*;
import org.testng.annotations.Test;

import java.io.IOException;

public class GithubTests {

  @Test
  public void testCommits() throws IOException {
    Github github = new RtGithub("72224ab3c9f27aecb46876a87d824d751208f718");
    RepoCommits commits = github.repos().get(new Coordinates.Simple("amdovgan", "java_mfp")).commits();
    for (RepoCommit commit : commits.iterate(new ImmutableMap.Builder<String, String>().build())) {
      System.out.println(new RepoCommit.Smart(commit).message());
    }
  }
}
