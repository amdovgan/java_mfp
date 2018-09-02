package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.*;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

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
    Contacts before = app.contact().all();
    UserName deletedUser = before.iterator().next();
    app.contact().delete(deletedUser);
    Contacts after = app.contact().all();
    assertEquals(after.size(),before.size() - 1);
    assertThat(after, equalTo(before.without(deletedUser)));

  }
}
