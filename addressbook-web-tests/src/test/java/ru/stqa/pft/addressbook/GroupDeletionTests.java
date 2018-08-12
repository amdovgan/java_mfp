package ru.stqa.pft.addressbook;

import org.testng.annotations.Test;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.*;

public class GroupDeletionTests extends TestBase {

  @Test
  public void testGroupDeletion() {
    gotoGroupePage();
    selectGroup();
    deleteSelectedGroups();
    returnToGroupPage();
  }

}
