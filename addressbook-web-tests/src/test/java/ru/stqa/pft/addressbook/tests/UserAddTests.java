package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;
import ru.stqa.pft.addressbook.model.UserDateBirth;
import ru.stqa.pft.addressbook.model.UserName;
import ru.stqa.pft.addressbook.model.UserPhoneEmail;

import java.util.Comparator;
import java.util.List;

public class UserAddTests extends TestBase {


  @Test
  public void testUserAdd() {
    app.getNavigationHelper().gotoHomePage();
    List<UserName> before = app.getContactHelper().getUserNameList();
    UserName nameofuser = new UserName("nameFirstCreate", null, "nameLastCreate", null);
    app.getContactHelper().createContact(nameofuser,
            new UserPhoneEmail("495999999", "9999999999", "testerCreate@test.ru"),
            new UserDateBirth("1917", "2017"),
            new UserData("myCompanyCreate", "myAddressCreate", "testCreate.ru", "groupCreateName"));
    List<UserName> after = app.getContactHelper().getUserNameList();
    Assert.assertEquals(after.size(),before.size() + 1);

    before.add(nameofuser);
    Comparator<? super UserName> byId = (u1, u2) -> Integer.compare(u1.getId(), u2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }

}
