package ru.stqa.pft.addressbook.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

import java.io.File;
import java.util.Objects;

@XStreamAlias("contact")
public class UserName {

  @XStreamOmitField
  private int id = Integer.MAX_VALUE;
  private String firstname;
  private String middlename;
  private String lastname;
  private String nickname;
  private String address;
  private String homephone;
  private String mobilephone;
  private String workphone;
  private String allPhones;
  private String email;
  private String email2;
  private String email3;
  private String allEmails;
  private File photo;

  public File getPhoto() {
    return photo;
  }

  public String getFirstname() {
    return firstname;
  }

  public String getMiddlename() {
    return middlename;
  }

  public String getLastname() {
    return lastname;
  }

  public String getNickname() {
    return nickname;
  }

  public int getId() {
    return id;
  }

  public String getAddress() {
    return address;
  }

  public String getHomephone() {
    return homephone;
  }

  public String getMobilephone() {
    return mobilephone;
  }

  public String getWorkPhone() {
    return workphone;
  }

  public String getAllPhones() {
    return allPhones;
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

  public String getAllEmails() {
    return allEmails;
  }

  public UserName withId(int id) {
    this.id = id;
    return this;
  }

  public UserName withFirstname(String firstname) {
    this.firstname = firstname;
    return this;
  }

  public UserName withMiddlename(String middlename) {
    this.middlename = middlename;
    return this;
  }

  public UserName withLastname(String lastname) {
    this.lastname = lastname;
    return this;
  }

  public UserName withNickname(String nickname) {
    this.nickname = nickname;
    return this;
  }

  public UserName withPhoto(File photo) {
    this.photo = photo;
    return this;
  }

  public UserName withAddress (String address) {
    this.address = address;
    return this;
  }

  public UserName withHomePhone(String homephone) {
    this.homephone = homephone;
    return this;
  }

  public UserName withMobilePhone(String mobilephone) {
    this.mobilephone = mobilephone;
    return this;
  }

  public UserName withWorkPhone(String workphone) {
    this.workphone = workphone;
    return this;
  }

  public UserName withAllPhones(String allPhones) {
    this.allPhones = allPhones;
    return this;
  }

  public UserName withEmail(String email) {
    this.email = email;
    return this;
  }

  public UserName withEmail2(String email2) {
    this.email2 = email2;
    return this;
  }

  public UserName withEmail3(String email3) {
    this.email3 = email3;
    return this;
  }

  public UserName withAllEmails(String allEmails) {
    this.allEmails = allEmails;
    return this;
  }

  @Override
  public String toString() {
    return "UserName{" +
            "id='" + id + '\'' +
            ", firstname='" + firstname + '\'' +
            ", middlename='" + middlename + '\'' +
            ", lastname='" + lastname + '\'' +
            ", nickname='" + nickname + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    UserName userName = (UserName) o;
    return id == userName.id &&
            Objects.equals(firstname, userName.firstname) &&
            Objects.equals(middlename, userName.middlename) &&
            Objects.equals(lastname, userName.lastname) &&
            Objects.equals(nickname, userName.nickname);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, firstname, middlename, lastname, nickname);
  }

}
