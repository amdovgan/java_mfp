package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;
import ru.stqa.pft.addressbook.model.UserDateBirth;
import ru.stqa.pft.addressbook.model.UserName;
import ru.stqa.pft.addressbook.model.UserPhoneEmail;

import java.util.Set;

public class UserDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().homePage();
    if (app.contact().all().size() == 0) {
      app.contact().create(new UserName().withFirstname("nameFirstCreate").withLastname("nameLastCreate"),
              new UserPhoneEmail().withHomephone("495999999").withMobilephone("9999999999").withEmail("testerCreate@test.ru"),
              new UserDateBirth("1917", "2017"),
              new UserData().withCompanyname("myCompanyCreate").withAddress("myAddressCreate").withHomepageurl("testCreate.ru").withGroup("groupName"));
    }
  }

  @Test //(enabled = false)
  public void testUserDeletion() {
    Set<UserName> before = app.contact().all();
    UserName deletedUser = before.iterator().next();
    app.contact().delete(deletedUser);
    Set<UserName> after = app.contact().all();
    Assert.assertEquals(after.size(),before.size() - 1);

    before.remove(deletedUser);
    Assert.assertEquals(after, before);

  }

}
