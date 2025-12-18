package org.example;


public class Main {
    static void main() {

        WordCounter reader =new WordCounter("src/main/resources/file.txt");

        reader.count();
    }
}
