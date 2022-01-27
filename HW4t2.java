import java.util.*;
import java.util.Scanner;

public class HW4t2 {
    public static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {

        Person person1 = new Person(21200133, "Anna", "Lukyanova");
        Person person2 = new Person(20012134, "Daniel", "Ostrovsky");
        Person person3 = new Person(21200135, "Anton", "Ostrovsky");
        Person person4 = new Person(21213600, "Anna", "Tetelman");
        Person person5 = new Person(21213700, "Maria", "Krasnaya");

        ArrayList<Person> contacts = new ArrayList<>();
        contacts.add(person1);
        contacts.add(person2);
        contacts.add(person3);

        PhoneBook phoneBook = new PhoneBook(contacts);
        phoneBook.addContact(person4);

        System.out.print("Enter surname: ");
        String surname = in.nextLine();

        ArrayList<Person> results = phoneBook.getContactsBySurname(surname);
        for (Person searchResult : results) {
            System.out.println(searchResult.getName() + " " + searchResult.getSurname() + " " + searchResult.getNumber());
        }

    }


    public static class PhoneBook {
        private ArrayList<Person> contacts;

        public PhoneBook (ArrayList<Person> listOfContacts) {
            this.contacts = listOfContacts;
        }

        public Person addContact (Person personToAdd) {
           this.contacts.add(personToAdd);
           return personToAdd;
        }

        public ArrayList<Person> getContactsBySurname(String surnameToSearchBy) {
            ArrayList<Person> searchResults = new ArrayList<>();
            for (Person contact : this.contacts) {
                if (contact.getSurname().equals(surnameToSearchBy)) {
                    searchResults.add(contact);
                }
            }
            return searchResults;
        }

    }

    public static class Person {
        private Integer phonenumber;
        private String name;
        private String surname;

        public Person(Integer phonenumber, String name, String surname) {
            this.phonenumber = phonenumber;
            this.name = name;
            this.surname = surname;
        }

        public String getName() {
            return name;
        }

        public String getSurname() {
            return surname;
        }

        public Integer getNumber() {
            return phonenumber;
        }
    }
}




