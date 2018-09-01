package ru.stqa.pft.addressbook.model;

public class UserPhoneEmail {
  private String homephone;
  private String mobilephone;
  private String email;

  public String getHomephone() {
    return homephone;
  }

  public String getMobilephone() {
    return mobilephone;
  }

  public String getEmail() {
    return email;
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

}
