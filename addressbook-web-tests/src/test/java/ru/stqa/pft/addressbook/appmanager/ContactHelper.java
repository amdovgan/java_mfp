package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.addressbook.model.UserName;
import ru.stqa.pft.addressbook.model.Contacts;

import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ContactHelper extends HelperBase {

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void submitUserCreation() {
    click(By.name("submit"));
  }

  public void fillUserForm(UserName userName, boolean creation) {
    type(By.name("firstname"), userName.getFirstname());
    type(By.name("middlename"), userName.getMiddlename());
    type(By.name("lastname"), userName.getLastname());
    type(By.name("nickname"), userName.getNickname());
    //attach(By.name("photo"), userName.getPhoto());
    type(By.name("home"), userName.getHomephone());
    type(By.name("mobile"), userName.getMobilephone());
    type(By.name("work"), userName.getWorkphone());
    type(By.name("address"), userName.getAddress());
    type(By.name("email"), userName.getEmail());
    type(By.name("email2"), userName.getEmail2());
    type(By.name("email3"), userName.getEmail3());
    if (creation) {
      if (userName.getGroups().size() > 0) {
        Assert.assertTrue(userName.getGroups().size() == 1);
        new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(userName.getGroups().iterator().next().getName());
      }
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
/*
    click(By.xpath("//div[@id='content']/form/select[1]//option[1]"));
    click(By.xpath("//div[@id='content']/form/select[1]//option[27]"));
    click(By.xpath("//div[@id='content']/form/select[2]//option[11]"));
    type(By.name("byear"), userDateBirth.getYearofbirth());
    click(By.xpath("//div[@id='content']/form/select[3]//option[3]"));
    click(By.xpath("//div[@id='content']/form/select[4]//option[2]"));
    type(By.name("ayear"), userDateBirth.getYearofholiday());
*/
  }

  public void fillUserPhoneEmailDateBirth(UserPhoneEmail userPhoneEmail, UserDateBirth userDateBirth) {
    type(By.name("home"), userPhoneEmail.getHomephone());
    type(By.name("mobile"), userPhoneEmail.getMobilephone());
    type(By.name("work"), userPhoneEmail.getWorkPhone());
    type(By.name("email"), userPhoneEmail.getEmail());
    type(By.name("email2"), userPhoneEmail.getEmail2());
    type(By.name("email3"), userPhoneEmail.getEmail3());
    click(By.xpath("//div[@id='content']/form/select[1]//option[1]"));
    click(By.xpath("//div[@id='content']/form/select[1]//option[27]"));
    click(By.xpath("//div[@id='content']/form/select[2]//option[11]"));
    type(By.name("byear"), userDateBirth.getYearofbirth());
    click(By.xpath("//div[@id='content']/form/select[3]//option[3]"));
    click(By.xpath("//div[@id='content']/form/select[4]//option[2]"));
    type(By.name("ayear"), userDateBirth.getYearofholiday());
  }

  public void fillUserData (UserData userData, boolean creation) {
    type(By.name("company"), userData.getCompanyname());
    type(By.name("address"), userData.getAddress());
    type(By.name("homepage"), userData.getHomepageurl());
    if (creation) {
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(userData.getGroup());
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }

  public void deleteSelectedUsers() {
    click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
    wd.switchTo().alert().accept();
  }

  public void selectUserById(int id) {
    wd.findElement(By.cssSelector("input[value='" + id+ "']")).click();
    //wd.findElements(By.name("selected[]")).get(index).click();
  }

  public void submitUserModification() {
    click(By.xpath("//div[@id='content']/form[1]/input[22]"));
  }

  public void returnToHomePage() {
    click(By.linkText("home"));
  }

  public void gotoUserForm() {
    click(By.linkText("add new"));
  }

  public void create(UserName Name /*UserPhoneEmail PhoneEmail, UserDateBirth DateBirth, UserData Data*/) {
    /*File photo = new File("src/test/resources/big.png");
    UserName Name = new UserName().withFirstname(Firstname).withLastname(Lastname).withPhoto(photo);*/

    gotoUserForm();
    fillUserForm(Name, true);
    //fillUserPhoneEmailDateBirth (PhoneEmail, DateBirth);
    //fillUserForm(Name, PhoneEmail, DateBirth);
    //fillUserData(Data, true);
    submitUserCreation();
    contactCache = null;
    returnToHomePage();
  }

  public void modify(UserName nameOfUser) { /*UserPhoneEmail phoneEmailOfUser, UserDateBirth birthDateOfUser, UserData dataOfUser*/
    selectUserById(nameOfUser.getId());
    initUserModificationById(nameOfUser.getId());
    //initUserModification();
    fillUserForm(nameOfUser, true); /*phoneEmailOfUser,birthDateOfUser*/
    //fillUserPhoneEmailDateBirth (phoneEmailOfUser,birthDateOfUser);
    //fillUserData(dataOfUser,false);
    submitUserModification();
    contactCache = null;
    returnToHomePage();
  }

  public void delete(UserName user) {
    selectUserById(user.getId());
    deleteSelectedUsers();
    contactCache = null;
    returnToHomePage();
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public int count() {
    return wd.findElements(By.name("selected[]")).size();
  }

  private Contacts contactCache = null;

  public Contacts all() {
    Contacts usenames = new Contacts();
    List<WebElement> elements = wd.findElements(By.name("entry"));
    for (WebElement element : elements) {
      List<WebElement> cells = element.findElements(By.xpath(".//td"));
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      String lastname = cells.get(1).getText();
      String firstname = cells.get(2).getText();
      String address = cells.get(3).getText();
      String allEmails = cells.get(4).getText();
      String allPhones = cells.get(5).getText();
      usenames.add(new UserName().withId(id).withFirstname(firstname).withLastname(lastname)
              .withAddress(address).withAllPhones(allPhones).withAllEmails(allEmails));
    }
    return usenames;
  }

  public UserName infoFromEditForm(UserName contact) {
    initUserModificationById(contact.getId());
    //initContactModificationById(contact.getId());
    String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
    String address = wd.findElement(By.name("address")).getText();
    String home = wd.findElement(By.name("home")).getAttribute("value");
    String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
    String work = wd.findElement(By.name("work")).getAttribute("value");
    String email = wd.findElement(By.name("email")).getAttribute("value");
    String email2 = wd.findElement(By.name("email2")).getAttribute("value");
    String email3 = wd.findElement(By.name("email3")).getAttribute("value");
    wd.navigate().back();
    return  new UserName().withId(contact.getId()).withFirstname(firstname).withLastname(lastname)
            .withAddress(address).withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work)
            .withEmail(email).withEmail2(email2).withEmail3(email3);
  }

  public void initContactModificationById(int id) {
    WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[value='%s']", id)));
    WebElement row = checkbox.findElement(By.xpath("./../.."));
    List<WebElement> cells = row.findElements(By.tagName("td"));
    cells.get(7).findElement(By.tagName("a")).click();

    //wd.findElements(By.xpath(String.format("//input[@value='%s']/../../td[8]/a", id))).click();
    //wd.findElements(By.xpath(String.format("//tr[.//input[@value='%s']]/td[8]/a", id))).click();
    //wd.findElements(By.cssSelector(String.format("a[href='edit.php?id=%s']", id))).click();
  }

  public void initUserModificationById(int id) {
    //click(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[8]/a/img"));
    //wd.findElement(By.cssSelector("a[href='edit.php?id=" + id+ "']")).click();
    wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", id))).click();
    //wd.findElement(By.xpath("//a[@href='edit.php?id=" + id+ "']/img")).click();
    //click(By.xpath("//img[@title='Edit']"));
  }

  public void addGroupToContact(UserName modifiedUser) {
    selectUserById(modifiedUser.getId());
    //new Select(wd.findElement(By.name("to_group"))).selectByIndex(modifiedUser.getGroups().withAdded());
    //new Select(wd.findElement(By.name("to_group"))).selectByVisibleText(modifiedUser.getGroups().iterator().next().getId());
    click(By.cssSelector("input[value = 'Add to']"));
/*
    if (modifiedUser.getGroups().size() > 0) {
      //Assert.assertTrue(modifiedUser.getGroups().size() == 1);
    new Select(wd.findElement(By.name("to_group"))).selectByVisibleText(modifiedUser.getGroups().iterator().next().getName());
    } else {
      submitAddGroup();
    }
    //new Select(wd.findElement(By.name("to_group"))).selectByVisibleText(modifiedUser.getGroups().iterator().next().getName());
    //new Select(wd.findElement(By.name("to_group"))).selectByIndex(modifiedUser.getGroups().iterator().next().getId());
*/
    returnToHomePage();
  }

  private void submitAddGroup() {
    click(By.cssSelector("input[value = 'Add to']"));
  }

  public List<String> getUIGroups() {
    Select select = new Select(wd.findElement(By.name("to_group")));
    List<WebElement> list = select.getOptions();
    List<String> groupsName = new ArrayList<>();
    for (WebElement element:list){
      groupsName.add(element.getAttribute("value"));
    }
    return groupsName;
  }

  public GroupData searchEmptyGroup(UserName contactBefore, Groups groupsBefore) {
    GroupData emptyGroup = null;
    Iterator<GroupData> iter = groupsBefore.iterator();
    while (iter.hasNext()) {
      GroupData grp = iter.next();
      if (!contactBefore.getGroups().contains(grp)) {
        emptyGroup = grp;
        break;
      }
    }
    return emptyGroup;
  }

  public void addToGroup(UserName contact) {
    selectUserById(contact.getId());
    new Select(wd.findElement(By.name("to_group"))).selectByVisibleText(contact.getGroups().iterator().next().getName());
    click(By.cssSelector("input[value = 'Add to']"));
  }

  public UserName searchModContact(Contacts contactsAfter, UserName contactBefore) {
    UserName contactAfter = null;
    Iterator<UserName> iter = contactsAfter.iterator();
    while (iter.hasNext()){
      UserName cnt = iter.next();
      if (cnt.getId() == contactBefore.getId()) {
        contactAfter = cnt;
        break;
      }
    }
    return contactAfter;
  }

  public void deleteFromGroup(UserName contact) {
    Select select = new Select(wd.findElement(By.name("group")));
    select.selectByVisibleText(contact.getGroups().iterator().next().getName());
    selectUserById(contact.getId());
    click(By.name("remove"));
  }
}
