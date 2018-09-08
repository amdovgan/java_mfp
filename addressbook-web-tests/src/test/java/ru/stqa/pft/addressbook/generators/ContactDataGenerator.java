package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import ru.stqa.pft.addressbook.model.UserName;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {

  @Parameter(names = "-c", description = "Contact count")
  public int count;

  @Parameter(names = "-f", description = "Target file")
  public String file;

  @Parameter(names = "-d", description = "Data format")
  public String format;

  public static void main(String[] args) throws IOException {
    ContactDataGenerator generator = new ContactDataGenerator();
    JCommander jCommander = new JCommander(generator);
    try {
      jCommander.parse(args);
    } catch (ParameterException ex) {
      jCommander.usage();
      return;
    }
    generator.run();
  }

  private void run() throws IOException {
    List<UserName> contacts = generateContacts(count);
    if (format.equals("csv")) {
      saveAsCsv(contacts, new File(file));
    } else if (format.equals("xml")) {
      saveAsXml(contacts, new File(file));
    } else if (format.equals("json")) {
      saveAsjson(contacts, new File(file));
    } else {
      System.out.println("Unrecognized format " + format);
    }
  }

  private void saveAsjson(List<UserName> contacts, File file) throws IOException {
    Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
    String json = gson.toJson(contacts);
    Writer writer = new FileWriter(file);
    writer.write(json);
    writer.close();

  }

  private void saveAsXml(List<UserName> contacts, File file) throws IOException {
    XStream xstream = new XStream();
    xstream.processAnnotations(UserName.class);
    String xml = xstream.toXML(contacts);
    Writer writer = new FileWriter(file);
    writer.write(xml);
    writer.close();
  }

  private void saveAsCsv(List<UserName> contacts, File file) throws IOException {
    System.out.println(new File(".").getAbsolutePath());
    Writer writer = new FileWriter(file);
    for (UserName contact : contacts) {
      writer.write(String.format("%s;%s;%s;%s;%s;%s;%s;%s;%s\n", contact.getFirstname(), contact.getLastname()
              , contact.getHomephone(), contact.getMobilephone(), contact.getWorkPhone()
              , contact.getAddress(), contact.getEmail(), contact.getEmail2(), contact.getEmail3()));
    }
    writer.close();
  }

  private List<UserName> generateContacts(int count) {
    List<UserName> contacts = new ArrayList<UserName>();
    for (int i = 0; i < count; i++) {
      contacts.add(new UserName().withFirstname(String.format("Firstname %s", i)).withLastname(String.format("Lastname %s", i))
              /*.withPhoto(File.path("", i))*/
              .withHomePhone(String.format("(495)999-99-99 %s", i)).withMobilePhone(String.format("+7(999)999 99 99 %s", i))
              .withWorkPhone(String.format("9999999999 %s", i)).withAddress(String.format("Moskva\nst. Mira\n33-18 %s", i))
              .withEmail(String.format("testerCreate@test.ru %s", i)).withEmail2(String.format("testerCreate2@test.ru %s", i))
              .withEmail3(String.format("testerCreate3@test.ru %s", i)));
    }
    return contacts;
  }
}
