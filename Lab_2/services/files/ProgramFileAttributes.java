package Lab_2.services.files;

import Lab_2.services.files.abstracts.FileAttributes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ProgramFileAttributes extends FileAttributes {

    private int classCount;
    private int methodCount;

    public ProgramFileAttributes(File file) {
        super(file);
        calculateAttributes(file);
    }

    public void calculateAttributes(File file) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            boolean commentBlock = false;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.startsWith("/*")) {
                    commentBlock = true;
                }
                if (line.endsWith("*/")) {
                    commentBlock = false;
                }
                if (!commentBlock && !line.startsWith("//")) {
                    if (line.contains(" class ") || line.contains("class ")) {
                        classCount++;
                    }
                    if (line.contains(" void ") || line.contains(" int ") || line.contains(" String ") || line.contains(" boolean ") || line.contains(" double ") || line.contains(" float ")) {
                        if (line.contains("(") && line.contains(")") && line.contains("{")) {
                            methodCount++;
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getClassCount() {
        return classCount;
    }

    public int getMethodCount() {
        return methodCount;
    }

    public static boolean isProgramFile(File file) {
        String extension = getFileExtension(file).toLowerCase();
        return extension.equals("java") || extension.equals("py");
    }

    public static String getFileExtension(File file) {
        String name = file.getName();
        int lastIndexOf = name.lastIndexOf(".");
        if (lastIndexOf == -1) {
            return "";
        }
        return name.substring(lastIndexOf + 1);
    }
}

