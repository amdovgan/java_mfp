package ru.stqa.pft.addressbook.model;

import com.google.common.collect.ForwardingSet;

import java.util.HashSet;
import java.util.Set;

public class Contacts extends ForwardingSet<UserName> {

  private Set<UserName> delegate;

  public Contacts(Contacts contacts) {
    this.delegate = new HashSet<UserName>(contacts.delegate);
  }

  public Contacts() {
    this.delegate = new HashSet<UserName>();
  }

  @Override
  protected Set<UserName> delegate() {
    return delegate;
  }

  public Contacts withAdded(UserName contact) {
    Contacts contacts = new Contacts(this);
    contacts.add(contact);
    return contacts;
  }

  public Contacts without(UserName contact) {
    Contacts contacts = new Contacts(this);
    contacts.remove(contact);
    return contacts;
  }

  public Contacts withModified(UserName contact, UserName modifiedcontact) {
    Contacts contacts = new Contacts(this);
    contacts.remove(contact);
    contacts.add(modifiedcontact);
    return contacts;
  }

}
