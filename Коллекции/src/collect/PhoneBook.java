package collect;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class PhoneBook {

    private HashMap<String, List<String>> phoneBook;

    public PhoneBook() {
        this.phoneBook = new HashMap<String, List<String>>();
    }

    public void addToPhoneBook(String name, String phone) {
        List<String> numbers = new ArrayList<>();

        if (!phoneBook.containsKey(name)) {
            numbers.add(phone);
            phoneBook.put(name, numbers);
        }

        else {
            numbers = phoneBook.get(name);
            numbers.add(phone);
            phoneBook.put(name, numbers);
        }

    }


    public void getFromPhoneBook(String name) {
        System.out.println("Номер телефона " + name + ": " + phoneBook.getOrDefault(name, Collections.singletonList("NotFound")));
    }
}
