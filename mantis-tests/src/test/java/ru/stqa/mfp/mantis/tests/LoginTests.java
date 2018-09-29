package ru.stqa.mfp.mantis.tests;

import org.testng.annotations.Test;
import ru.stqa.mfp.mantis.appmanager.HttpSession;

import java.io.IOException;

import static org.testng.Assert.assertTrue;

public class LoginTests extends TestBase {

  @Test
  public void testLogin() throws IOException {
    HttpSession session = app.newSession();
    assertTrue(session.login("administrator", "administrator"));
    assertTrue(session.isLoggedInAs("administrator"));
  }

}
