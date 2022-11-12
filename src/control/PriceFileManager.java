package control;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.*;

public class PriceFileManager {
	
	public static final String FILENAME = "Database/prices.txt";
	
    public static void main(String[] args) {
    	
        

    }
    
    public void writeToFile() {
    	
    }
    
    public void initialisePrice() {
    	String path_student_weekday = "Database/Movie_Student_Weekday.csv";
        String path_student_weekend = "Database/Movie_Student_Weekend.csv";
        String path_adult = "Database/Movie_Price_Adult.csv";
        String line = "";

        // my Tries

        // ====== Student ========
        Trie student_weekday_prices = new Trie();
        Trie student_weekend_prices = new Trie();

        // ====== Adult =========
        Trie adult_prices = new Trie();

        // ======= Senior ===========

        Trie senior_weekday_prices = new Trie();
        Trie senior_weekend_prices = new Trie();

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

        // collect adult data

        try (BufferedReader br = new BufferedReader(new FileReader(path_adult))) {
            while ((line = br.readLine()) != null) {
                String[] currentLine = line.split(",");

                if (currentLine.length != 0) {
                    adult_prices.insertInformation(currentLine);

                }

                System.out.println(line);

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedReader br = new BufferedReader(new FileReader(path_student_weekday))) {
            while ((line = br.readLine()) != null) {
                String[] currentLine = line.split(",");
                // insert student weekday prices in
                if (currentLine.length != 0) {
                    senior_weekday_prices.insertInformation(currentLine);

                }

                System.out.println(line);

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedReader br = new BufferedReader(new FileReader(path_student_weekday))) {
            while ((line = br.readLine()) != null) {
                String[] currentLine = line.split(",");
                // insert student weekday prices in
                if (currentLine.length != 0) {
                    senior_weekend_prices.insertInformation(currentLine);

                }

                System.out.println(line);

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    

    public static class TrieNode implements Serializable {
        private static final long serialVersionUID = 1L;
		Map<String, TrieNode> children = new HashMap<String, TrieNode>();
        String finalInformation = "";
    }

    public static class Trie implements Serializable{
        private static final long serialVersionUID = 1L;
		TrieNode root = new TrieNode();

        public void insertInformation(String[] currentLine) {
            String finalPrice = currentLine[currentLine.length - 1];
            TrieNode node = root;
            for (int i = 0; i < currentLine.length - 1; i++) {
                String currentString = currentLine[i];
                if (!node.children.containsKey(currentString)) {
                    TrieNode newNode = new TrieNode();
                    node.children.put(currentString, newNode);
                }
                node = node.children.get(currentString);

            }
            node.finalInformation = finalPrice;

        }

        public String readPrice(String[] queries) {
            String res = "";

            TrieNode node = root;
            for (String query : queries) {
                if (!node.children.containsKey(query)) {
                    System.out.println("Key does not exist!");
                    System.out.println(query);

                }
                node = node.children.get(query);

            }

            res = node.finalInformation;
            return res;
        }
    }

}
