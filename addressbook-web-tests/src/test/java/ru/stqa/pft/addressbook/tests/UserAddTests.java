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


  @Test //(enabled = false)
  public void testUserAdd() {
    app.goTo().homePage();
    List<UserName> before = app.contact().list();
    UserName nameofuser = new UserName("nameFirstCreate", null, "nameLastCreate", null);
    UserPhoneEmail phoneEmailOfUser = new UserPhoneEmail ("495999999", "9999999999", "testerCreate@test.ru");
    UserDateBirth birthDateOfUser = new UserDateBirth ("1917", "2017");
    UserData dataOfUser = new UserData("myCompanyCreate", "myAddressCreate", "testCreate.ru", "groupCreateName");
    app.contact().create(nameofuser, phoneEmailOfUser, birthDateOfUser, dataOfUser);
    List<UserName> after = app.contact().list();
    Assert.assertEquals(after.size(),before.size() + 1);

    before.add(nameofuser);
    Comparator<? super UserName> byId = (u1, u2) -> Integer.compare(u1.getId(), u2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }

}
