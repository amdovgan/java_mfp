package ru.stqa.mfp.mantis.tests;

import org.testng.annotations.Test;
import ru.stqa.mfp.mantis.appmanager.HttpSession;
import ru.stqa.mfp.mantis.model.MailMessage;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class AdministratorPasswordChangeTests extends TestBase{


  @Test
  public void testAdministratorPasswordChange() throws IOException, MessagingException, InterruptedException {
    String username = app.login().userRegistration();
    String userpassword = "password";
    String email = username + "@localhost";
    String passwordnew = "passwordnew";
    app.login().userLogin();
    app.login().resetUserPassword(username);
    List<MailMessage> mailMessages = app.james().waitForMailNext(username, userpassword, 60000);
    String confirmationLink = app.login().nextConfirmationLink(mailMessages, email);
    app.registration().finish(confirmationLink, passwordnew);
    HttpSession session = app.newSession();
    assertTrue(session.login(username, passwordnew));
    assertTrue(session.isLoggedInAs(username));
  }

}
