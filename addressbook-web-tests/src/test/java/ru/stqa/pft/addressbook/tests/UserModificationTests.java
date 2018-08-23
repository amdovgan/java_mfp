package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;
import ru.stqa.pft.addressbook.model.UserDateBirth;
import ru.stqa.pft.addressbook.model.UserName;
import ru.stqa.pft.addressbook.model.UserPhoneEmail;

public class UserModificationTests extends TestBase {

  @Test
  public void testUserModification() {
    app.getNavigationHelper().gotoHomePage();
    int before = app.getContactHelper().getContactCount();
    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new UserName("nameFirstCreate", "nameMiddleCreate", "nameLastCreate", "ololoCreate"),
              new UserPhoneEmail("495999999", "9999999999", "testerCreate@test.ru"),
              new UserDateBirth("1917", "2017"),
              new UserData("myCompanyCreate", "myAddressCreate", "testCreate.ru", "groupName"));
    }
    app.getContactHelper().selectUser(before - 1);
    app.getContactHelper().initUserModification();
    app.getContactHelper().fillUserForm(new UserName("nameFirstMod", "nameMiddleMod", "nameLastMod", "4"),
            new UserPhoneEmail("495999998", "9999999998", "tester@test.com"),
            new UserDateBirth("2917", "3917"));
    app.getContactHelper().fillUserData(new UserData("mycompany5", "myAddressMod", "test.com", null), false);
    app.getContactHelper().submitUserModification();
    app.getContactHelper().returnToHomePage();
    int after = app.getContactHelper().getContactCount();
    Assert.assertEquals(after,before);


  }
}
