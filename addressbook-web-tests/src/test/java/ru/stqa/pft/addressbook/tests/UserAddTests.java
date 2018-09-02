package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.*;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class UserAddTests extends TestBase {

  @Test //(enabled = false)
  public void testUserAdd() {
    app.goTo().homePage();
    Contacts before = app.contact().all();
    UserName nameofuser = new UserName().withFirstname("nameFirstCreate").withLastname("nameLastCreate");
    UserPhoneEmail phoneEmailOfUser = new UserPhoneEmail().withHomephone("495999999").withMobilephone("9999999999").withEmail("testerCreate@test.ru");
    UserDateBirth birthDateOfUser = new UserDateBirth ("1917", "2017");
    UserData dataOfUser = new UserData().withCompanyname("myCompanyCreate").withAddress("myAddressCreate").withHomepageurl("testCreate.ru").withGroup("groupCreateName");
    app.contact().create(nameofuser, phoneEmailOfUser, birthDateOfUser, dataOfUser);
    Contacts after = app.contact().all();
    assertThat(after.size(),equalTo(before.size() + 1));
    assertThat(after, equalTo(
            before.withAdded(nameofuser.withId(after.stream().mapToInt((u) -> u.getId()).max().getAsInt()))));
  }

}
