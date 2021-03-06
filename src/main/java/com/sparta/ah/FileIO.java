package com.sparta.ah;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;

import static com.sparta.ah.logging.LogConfig.logger;

public class FileIO {


    public static ArrayList<EmployeeDTO> readFromFile(String filename) {
        logger.log(Level.INFO,"Reading from "  + filename + "...");
        ArrayList<EmployeeDTO> arrayToSort = new ArrayList<>();
        try {
            // Decorator pattern:
            FileReader fileReader = new FileReader(filename);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            bufferedReader.readLine(); //read first line column headers

            for (String line = bufferedReader.readLine(); line != null; line = bufferedReader.readLine()) {
                String[] args = line.split(",");
                arrayToSort.add(new EmployeeDTO(args));
                            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        logger.log(Level.INFO, "Finished reading " + filename);
    return arrayToSort;

    }
}
