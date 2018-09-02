package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;
import ru.stqa.pft.addressbook.model.UserDateBirth;
import ru.stqa.pft.addressbook.model.UserName;
import ru.stqa.pft.addressbook.model.UserPhoneEmail;

import java.util.List;

public class UserDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().homePage();
    if (app.contact().list().size() == 0) {
      app.contact().create(new UserName().withFirstname("nameFirstCreate").withLastname("nameLastCreate"),
              new UserPhoneEmail().withHomephone("495999999").withMobilephone("9999999999").withEmail("testerCreate@test.ru"),
              new UserDateBirth("1917", "2017"),
              new UserData().withCompanyname("myCompanyCreate").withAddress("myAddressCreate").withHomepageurl("testCreate.ru").withGroup("groupName"));
    }
  }

  @Test //(enabled = false)
  public void testUserDeletion() {
    List<UserName> before = app.contact().list();
    int index = before.size() - 1;
    app.contact().delete(index);
    List<UserName> after = app.contact().list();
    Assert.assertEquals(after.size(),before.size() - 1);

    before.remove(index);
    Assert.assertEquals(after, before);

  }

}
