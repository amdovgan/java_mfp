package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.*;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class UserModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().homePage();
    if (app.contact().all().size() == 0) {
      app.contact().create(new UserName().withFirstname("nameFirstCreate").withLastname("nameLastCreate"),
              new UserPhoneEmail().withHomephone("(495)999-99-99").withMobilephone("+7(999)999 99 99").withWorkPhone("9999999999")
                      .withEmail("testerCreate@test.ru").withEmail2("tester2Create@test.ru").withEmail3("tester3Create@test.ru"),
              new UserDateBirth("1917", "2017"),
              new UserData().withCompanyname("myCompanyCreate").withAddress("Moskva\nst. Mira\n33-18").withHomepageurl("testCreate.ru"));
    }
  }

  @Test //(enabled = false)
  public void testUserModification() {
    Contacts before = app.contact().all();
    UserName modifiedUser = before.iterator().next();
    UserName nameOfUser = new UserName().withId(modifiedUser.getId()).withFirstname("nameFirstMod").withLastname("nameLastMod");
    UserPhoneEmail phoneEmailOfUser = new UserPhoneEmail().withHomephone("(495)888-88-88").withMobilephone("+7(888)888 88 88").withWorkPhone("")
            .withEmail("testerMod@test.ru").withEmail2("").withEmail3("tester3Mod@test.ru");
    UserDateBirth birthDateOfUser = new UserDateBirth ("2917", "3917");
    UserData dataOfUser = new UserData().withCompanyname("mycompany5").withAddress("Plast\nl. Lazo\n4").withHomepageurl("test.com");
    app.contact().modify(nameOfUser, phoneEmailOfUser, birthDateOfUser, dataOfUser);
    assertEquals(app.contact().count(),before.size());
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before.without(modifiedUser).withAdded(nameOfUser)));
    //assertThat(after, equalTo(before.withModified(modifiedUser, nameOfUser)));
  }

}
