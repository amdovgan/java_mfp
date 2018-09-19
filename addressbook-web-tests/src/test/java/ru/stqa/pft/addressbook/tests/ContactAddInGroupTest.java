package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.UserName;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactAddInGroupTest extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().contacts().size() == 0) {
      app.goTo().homePage();
      app.contact().create(new UserName().withFirstname("nameFirstMod").withLastname("nameLastMod")
              .withMiddlename("nameMiddleMod").withNickname("nameNickMod").withAddress("Plast l. Lazo 4")
              .withHomePhone("(495)888-88-88").withMobilePhone("+7(888)888 88 88").withWorkPhone("9999999999")
              .withEmail("testerCreate@test.ru").withEmail2("tester2Create@test.ru").withEmail3("tester3Create@test.ru"));
    }
    if (app.db().groups().size() == 0) {
      app.goTo().groupePage();
      app.group().create(new GroupData().withName("groupCreateName"));
    }
  }
  @Test
  public void testContactAddInGroup() {
    Contacts before = app.db().contacts();
    UserName modifiedUser = before.iterator().next();
/*
    UserName nameOfUser = new UserName().withId(modifiedUser.getId()).withFirstname("nameFirstMod").withLastname("nameLastMod")
            .withMiddlename("nameMiddleMod").withNickname("nameNickMod").withAddress("Plast l. Lazo 4")
            .withHomePhone("(495)888-88-88").withMobilePhone("+7(888)888 88 88").withWorkPhone("9999999999")
            .withEmail("testerCreate@test.ru").withEmail2("tester2Create@test.ru").withEmail3("tester3Create@test.ru");
*/
    app.goTo().homePage();
    app.contact().addGroupToContact(modifiedUser);
    assertEquals(app.contact().count(),before.size());
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before.without(modifiedUser).withAdded(modifiedUser)));
    //validateContactListInUI();
  }
}
