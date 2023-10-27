package Lab_2.services.files;


import Lab_2.services.files.abstracts.FileAttributes;

import java.io.*;

public class TextFileAttributes extends FileAttributes {
    int lineCount;
    int wordCount;
    int characterCount;

    public TextFileAttributes(File file) {
        super(file);
        countTextFileAttributes(file);
    }

    private void countTextFileAttributes(File file) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                lineCount++;
                wordCount += line.split("\\s+").length;
                characterCount += line.length();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean isTextFile(File file) {
        String extension = getFileExtension(file).toLowerCase();
        return extension.equals("txt");
    }

    public static String getFileExtension(File file) {
        String name = file.getName();
        int lastIndexOf = name.lastIndexOf(".");
        if (lastIndexOf == -1) {
            return "";
        }
        return name.substring(lastIndexOf + 1);
    }

    public int getLineCount() {
        return lineCount;
    }

    public int getWordCount() {
        return wordCount;
    }

    public int getCharacterCount() {
        return characterCount;
    }
}
