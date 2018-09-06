package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.addressbook.model.UserName;
import ru.stqa.pft.addressbook.model.Contacts;

import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.*;

import java.util.List;

public class ContactHelper extends HelperBase {

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void submitUserCreation() {
    click(By.name("submit"));
  }

  public void fillUserForm(UserName userName, UserPhoneEmail userPhoneEmail, UserDateBirth userDateBirth) {
    type(By.name("firstname"), userName.getFirstname());
    type(By.name("middlename"), userName.getMiddlename());
    type(By.name("lastname"), userName.getLastname());
    type(By.name("nickname"), userName.getNickname());
    attach(By.name("photo"), userName.getPhoto());
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

  public void create(UserName Name, UserPhoneEmail PhoneEmail, UserDateBirth DateBirth, UserData Data) {
    gotoUserForm();
    fillUserForm(Name, PhoneEmail, DateBirth);
    fillUserData(Data, true);
    submitUserCreation();
    contactCache = null;
    returnToHomePage();
  }

  public void modify(UserName nameOfUser, UserPhoneEmail phoneEmailOfUser, UserDateBirth birthDateOfUser, UserData dataOfUser) {
    selectUserById(nameOfUser.getId());
    initUserModificationById(nameOfUser.getId());
    //initUserModification();
    fillUserForm(nameOfUser, phoneEmailOfUser,birthDateOfUser);
    fillUserData(dataOfUser,false);
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
      usenames.add(new UserName().withId(id).withFirstname(firstname).withLastname(lastname).withAddress(address).withAllPhones(allPhones).withAllEmails(allEmails));
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

}
