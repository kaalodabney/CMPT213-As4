package ca.cmpt213.as4.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * FileReader class reads and parses the data file provided
 */
public class FileReader {
    CourseManager courseManager;

    public FileReader(CourseManager courseManager) {
        this.courseManager = courseManager;
    }

    public void readFile(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        if (scanner.hasNext()) {
            scanner.nextLine();
            while (scanner.hasNext()) {
                String[] line = parseLine(scanner.nextLine());
                courseManager.addCourse(line[0], line[1], line[2], line[3], line[4], line[5], line[6], line[7]);
            }
        } else {
            //TODO add error handling
        }
        scanner.close();
    }

    public String[] parseLine(String s) {
        String[] lineParsed = new String[8];
        boolean insideQuotes = false;

        String line = s + ",";
        int charIndex = 0;

        for (int i = 0; i < 8; i++) {
            StringBuffer buffer = new StringBuffer();
            if (line.charAt(charIndex) == '\"') {
                insideQuotes = !insideQuotes;
                charIndex++;
            }

            if (!insideQuotes) {
                while (line.charAt(charIndex) != ',') {
                    buffer.append(line.charAt(charIndex));
                    charIndex++;
                }
                charIndex++;
            } else {
                while (line.charAt(charIndex) != '\"') {
                    buffer.append(line.charAt(charIndex));
                    charIndex++;
                }
                insideQuotes = !insideQuotes;
                charIndex += 2;
            }
            lineParsed[i] = buffer.toString();
        }
        return lineParsed;
    }
}
