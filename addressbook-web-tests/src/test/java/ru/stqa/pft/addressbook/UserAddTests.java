package ru.stqa.pft.addressbook;

import org.testng.annotations.Test;

public class UserAddTests extends TestBase {


  @Test
  public void testUserAdd() {
    gotoUserForm();
    fillUserForm(new UserName("namefirst", "namemiddle", "namelast", "ololo"), new UserData("mycompany", "myaddress", "test.ru"), new UserPhoneEmail("495999999", "9999999999", "tester@test.ru"), new UserDateBirth("1917", "2017"));
    submitUserCreation();
  }

}
