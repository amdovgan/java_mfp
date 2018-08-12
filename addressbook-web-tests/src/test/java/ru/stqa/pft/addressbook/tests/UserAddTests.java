package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;
import ru.stqa.pft.addressbook.model.UserDateBirth;
import ru.stqa.pft.addressbook.model.UserName;
import ru.stqa.pft.addressbook.model.UserPhoneEmail;

public class UserAddTests extends TestBase {


  @Test
  public void testUserAdd() {
    app.getNavigationHelper().gotoUserForm();
    app.getContactHelper().fillUserForm(new UserName("namefirst", "namemiddle", "namelast", "ololo"), new UserData("mycompany", "myaddress", "test.ru"), new UserPhoneEmail("495999999", "9999999999", "tester@test.ru"), new UserDateBirth("1917", "2017"));
    app.getContactHelper().submitUserCreation();
  }

}
