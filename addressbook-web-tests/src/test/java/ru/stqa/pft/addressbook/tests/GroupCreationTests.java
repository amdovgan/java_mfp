package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;


public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() {
    app.gotoGroupePage();
    app.initGroupCreation();
    app.fillGroupForm(new GroupData("groupname", "goroupheader", "groupfooter"));
    app.submitGroupCreation();
    app.returnToGroupPage();
  }

}
