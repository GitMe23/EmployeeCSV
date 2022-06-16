package com.sparta.ah;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class DirtyListFileWriter {

    private File file;
    private String filePath;

    public DirtyListFileWriter(String filePath) {
        this.filePath = filePath;
    }



    public boolean setFile() {
        File temp = new File(filePath);
        if (temp.exists()) {
            file = temp;
            return true;
        } else {
            return false;
        }
    }


    public void writeDirtyDataToFile(ArrayList<EmployeeDTO> dirtyList) {

        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath));
            bufferedWriter.write("Emp ID,Name Prefix,First Name,Middle Initial,Last Name,Gender,E Mail,Date of Birth,Date of Joining,Salary");
            bufferedWriter.newLine();
            for (EmployeeDTO employee : dirtyList) {
                bufferedWriter.write(employee.toString());
                bufferedWriter.newLine();
            }
            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }





}
