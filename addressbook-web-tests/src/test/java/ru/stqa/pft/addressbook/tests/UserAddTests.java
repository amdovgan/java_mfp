package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;
import ru.stqa.pft.addressbook.model.UserDateBirth;
import ru.stqa.pft.addressbook.model.UserName;
import ru.stqa.pft.addressbook.model.UserPhoneEmail;

import java.util.List;

public class UserAddTests extends TestBase {


  @Test
  public void testUserAdd() {
    app.getNavigationHelper().gotoHomePage();
    List<UserName> before = app.getContactHelper().getUserNameList();
    app.getContactHelper().createContact(new UserName("nameFirstCreate", "nameMiddleCreate", "nameLastCreate", "ololoCreate"),
            new UserPhoneEmail("495999999", "9999999999", "testerCreate@test.ru"),
            new UserDateBirth("1917", "2017"),
            new UserData("myCompanyCreate", "myAddressCreate", "testCreate.ru", "groupName"));
    List<UserName> after = app.getContactHelper().getUserNameList();
    Assert.assertEquals(after.size(),before.size() + 1);
  }

}
