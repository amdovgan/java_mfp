package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;
import ru.stqa.pft.addressbook.model.UserDateBirth;
import ru.stqa.pft.addressbook.model.UserName;
import ru.stqa.pft.addressbook.model.UserPhoneEmail;

public class UserDeletionTests extends TestBase {

  @Test
  public void testUserDeletion() {
    app.getNavigationHelper().gotoHomePage();
    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new UserName("nameFirstCreate", "nameMiddleCreate", "nameLastCreate", "ololoCreate"),
              new UserPhoneEmail("495999999", "9999999999", "testerCreate@test.ru"),
              new UserDateBirth("1917", "2017"),
              new UserData("myCompanyCreate", "myAddressCreate", "testCreate.ru", "groupName"));
    }
    app.getContactHelper().selectUser();
    app.getContactHelper().deleteSelectedUsers();
    app.getContactHelper().returnToHomePage();
  }

}
