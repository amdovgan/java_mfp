package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;
import ru.stqa.pft.addressbook.model.UserName;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class AddContactToGroupTest extends TestBase {

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
    app.goTo().homePage();
  }

  @Test
  public void testAddContactToGroup() {
    Contacts contactsBefore = app.db().contacts();
    UserName contactBefore = contactsBefore.iterator().next();
    Groups groupsBefore = app.db().groups();
    GroupData emptyGroup = app.contact().searchEmptyGroup(contactBefore, groupsBefore);
    if (emptyGroup == null) {
      app.goTo().groupePage();
      app.group().create(new GroupData().withName("test" + System.currentTimeMillis()));
      app.goTo().homePage();
      Groups groupsAfter = app.db().groups();
      int groupSize = groupsAfter.size();
      //int lastGroupId = app.db().groups().stream().mapToInt((g) -> g.getId()).max().getAsInt();
      emptyGroup = app.db().groupsList().get(groupSize-1);
    }
    UserName contact = new UserName().withId(contactBefore.getId()).withFirstname(contactBefore.getFirstname()).withLastname(contactBefore.getLastname())
            .withMiddlename(contactBefore.getMiddlename()).withNickname(contactBefore.getMiddlename()).withAddress(contactBefore.getAddress())
            .withHomePhone(contactBefore.getHomephone()).withWorkPhone(contactBefore.getWorkphone()).withMobilePhone(contactBefore.getMobilephone())
            .withEmail(contactBefore.getEmail()).withEmail2(contactBefore.getEmail2()).withEmail3(contactBefore.getEmail3())
            .inGroup(emptyGroup);
    app.contact().addToGroup(contact);
    Contacts contactsAfter = app.db().contacts();
    UserName contactAfter = app.contact().searchModContact(contactsAfter, contactBefore);
    assertThat(contactAfter.getGroups(), equalTo(contactBefore.getGroups().withAdded(emptyGroup)));
  }

}

