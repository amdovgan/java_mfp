package ru.stqa.pft.addressbook;

public class UserDateBirth {
  private final String yearofbirth;
  private final String yearofholiday;

  public UserDateBirth(String yearofbirth, String yearofholiday) {
    this.yearofbirth = yearofbirth;
    this.yearofholiday = yearofholiday;
  }

  public String getYearofbirth() {
    return yearofbirth;
  }

  public String getYearofholiday() {
    return yearofholiday;
  }
}
