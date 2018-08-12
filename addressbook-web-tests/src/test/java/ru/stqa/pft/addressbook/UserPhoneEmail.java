package ru.stqa.pft.addressbook;

public class UserPhoneEmail {
  private final String homephone;
  private final String mobilephone;
  private final String email;

  public UserPhoneEmail(String homephone, String mobilephone, String email) {
    this.homephone = homephone;
    this.mobilephone = mobilephone;
    this.email = email;
  }

  public String getHomephone() {
    return homephone;
  }

  public String getMobilephone() {
    return mobilephone;
  }

  public String getEmail() {
    return email;
  }
}
