package ru.stqa.pft.addressbook;

import org.testng.annotations.Test;


public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() {
    gotoGroupePage();
    initGroupCreation();
    fillGroupForm(new GroupData("groupname", "goroupheader", "groupfooter"));
    submitGroupCreation();
    returnToGroupPage();
  }

}
