package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;
import ru.stqa.pft.addressbook.model.UserDateBirth;
import ru.stqa.pft.addressbook.model.UserName;
import ru.stqa.pft.addressbook.model.UserPhoneEmail;

import java.util.Set;

public class UserModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().homePage();
    if (app.contact().all().size() == 0) {
      app.contact().create(new UserName().withFirstname("nameFirstCreate").withLastname("nameLastCreate"),
              new UserPhoneEmail().withHomephone("495999999").withMobilephone("9999999999").withEmail("testerCreate@test.ru"),
              new UserDateBirth("1917", "2017"),
              new UserData().withCompanyname("myCompanyCreate").withAddress("myAddressCreate").withHomepageurl("testCreate.ru"));
    }
  }

  @Test //(enabled = false)
  public void testUserModification() {
    Set<UserName> before = app.contact().all();
    UserName modifiedUser = before.iterator().next();
    UserName nameOfUser = new UserName().withId(modifiedUser.getId()).withFirstname("nameFirstMod").withLastname("nameLastMod");
    UserPhoneEmail phoneEmailOfUser = new UserPhoneEmail().withHomephone("495999998").withMobilephone("9999999998").withEmail("tester@test.com");
    UserDateBirth birthDateOfUser = new UserDateBirth ("2917", "3917");
    UserData dataOfUser = new UserData().withCompanyname("mycompany5").withAddress("myAddressMod").withHomepageurl("test.com");
    app.contact().modify(nameOfUser, phoneEmailOfUser, birthDateOfUser, dataOfUser);
    Set<UserName> after = app.contact().all();
    Assert.assertEquals(after.size(),before.size());

    before.remove(modifiedUser);
    before.add(nameOfUser);
    Assert.assertEquals(before, after);
  }

}
