package control;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.HashMap;

public class PriceFileManager {

    // our information could be split into :
    // Movie type -> Cinema Class -> Ticket type -> date time
    HashMap<String, HashMap<String, HashMap<String, String>>> PriceFiles = new HashMap<String, HashMap<String, HashMap<String, String>>>();

    public void loadData() throws FileNotFoundException {
        File file = new File("./../Database/test.txt");
        Scanner scan = new Scanner(file);
        while (scan.hasNextLine()) {
            String currentLine = scan.nextLine();
            String[] PriceInformationArray = currentLine.split("-");
            // populate the hashmap here

        }
    }

    public ArrayList<Integer> updatePrice() {

    }

}
