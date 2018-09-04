package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class UserPhoneEmail {
  private String homephone;
  private String mobilephone;
  private String workphone;
  private String email;
  private String email2;
  private String email3;

  public String getHomephone() {
    return homephone;
  }

  public String getMobilephone() {
    return mobilephone;
  }

  public String getWorkPhone() {
    return workphone;
  }

  public String getEmail() {
    return email;
  }

  public String getEmail2() {
    return email2;
  }

  public String getEmail3() {
    return email3;
  }

  public UserPhoneEmail withEmail2(String email2) {
    this.email2 = email2;
    return this;
  }

  public UserPhoneEmail withEmail3(String email3) {
    this.email3 = email3;
    return this;
  }

  public UserPhoneEmail withHomephone(String homephone) {
    this.homephone = homephone;
    return this;
  }

  public UserPhoneEmail withMobilephone(String mobilephone) {
    this.mobilephone = mobilephone;
    return this;
  }

  public UserPhoneEmail withEmail(String email) {
    this.email = email;
    return this;
  }

  public UserPhoneEmail withWorkPhone(String workphone) {
    this.workphone = workphone;
    return this;
  }

}
