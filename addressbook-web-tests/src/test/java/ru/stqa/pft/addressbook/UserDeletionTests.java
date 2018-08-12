package ru.stqa.pft.addressbook;

import org.testng.annotations.Test;

public class UserDeletionTests extends TestBase {

  @Test
  public void testUserDeletion() {
    selectUser();
    deleteSelectedUsers();

  }

}
