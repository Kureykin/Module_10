package org.example;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

public class WordCounter {

    private File filePath;

    public  WordCounter(String filePath) {
        
        this.filePath = new File(filePath);
    }

     static class Node {
        private String word;
        private int wordCount;

        public Node(String word, int wordCount) {

            this.word = word;
            this.wordCount = wordCount;
        }

        public int getWordCount() {
            return wordCount;
        }
        public String getWord(){
            return word;
        }

        @Override
        public String toString(){
            return word + " - " + wordCount;
        }
    }

    public void count(){

        try(FileReader reader = new FileReader(filePath)) {

            String buffer = reader.readAllAsString();

            ArrayList<String> words = separate(buffer);
            HashSet<String> exclusiveWordsList = new HashSet<>(words);

            int index = 0;
            Node[] wordsStatistic = new Node[exclusiveWordsList.size()];

            for(String  statisticWord: exclusiveWordsList) {

                int wordCount = 0;

               for (String word: words) {
                   if(statisticWord.equals(word)) {
                       wordCount++;
                   }
               }

               wordsStatistic[index] = new Node(statisticWord, wordCount);
               index++;
            }
            arraySort(wordsStatistic);

            for(Node statistic: wordsStatistic) {

                System.out.println(statistic.toString());
            }
        }
        catch (IOException ex) {

            System.out.println(ex.getMessage());
        }

    }

    private void arraySort(Node[] array) {

        Node tmp;
        for (int i = 0; i < array.length / 2 + 1; i++) {
            for(int j = array.length / 2; j > i; j--) {
                if(array[i].wordCount < array[j].wordCount) {

                    tmp = array[i];
                    array[i] = array[j];
                    array[j] = tmp;
                }
            }
            for (int j = array.length - 1; j > (array.length /2) + i; j--) {
                if(array[(array.length /2) + i].wordCount < array[j].wordCount) {

                    tmp = array[(array.length /2) + i];
                    array[(array.length /2) + i] = array[j];
                    array[j] = tmp;
                }
            }
        }

        for (int i = 0; i < array.length / 2 + 1; i++) {
            for (int j = array.length - 1; j > array.length / 2; j--) {
                if(array[i].wordCount < array[j].wordCount){

                    tmp = array[i];
                    array[i] = array[j];
                    array[j] = tmp;
                }
            }
        }
    }

    private ArrayList<String> separate(String text) {

        text = text + " ";
        int startWordChar = 0;
        ArrayList<String> result = new ArrayList<>();

        for (int i = 0; i < text.length(); i++) {
            if(text.charAt(i) == ' ' || text.charAt(i) == '\n') {

                result.add(text.substring(startWordChar, i).strip());
                startWordChar = i + 1;
            }
        }

        return result;
    }
}

