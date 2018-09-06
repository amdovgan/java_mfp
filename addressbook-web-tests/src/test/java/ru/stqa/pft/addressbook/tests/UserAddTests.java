package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.*;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class UserAddTests extends TestBase {

  @Test //(enabled = false)
  public void testUserAdd() {
    app.goTo().homePage();
    Contacts before = app.contact().all();
    File photo = new File("src/test/resources/big.png");
    UserName nameofuser = new UserName().withFirstname("nameFirstCreate").withLastname("nameLastCreate").withPhoto(photo);
    UserPhoneEmail phoneEmailOfUser = new UserPhoneEmail().withHomephone("(495)999-99-99").withMobilephone("+7(999)999 99 99").withWorkPhone("9999999999")
            .withEmail("testerCreate@test.ru").withEmail2("tester2Create@test.ru").withEmail3("tester3Create@test.ru");
    UserDateBirth birthDateOfUser = new UserDateBirth ("1917", "2017");
    UserData dataOfUser = new UserData().withCompanyname("myCompanyCreate").withAddress("Moskva\nst. Mira\n33-18").withHomepageurl("testCreate.ru").withGroup("groupCreateName");
    app.contact().create(nameofuser, phoneEmailOfUser, birthDateOfUser, dataOfUser);
    assertThat(app.contact().count(),equalTo(before.size() + 1));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(
            before.withAdded(nameofuser.withId(after.stream().mapToInt((u) -> u.getId()).max().getAsInt()))));
  }

}
