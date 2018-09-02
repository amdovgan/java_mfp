package ru.stqa.pft.addressbook.model;

public class UserData {
  private final String companyname;
  private final String address;
  private final String homepageurl;
  private String group;

  public UserData(String companyname, String address, String homepageurl, String group) {
    this.companyname = companyname;
    this.address = address;
    this.homepageurl = homepageurl;
    this.group = group;
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

  public String getGroup() {
    return group;
  }
}
