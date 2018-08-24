package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class UserName {
  private final String firstname;
  private final String middlename;
  private final String lastname;
  private final String nickname;

  public UserName(String firstname, String middlename, String lastname, String nickname) {
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

  @Override
  public String toString() {
    return "UserName{" +
            "firstname='" + firstname + '\'' +
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
            Objects.equals(lastname, userName.lastname) &&
            Objects.equals(nickname, userName.nickname);
  }

  @Override
  public int hashCode() {
    return Objects.hash(firstname, middlename, lastname, nickname);
  }
}
