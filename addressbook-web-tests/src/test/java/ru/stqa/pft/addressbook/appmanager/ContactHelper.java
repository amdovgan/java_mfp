package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.stqa.pft.addressbook.model.UserData;
import ru.stqa.pft.addressbook.model.UserDateBirth;
import ru.stqa.pft.addressbook.model.UserName;
import ru.stqa.pft.addressbook.model.UserPhoneEmail;

public class ContactHelper extends HelperBase {

  public ContactHelper(FirefoxDriver wd) {
    super(wd);
  }

  public void submitUserCreation() {
    click(By.name("submit"));
  }

  public void fillUserForm(UserName userName, UserData userData, UserPhoneEmail userPhoneEmail, UserDateBirth userDateBirth) {
    type(By.name("firstname"), userName.getFirstname());
    type(By.name("middlename"), userName.getMiddlename());
    type(By.name("lastname"), userName.getLastname());
    type(By.name("nickname"), userName.getNickname());
    type(By.name("company"), userData.getCompanyname());
    type(By.name("address"), userData.getAddress());
    type(By.name("home"), userPhoneEmail.getHomephone());
    type(By.name("mobile"), userPhoneEmail.getMobilephone());
    type(By.name("email"), userPhoneEmail.getEmail());
    type(By.name("homepage"), userData.getHomepageurl());
    click(By.xpath("//div[@id='content']/form/select[1]//option[1]"));
    click(By.xpath("//div[@id='content']/form/select[1]//option[27]"));
    click(By.xpath("//div[@id='content']/form/select[2]//option[11]"));
    type(By.name("byear"), userDateBirth.getYearofbirth());
    click(By.xpath("//div[@id='content']/form/select[3]//option[3]"));
    click(By.xpath("//div[@id='content']/form/select[4]//option[2]"));
    type(By.name("ayear"), userDateBirth.getYearofholiday());
    click(By.xpath("//div[@id='content']/form/select[5]//option[5]"));
  }

  public void deleteSelectedUsers() {
      click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
  }

  public void selectUser() {
      click(By.name("selected[]"));
  }
}
