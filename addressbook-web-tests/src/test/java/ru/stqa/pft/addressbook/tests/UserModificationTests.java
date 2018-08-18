package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;
import ru.stqa.pft.addressbook.model.UserDateBirth;
import ru.stqa.pft.addressbook.model.UserName;
import ru.stqa.pft.addressbook.model.UserPhoneEmail;

public class UserModificationTests extends TestBase {

  @Test
  public void testUserModification() {
    app.getNavigationHelper().gotoHomePage();
    app.getContactHelper().selectUser();
    app.getContactHelper().initUserModification();
    app.getContactHelper().fillUserForm(new UserName("name1", "name2", "name3", "4"),  new UserPhoneEmail("495999998", "9999999998", "tester@test.com"),new UserDateBirth("2917", "3917"));
    app.getContactHelper().fillUserData(new UserData("mycompany5", "myaddress6", "test.com", null), false);
    app.getContactHelper().submitUserModification();
    app.getContactHelper().returnToHomePage();

  }
}
