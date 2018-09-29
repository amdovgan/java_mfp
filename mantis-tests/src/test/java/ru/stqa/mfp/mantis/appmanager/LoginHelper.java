package ru.stqa.mfp.mantis.appmanager;

import org.openqa.selenium.By;

public class LoginHelper extends HelperBase {

  public LoginHelper(ApplicationManager app) {
    super(app);
  }

  public void userLogin(String user, String password) {
    wd.get(app.getProperty("web.baseUrl") + "/login_page.php");
    type(By.name("username"), user);
    click(By.cssSelector("input[value='Войти']"));
    type(By.name("password"), password);
    click(By.cssSelector("input[value='Войти']"));
  }


  public void resetUsePassword(String username) throws InterruptedException {
    wd.findElement(By.cssSelector("a[href='/mantisbt-2.17.1/manage_user_page.php']")).click();
    click(By.cssSelector("input[id='search']"));
    type(By.cssSelector("input[id='search']"), username);
    click(By.cssSelector("input[value='Фильтровать']"));
    click(By.linkText(username));
    Thread.sleep(500);
    click(By.cssSelector("input[value='Сбросить пароль']"));
  }
}
