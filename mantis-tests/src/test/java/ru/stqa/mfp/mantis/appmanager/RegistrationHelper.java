package ru.stqa.mfp.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegistrationHelper extends HelperBase {

  public RegistrationHelper(ApplicationManager app) {
    super(app);
  }

  public void start(String username, String email) {
    wd.get(app.getProperty("web.baseUrl") + "/signup_page.php");
    type(By.name("username"), username);
    type(By.name("email"), email);
    click(By.cssSelector("input[value='Зарегистрироваться']"));
  }

  public void finish(String confirmationLink, String password) throws InterruptedException {
    wd.get(confirmationLink);
    Thread.sleep(700);
    type(By.name("password"), password);
    type(By.name("password_confirm"), password);
    //click(By.cssSelector("input[value='Update User']"));
    //click(By.cssSelector("span[class='submit-button']"));
    click(By.cssSelector("button[type='submit']"));
  }
}
