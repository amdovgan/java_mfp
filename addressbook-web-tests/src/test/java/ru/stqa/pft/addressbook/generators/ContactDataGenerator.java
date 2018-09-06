package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
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
    save(contacts, new File(file));
  }

  private void save(List<UserName> contacts, File file) throws IOException {
    System.out.println(new File(".").getAbsolutePath());
    Writer writer = new FileWriter(file);
    for (UserName contact : contacts) {
      writer.write(String.format("%s;%s;%s;%s;%s;%s;%s\n", contact.getFirstname(), contact.getLastname()
              , contact.getHomephone(), contact.getMobilephone(), contact.getWorkPhone()
              , contact.getAddress(), contact.getEmail()));
    }
    writer.close();
  }

  private List<UserName> generateContacts(int count) {
    List<UserName> contacts = new ArrayList<UserName>();
    for (int i = 0; i < count; i++) {
      contacts.add(new UserName().withFirstname(String.format("Firstname %s", i)).withLastname(String.format("Lastname %s", i))
              .withHomePhone(String.format("(495)999-99-99 %s", i)).withMobilePhone(String.format("+7(999)999 99 99 %s", i))
              .withWorkPhone(String.format("9999999999 %s", i)).withAddress(String.format("Moskva st. Mira 33-18 %s", i))
              .withEmail(String.format("testerCreate@test.ru %s", i)));
    }
    return contacts;
  }
}
