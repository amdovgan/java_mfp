package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class UserName {
  private int id;
  private final String firstname;
  private final String middlename;
  private final String lastname;
  private final String nickname;

  public UserName(int id, String firstname, String middlename, String lastname, String nickname) {
    this.id = id;
    this.firstname = firstname;
    this.middlename = middlename;
    this.lastname = lastname;
    this.nickname = nickname;
  }

  public UserName(String firstname, String middlename, String lastname, String nickname) {
    this.id = Integer.MAX_VALUE;
    this.firstname = firstname;
    this.middlename = middlename;
    this.lastname = lastname;
    this.nickname = nickname;
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

  public void setId(int id) {
    this.id = id;
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
    return Objects.equals(firstname, userName.firstname) &&
            Objects.equals(middlename, userName.middlename) &&
            Objects.equals(lastname, userName.lastname);
  }

  @Override
  public int hashCode() {
    return Objects.hash(firstname, middlename, lastname);
  }

}
