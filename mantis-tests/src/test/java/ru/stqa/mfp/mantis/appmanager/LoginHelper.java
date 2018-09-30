package ru.stqa.mfp.mantis.appmanager;

import org.openqa.selenium.By;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.mfp.mantis.model.MailMessage;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class LoginHelper extends HelperBase {

  public LoginHelper(ApplicationManager app) {
    super(app);
  }

  public String userRegistration() throws IOException, MessagingException, InterruptedException {
    long now = System.currentTimeMillis();
    String user = String.format("user%s", now);
    String password = "password";
    String email = String.format("user%s@localhost", now);
    app.james().createUser(user, password);
    app.registration().start(user, email);
    //List<MailMessage> mailMessages = app.mail().waitForMail(2, 10000);
    List<MailMessage> mailMessages = app.james().waitForMail(user, password, 60000);
    String confirmationLink = findConfirmationLink(mailMessages, email);
    app.registration().finish(confirmationLink, password);
    assertTrue(app.newSession().login(user, password));
    return user;
  }

  public String findConfirmationLink(List<MailMessage> mailMessages, String email) {
    MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
    VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
    return regex.getText(mailMessage.text);
  }


  public String nextConfirmationLink(List<MailMessage> mailMessages, String email) {
    MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
    VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
    return regex.getText(mailMessage.text);
  }



  public void userLogin() {
    String user = "administrator";
    String password = "administrator";
    wd.get(app.getProperty("web.baseUrl") + "/login_page.php");
    type(By.name("username"), user);
    click(By.cssSelector("input[value='Войти']"));
    type(By.name("password"), password);
    click(By.cssSelector("input[value='Войти']"));
  }

  public void resetUserPassword(String username) throws InterruptedException {
    wd.findElement(By.cssSelector("a[href='/mantisbt-2.17.1/manage_user_page.php']")).click();
    Thread.sleep(700);
    click(By.cssSelector("input[id='search']"));
    type(By.cssSelector("input[id='search']"), username);
    click(By.cssSelector("input[value='Фильтровать']"));
    click(By.linkText(username));
    Thread.sleep(700);
    click(By.cssSelector("input[value='Сбросить пароль']"));
  }
}
