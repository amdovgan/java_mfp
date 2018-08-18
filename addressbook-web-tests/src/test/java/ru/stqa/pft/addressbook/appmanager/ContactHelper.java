package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.UserData;
import ru.stqa.pft.addressbook.model.UserDateBirth;
import ru.stqa.pft.addressbook.model.UserName;
import ru.stqa.pft.addressbook.model.UserPhoneEmail;

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
    type(By.name("home"), userPhoneEmail.getHomephone());
    type(By.name("mobile"), userPhoneEmail.getMobilephone());
    type(By.name("email"), userPhoneEmail.getEmail());
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

  public void selectUser() {
    click(By.name("selected[]"));
  }

  public void initUserModification() {
    //click(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[8]/a/img"));
    click(By.xpath("//img[@title='Edit']"));
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

  public void createContact(UserName Name, UserPhoneEmail PhoneEmail, UserDateBirth DateBirth, UserData Data) {
    gotoUserForm();
    fillUserForm(Name, PhoneEmail, DateBirth);
    fillUserData(Data, true);
    submitUserCreation();
    returnToHomePage();
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }
}
