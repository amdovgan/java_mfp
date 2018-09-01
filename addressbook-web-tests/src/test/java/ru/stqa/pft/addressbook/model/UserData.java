package ru.stqa.pft.addressbook.model;

public class UserData {
  private String companyname;
  private String address;
  private String homepageurl;
  private String group;

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

  public UserData withCompanyname(String companyname) {
    this.companyname = companyname;
    return this;
  }

  public UserData withAddress(String address) {
    this.address = address;
    return this;
  }

  public UserData withHomepageurl(String homepageurl) {
    this.homepageurl = homepageurl;
    return this;
  }

  public UserData withGroup(String group) {
    this.group = group;
    return this;
  }

}
