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
    if (app.db().contacts().size() == 0) {
      app.goTo().homePage();
      app.contact().create(new UserName().withFirstname("nameFirstCreate").withLastname("nameLastCreate").withMiddlename("nameMiddleCreate")
              .withNickname("nameNickCreate").withAddress("Moskva\nst. Mira\n33-18").withHomePhone("(495)999-99-99").withMobilePhone("+7(999)999 99 99")
              .withWorkPhone("9999999999").withEmail("testerCreate@test.ru").withEmail2("tester2Create@test.ru").withEmail3("tester3Create@test.ru"));
    }
  }

  @Test //(enabled = false)
  public void testUserDeletion() {
    Contacts before = app.db().contacts();
    UserName deletedUser = before.iterator().next();
    app.goTo().homePage();
    app.contact().delete(deletedUser);
    assertEquals(app.contact().count(),before.size() - 1);
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before.without(deletedUser)));
    validateContactListInUI();
  }
}
