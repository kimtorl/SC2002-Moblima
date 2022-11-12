package control;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class PriceFileManager {
    public static void main(String[] args) {
        String path_student_weekday = "./../Database/Movie_Student_Weekday.csv";
        String path_student_weekend = "./../Database/Movie_Student_Weekend.csv";

        String line = "";

        // my Tries
        Trie student_weekday_prices = new Trie();
        Trie student_weekend_prices = new Trie();

        // weekday prices
        try (BufferedReader br = new BufferedReader(new FileReader(path_student_weekday))) {
            while ((line = br.readLine()) != null) {
                String[] currentLine = line.split(",");
                // insert student weekday prices in
                if (currentLine.length != 0) {
                    student_weekday_prices.insertInformation(currentLine);

                }

                System.out.println(line);

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // weekend prices
        try (BufferedReader br = new BufferedReader(new FileReader(path_student_weekend))) {
            while ((line = br.readLine()) != null) {
                String[] currentLine = line.split(",");
                // insert student weekday prices in
                if (currentLine.length != 0) {
                    student_weekend_prices.insertInformation(currentLine);

                }

                System.out.println(line);

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static class TrieNode {
        Map<String, TrieNode> children = new HashMap<String, TrieNode>();
    }

    public static class Trie {
        TrieNode root = new TrieNode();

        public void insertInformation(String[] currentLine) {
            TrieNode node = root;
            for (String currentString : currentLine) {
                if (!node.children.containsKey(currentString)) {
                    TrieNode newNode = new TrieNode();
                    node.children.put(currentString, newNode);
                }

            }
        }
    }

}
