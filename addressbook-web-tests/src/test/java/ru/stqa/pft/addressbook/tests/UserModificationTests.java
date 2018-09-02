package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;
import ru.stqa.pft.addressbook.model.UserDateBirth;
import ru.stqa.pft.addressbook.model.UserName;
import ru.stqa.pft.addressbook.model.UserPhoneEmail;

import java.util.Comparator;
import java.util.List;

public class UserModificationTests extends TestBase {

  @Test
  public void testUserModification() {
    app.getNavigationHelper().gotoHomePage();
    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new UserName("nameFirstCreate", null, "nameLastCreate", null),
              new UserPhoneEmail("495999999", "9999999999", "testerCreate@test.ru"),
              new UserDateBirth("1917", "2017"),
              new UserData("myCompanyCreate", "myAddressCreate", "testCreate.ru", null));
    }
    List<UserName> before = app.getContactHelper().getUserNameList();
    app.getContactHelper().selectUser(before.size() - 1);
    app.getContactHelper().initUserModification(before.size() - 1);
    UserName nameOfUser = new UserName(before.get(before.size() - 1).getId(),"nameFirstMod", null, "nameLastMod", null);
    app.getContactHelper().fillUserForm(nameOfUser,
            new UserPhoneEmail("495999998", "9999999998", "tester@test.com"),
            new UserDateBirth("2917", "3917"));
    app.getContactHelper().fillUserData(new UserData("mycompany5", "myAddressMod", "test.com", null), false);
    app.getContactHelper().submitUserModification();
    app.getContactHelper().returnToHomePage();
    List<UserName> after = app.getContactHelper().getUserNameList();
    Assert.assertEquals(after.size(),before.size());

    before.remove(before.size() - 1);
    before.add(nameOfUser);
    Comparator<? super UserName> byId = (u1, u2) -> Integer.compare(u1.getId(), u2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }
}
