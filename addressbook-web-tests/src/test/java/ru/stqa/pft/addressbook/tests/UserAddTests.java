package ru.stqa.pft.addressbook.tests;

import com.thoughtworks.xstream.XStream;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class UserAddTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> validContacts() throws IOException {
    BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.xml")));
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

  @Test (dataProvider = "validContacts")
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
