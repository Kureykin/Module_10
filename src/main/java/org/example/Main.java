package org.example;


public class Main {
    static void main() {

        new PhoneNumberReader().readPhoneNumber("src/main/resources/phonenumbers.txt");
        System.out.println("----");

        new ToJsonConverter("src/main/resources/users.txt").parceToJSON();
        System.out.println("----");

        new WordCounter("src/main/resources/words.txt").count();
        System.out.println("----");

    }
}
