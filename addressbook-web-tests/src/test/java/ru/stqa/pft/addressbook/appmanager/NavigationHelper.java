package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

public class NavigationHelper extends HelperBase {

  public NavigationHelper(FirefoxDriver wd) {
    super(wd);
  }

  public void gotoGroupePage() {
    click(By.linkText("groups"));
  }

  public void gotoUserForm() {
    click(By.linkText("add new"));
  }
}
