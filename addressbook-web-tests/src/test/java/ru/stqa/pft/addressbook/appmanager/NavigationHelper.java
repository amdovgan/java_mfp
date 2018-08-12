package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

public class NavigationHelper {
  private FirefoxDriver wd;

  public NavigationHelper(FirefoxDriver wd) {
    this.wd = wd;
  }

  public void gotoGroupePage() {
    wd.findElement(By.linkText("groups")).click();
  }

  public void gotoUserForm() {
    wd.findElement(By.linkText("add new")).click();
  }
}
