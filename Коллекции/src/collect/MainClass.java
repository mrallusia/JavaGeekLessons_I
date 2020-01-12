package collect;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public class MainClass {
    public static void main(String[] args) {
/**
        //Создадим список слов
        ArrayList<String> words = new ArrayList<>();
        words.add("Яблоко");
        words.add("Яблоко");
        words.add("Груша");
        words.add("Груша");
        words.add("Груша");
        words.add("Апельсин");
        words.add("Апельсин");
        words.add("Апельсин");
        words.add("Груша");
        words.add("Апельсин");
        words.add("Апельсин");
        words.add("Яблоко");
        words.add("Яблоко");
        System.out.println(words);

        //Преобразуем в уникальные значения
        HashSet<String> unicWords = new HashSet<>(words);
        System.out.println(unicWords);

        //Подсчёт сколько каких слов
        int count = 0; //счётчик
        Iterator<String> iterUnicWords = unicWords.iterator();
        while (iterUnicWords.hasNext()) {
            String strUnic = iterUnicWords.next();

            Iterator<String> iterWords = words.iterator();
            while (iterWords.hasNext()) {

                String str = iterWords.next();
                if (str.equals(strUnic)) {
                    count++;
                }
            }
            System.out.println(strUnic +" "+ count);
            count = 0;
        }


         /**
         * Задание по созданию простого телефонного справочника
         */

        PhoneBook phoneBook = new PhoneBook();
        phoneBook.addToPhoneBook("Sokolov", "+791512345678");
        phoneBook.addToPhoneBook("Korchits", "+79851234678");
        phoneBook.addToPhoneBook("Korchits", "+797771234678");

        phoneBook.getFromPhoneBook("Korchits");
        phoneBook.getFromPhoneBook("Sokolov");

        phoneBook.getFromPhoneBook("Korts");
    }

}
