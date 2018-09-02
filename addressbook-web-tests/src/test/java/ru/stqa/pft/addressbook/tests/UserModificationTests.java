package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;
import ru.stqa.pft.addressbook.model.UserDateBirth;
import ru.stqa.pft.addressbook.model.UserName;
import ru.stqa.pft.addressbook.model.UserPhoneEmail;

import java.util.Comparator;
import java.util.List;

public class UserModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().homePage();
    if (app.contact().list().size() == 0) {
      app.contact().create(new UserName("nameFirstCreate", null, "nameLastCreate", null),
              new UserPhoneEmail("495999999", "9999999999", "testerCreate@test.ru"),
              new UserDateBirth("1917", "2017"),
              new UserData("myCompanyCreate", "myAddressCreate", "testCreate.ru", null));
    }
  }

  @Test //(enabled = false)
  public void testUserModification() {
    List<UserName> before = app.contact().list();
    int index = before.size() - before.size();
    UserName nameOfUser = new UserName(before.get(index).getId(),"nameFirstMod", null, "nameLastMod", null);
    UserPhoneEmail phoneEmailOfUser = new UserPhoneEmail ("495999998", "9999999998", "tester@test.com");
    UserDateBirth birthDateOfUser = new UserDateBirth ("2917", "3917");
    UserData dataOfUser = new UserData("mycompany5", "myAddressMod", "test.com", null);
    app.contact().modify(index, nameOfUser, phoneEmailOfUser, birthDateOfUser, dataOfUser);
    List<UserName> after = app.contact().list();
    Assert.assertEquals(after.size(),before.size());

    before.remove(index);
    before.add(nameOfUser);
    Comparator<? super UserName> byId = (u1, u2) -> Integer.compare(u1.getId(), u2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }

}
