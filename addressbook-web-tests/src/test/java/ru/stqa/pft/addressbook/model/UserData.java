package ru.stqa.pft.addressbook.model;

public class UserData {
  private final String companyname;
  private final String address;
  private final String homepageurl;

  public UserData(String companyname, String address, String homepageurl) {
    this.companyname = companyname;
    this.address = address;
    this.homepageurl = homepageurl;
  }

  public String getCompanyname() {
    return companyname;
  }

  public String getAddress() {
    return address;
  }

  public String getHomepageurl() {
    return homepageurl;
  }
}
