import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

class ContactManager {
    private List<Contact> contacts;
    private final String FILE_NAME = "contacts.txt";

    public ContactManager() {
        contacts = new ArrayList<>();
        loadContacts();
    }

    public void addContact(String name, String phoneNumber, String email) {
        contacts.add(new Contact(name, phoneNumber, email));
        saveContacts();
    }

    public void viewContacts() {
        for (Contact contact : contacts) {
            System.out.println(contact);
        }
    }

    public void editContact(int index, String phoneNumber, String email) {
        Contact contact = contacts.get(index);
        contact.setPhoneNumber(phoneNumber);
        contact.setEmail(email);
        saveContacts();
    }

    public void deleteContact(int index) {
        contacts.remove(index);
        saveContacts();
    }

    private void loadContacts() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                contacts.add(new Contact(parts[0], parts[1], parts[2]));
            }
        } catch (IOException e) {
            // File may not exist yet, ignore
        }
    }

    private void saveContacts() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Contact contact : contacts) {
                pw.println(contact.getName() + "," + contact.getPhoneNumber() + "," + contact.getEmail());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
