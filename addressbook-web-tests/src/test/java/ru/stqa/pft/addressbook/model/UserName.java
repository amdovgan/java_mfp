package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class UserName {
  private int id = Integer.MAX_VALUE;
  private String firstname;
  private String middlename;
  private String lastname;
  private String nickname;

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

  public UserName withId(int id) {
    this.id = id;
    return this;
  }

  public UserName withFirstname(String firstname) {
    this.firstname = firstname;
    return this;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, firstname, middlename, lastname, nickname);
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

}
