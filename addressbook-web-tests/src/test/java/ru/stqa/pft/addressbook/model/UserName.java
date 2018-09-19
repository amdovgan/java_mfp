package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@XStreamAlias("contact")
@Entity
@Table(name = "addressbook")
public class UserName {

  @XStreamOmitField
  @Id
  @Column(name = "id")
  private int id = Integer.MAX_VALUE;

  @Expose
  @Column(name = "firstname")
  private String firstname;

  @Expose
  @Column(name = "middlename")
  private String middlename;

  @Expose
  @Column(name = "lastname")
  private String lastname;

  @Expose
  @Column(name = "nickname")
  private String nickname;

  @Expose
  @Column(name = "address")
  @Type(type = "text")
  private String address;

  @Expose
  @Column(name = "home")
  @Type(type = "text")
  private String homephone;

  @Expose
  @Column(name = "mobile")
  @Type(type = "text")
  private String mobilephone;

  @Expose
  @Column(name = "work")
  @Type(type = "text")
  private String workphone;

  @Expose
  @Transient
  private String allPhones;

  @Expose
  @Column(name = "email")
  @Type(type = "text")
  private String email;

  @Expose
  @Column(name = "email2")
  @Type(type = "text")
  private String email2;

  @Expose
  @Column(name = "email3")
  @Type(type = "text")
  private String email3;

  @Expose
  @Transient
  private String allEmails;

  @Expose
  @Column(name = "photo")
  @Type(type = "text")
  private String photo;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "address_in_groups",
          joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "group_id"))
  private Set<GroupData> groups = new HashSet<GroupData>();

  public String getWorkphone() {
    return workphone;
  }

  public Groups getGroups() {
    return new Groups(groups);
  }

  public File getPhoto() {
    if (photo != null) {
      return new File(photo);
    } else {
      return null;
    }
  }

  public UserName withPhoto(File photo) {
    this.photo = photo.getPath();
    return this;
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

  public UserName inGroup(GroupData group) {
    groups.add(group);
    return this;
  }

  @Override
  public String toString() {
    return "UserName{" +
            "id=" + id +
            ", firstname='" + firstname + '\'' +
            ", middlename='" + middlename + '\'' +
            ", lastname='" + lastname + '\'' +
            ", nickname='" + nickname + '\'' +
            ", address='" + address + '\'' +
            ", homephone='" + homephone + '\'' +
            ", mobilephone='" + mobilephone + '\'' +
            ", workphone='" + workphone + '\'' +
            ", email='" + email + '\'' +
            ", email2='" + email2 + '\'' +
            ", email3='" + email3 + '\'' +
            ", photo='" + photo + '\'' +
            ", groups=" + groups +
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
            Objects.equals(nickname, userName.nickname) &&
            Objects.equals(address, userName.address) &&
            Objects.equals(homephone, userName.homephone) &&
            Objects.equals(mobilephone, userName.mobilephone) &&
            Objects.equals(workphone, userName.workphone) &&
            Objects.equals(email, userName.email) &&
            Objects.equals(email2, userName.email2) &&
            Objects.equals(email3, userName.email3) &&
            Objects.equals(photo, userName.photo) &&
            Objects.equals(groups, userName.groups);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, firstname, middlename, lastname, nickname, address, homephone, mobilephone, workphone, email, email2, email3, photo, groups);
  }

}
