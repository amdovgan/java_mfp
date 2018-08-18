package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupModificationTests extends TestBase {

  @Test
  public void testGroupModification(){
    app.getNavigationHelper().gotoGroupePage();
    if (! app.getGroupHelper().isThereAGroup()) {
      app.getGroupHelper().createGroup(new GroupData("groupName", null, null));
    }
    app.getGroupHelper().selectGroup();
    app.getGroupHelper().initModificationGroup();
    app.getGroupHelper().fillGroupForm(new GroupData("groupName", "groupModHeader", "groupModFooter"));
    app.getGroupHelper().submitGroupModification();
    app.getGroupHelper().returnToGroupPage();

  }
}
