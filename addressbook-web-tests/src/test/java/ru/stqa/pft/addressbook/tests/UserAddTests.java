package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;
import ru.stqa.pft.addressbook.model.UserDateBirth;
import ru.stqa.pft.addressbook.model.UserName;
import ru.stqa.pft.addressbook.model.UserPhoneEmail;

public class UserAddTests extends TestBase {


  @Test
  public void testUserAdd() {
    app.getNavigationHelper().gotoHomePage();
    app.getContactHelper().createContact(new UserName("nameFirstCreate", "nameMiddleCreate", "nameLastCreate", "ololoCreate"),
            new UserPhoneEmail("495999999", "9999999999", "testerCreate@test.ru"),
            new UserDateBirth("1917", "2017"),
            new UserData("myCompanyCreate", "myAddressCreate", "testCreate.ru", "groupName"));
  }

}
