package ru.stqa.pft.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.*;

import java.io.*;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class UserAddTests extends TestBase {

  private final Properties properties;

  public UserAddTests() {
    properties = new Properties();
  }

  @DataProvider
  public Iterator<Object[]> validContactsFromXml() throws IOException {
    String datafile = System.getProperty("datafile", "filecontacts");
    properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", datafile))));
    try(BufferedReader reader = new BufferedReader(new FileReader(new File(properties.getProperty("data.fileContactXmlUrl"))))) {
      String xml = "";
      String line = reader.readLine();
      while (line != null) {
        xml += line;
        line = reader.readLine();
      }
      XStream xstream = new XStream();
      xstream.processAnnotations(UserName.class);
      List<UserName> contacts = (List<UserName>) xstream.fromXML(xml);
      return contacts.stream().map((u) -> new Object[] {u}).collect(Collectors.toList()).iterator();
    }
  }

  @DataProvider
  public Iterator<Object[]> validContactsFromJson() throws IOException {
    String datafile = System.getProperty("datafile", "filecontacts");
    properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", datafile))));
    try (BufferedReader reader = new BufferedReader(new FileReader(new File(properties.getProperty("data.fileContactJsonUrl"))))) {
      String json = "";
      String line = reader.readLine();
      while (line != null) {
        json += line;
        line = reader.readLine();
      }
      Gson gson = new Gson();
      List<UserName> contacts = gson.fromJson(json, new TypeToken<List<UserName>>() {
      }.getType());
      return contacts.stream().map((u) -> new Object[]{u}).collect(Collectors.toList()).iterator();
    }
  }


  @Test (dataProvider = "validContactsFromXml")
  public void testUserAdd(UserName nameofuser) {
/*    File photo = new File("src/test/resources/big.png");
    //UserName nameofuser = new UserName().withFirstname(Firstname).withLastname(Lastname).withPhoto(photo);

    UserPhoneEmail phoneEmailOfUser = new UserPhoneEmail().withHomephone("(495)999-99-99").withMobilephone("+7(999)999 99 99").withWorkPhone("9999999999")
            .withEmail("testerCreate@test.ru").withEmail2("tester2Create@test.ru").withEmail3("tester3Create@test.ru");
    UserDateBirth birthDateOfUser = new UserDateBirth("1917", "2017");
    UserData dataOfUser = new UserData().withCompanyname("myCompanyCreate").withAddress("Moskva\nst. Mira\n33-18").withHomepageurl("testCreate.ru").withGroup("groupCreateName");
*/
    app.goTo().homePage();
    Contacts before = app.contact().all();
    app.contact().create(nameofuser); /*phoneEmailOfUser, birthDateOfUser, dataOfUser*/
    assertThat(app.contact().count(), equalTo(before.size() + 1));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(
            before.withAdded(nameofuser.withId(after.stream().mapToInt((u) -> u.getId()).max().getAsInt()))));
  }

}
