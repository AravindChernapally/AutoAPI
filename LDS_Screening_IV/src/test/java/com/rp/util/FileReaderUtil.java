package com.rp.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileReaderUtil {

    public String readAllFileContentAsString(String filePath) throws IOException {
        System.out.println("Reading all file content from: " + filePath);

        String fileContent = null;

        try(BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }

            fileContent = sb.toString();
        }
        catch (IOException e)
        {
            System.out.println("Error reading file content for " + filePath + ". Error is: " + e.getMessage());
            throw e;
        }

        return fileContent;
    }
}
