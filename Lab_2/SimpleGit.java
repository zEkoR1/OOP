package Lab_2;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.text.SimpleDateFormat;
import java.util.*;

public class SimpleGit {

    private static final String FOLDER_PATH = "C:\\Codes\\Java\\Lab_2\\fileFolder";
    private static final String COMMIT_FOLDER_PATH = FOLDER_PATH + File.separator + ".simplegit";
    private static final String COMMIT_FILE_NAME = ".commit";
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MMMM dd, yyyy 'at' hh:mm:ss a");
    private static final String SEPARATOR = " | ";

    private static Date lastSnapshotTime = new Date();
    private static final Map<String, FileAttributes> fileAttributesMap = new HashMap<>();

    public static void main(String[] args) {
        File directory = new File(FOLDER_PATH);
        if (!directory.exists() || !directory.isDirectory()) {
            System.out.println("The specified directory does not exist or is not a directory.");
            return;
        }

        loadFileAttributes(directory);

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            while (true) {
                System.out.println("Enter command (commit, info <filename>, status, exit):");
                String command = br.readLine();
                if (command.equals("exit")) {
                    System.out.println("Shutting down...");
                    deleteCommits();
                    break;
                } else if (command.startsWith("commit")) {
                    commit(directory);
                } else if (command.startsWith("info ")) {
                    String filename = command.substring("info ".length());
                    info(directory, filename);
                } else if (command.equals("status")) {
                    status(directory);
                } else {
                    System.out.println("Invalid command. Please try again.");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void loadFileAttributes(File directory) {
        fileAttributesMap.clear();
        if (directory.exists() && directory.isDirectory()) {
            for (File file : Objects.requireNonNull(directory.listFiles())) {
                if (!file.isDirectory() && !file.isHidden() && !file.getName().equals(".simplegit")) {
                    fileAttributesMap.put(file.getName(), new FileAttributes(file));
                }
            }
        } else {
            System.out.println("Commit directory does not exist. No previous commit found.");
        }
    }



    private static void commit(File directory) {
        System.out.println("Committing changes...");
        System.out.println("Do you want to commit all files? (yes/no): ");
        Scanner scanner = new Scanner(System.in);
        String answer = scanner.nextLine();

        // Ensure the commit directory exists
        File commitDirectory = new File(COMMIT_FOLDER_PATH);
        if (!commitDirectory.exists()) {
            commitDirectory.mkdirs();
        }

        if ("yes".equalsIgnoreCase(answer)) {
            for (File file : Objects.requireNonNull(directory.listFiles())) {
                if (!file.isDirectory() && !file.isHidden()) {
                    commitFile(file, commitDirectory);
                }
            }
        } else {
            System.out.println("Enter file names to commit, separated by commas:");
            String fileNames = scanner.nextLine();
            String[] filesToCommit = fileNames.split(",");
            for (String fileName : filesToCommit) {
                File file = new File(directory, fileName.trim());
                if (file.exists() && !file.isDirectory() && !file.isHidden()) {
                    commitFile(file, commitDirectory);
                    System.out.println("Commit completed.");
                } else {
                    System.out.println("File not found or is a directory: " + fileName.trim());
                    System.out.println("Commit failed.");

                }
            }
        }

        lastSnapshotTime = new Date();
    }

    private static void commitFile(File file, File commitDirectory) {
        try {
            Path targetPath = Paths.get(COMMIT_FOLDER_PATH + File.separator + file.getName());
            Files.copy(file.toPath(), targetPath, StandardCopyOption.REPLACE_EXISTING);
            fileAttributesMap.put(file.getName(), new FileAttributes(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void info(File directory, String filename) {
        File file = new File(directory, filename);
        if (!file.exists()) {
            System.out.println("File not found.");
            return;
        }

        FileAttributes attributes = fileAttributesMap.get(filename);
        if (attributes == null) {
            System.out.println("File attributes not found. Please commit the changes first.");
            return;
        }

        System.out.println("File name: " + filename);
        System.out.println("File extension: " + attributes.fileExtension);
        System.out.println("Created date: " + DATE_FORMAT.format(attributes.creationTime.toMillis()));
        System.out.println("Last modified date: " + DATE_FORMAT.format(attributes.lastModifiedTime.toMillis()));

        if (attributes.isImage()) {
            System.out.println("Image size: " + attributes.imageWidth + "x" + attributes.imageHeight);
        } else if (attributes.isTextFile()) {
            System.out.println("Line count: " + attributes.lineCount);
            System.out.println("Word count: " + attributes.wordCount);
            System.out.println("Character count: " + attributes.characterCount);
        } else if (attributes.isProgramFile()) {
            System.out.println("Line count: " + attributes.lineCount);
            System.out.println("Class count: " + attributes.classCount);
            System.out.println("Method count: " + attributes.methodCount);
        }
    }



    private static void status(File directory) {
        System.out.println("Status since last snapshot (" + DATE_FORMAT.format(lastSnapshotTime) + "):");

        File commitDirectory = new File(COMMIT_FOLDER_PATH);
        if (!commitDirectory.exists()) {
            System.out.println("No commits found.");
            return;
        }

        Set<String> committedFiles = new HashSet<>(Arrays.asList(Objects.requireNonNull(commitDirectory.list())));
        Set<String> workingFiles = new HashSet<>(Arrays.asList(Objects.requireNonNull(directory.list())));

        for (String workingFileName : workingFiles) {
            if (!committedFiles.contains(workingFileName) && !workingFileName.equals(".simplegit")) {
                System.out.println("New/Uncommitted: " + workingFileName);
            }
        }

        for (String committedFileName : committedFiles) {
            File committedFile = new File(commitDirectory, committedFileName);
            File workingFile = new File(directory, committedFileName);

            if (!workingFile.exists()) {
                System.out.println("Deleted: " + committedFileName);
            } else {
                FileAttributes committedAttributes = fileAttributesMap.get(committedFileName);
                FileAttributes workingAttributes = new FileAttributes(workingFile);

                if (committedAttributes != null && committedAttributes.equals(workingAttributes)) {
                    System.out.println("Committed (Unchanged): " + committedFileName);
                } else {
                    System.out.println("Changed: " + committedFileName);
                }
            }
        }
    }



    private static void deleteCommits() {
        File commitDirectory = new File(COMMIT_FOLDER_PATH);
        if (commitDirectory.exists() && commitDirectory.isDirectory()) {
            for (File file : Objects.requireNonNull(commitDirectory.listFiles())) {
                if (file.isFile()) {
                    file.delete();
                }
            }
        }
    }


    private static class FileAttributes {
        String fileName;
        String fileExtension;
        FileTime creationTime;
        FileTime lastModifiedTime;
        int imageWidth;
        int imageHeight;
        int lineCount;
        int wordCount;
        int characterCount;
        int classCount;
        int methodCount;

        public FileAttributes(File file) {
            this.fileName = file.getName();
            this.fileExtension = getFileExtension(file);
            this.creationTime = getFileCreationTime(file);
            this.lastModifiedTime = getFileLastModifiedTime(file);

            if (isImage()) {
            } else if (isTextFile()) {
                countTextFileAttributes(file);
            } else if (isProgramFile()) {
                countProgramFileAttributes(file);
            }
        }

        private String getFileExtension(File file) {
            String name = file.getName();
            int lastIndexOf = name.lastIndexOf(".");
            if (lastIndexOf == -1) {
                return ""; // Empty extension
            }
            return name.substring(lastIndexOf + 1);
        }

        private FileTime getFileCreationTime(File file) {
            try {
                BasicFileAttributes attrs = Files.readAttributes(file.toPath(), BasicFileAttributes.class);
                return attrs.creationTime();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        private FileTime getFileLastModifiedTime(File file) {
            try {
                BasicFileAttributes attrs = Files.readAttributes(file.toPath(), BasicFileAttributes.class);
                return attrs.lastModifiedTime();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        public boolean isImage() {
            return fileExtension.equals("png") || fileExtension.equals("jpg");
        }

        public boolean isTextFile() {
            return fileExtension.equals("txt");
        }

        public boolean isProgramFile() {
            return fileExtension.equals("py") || fileExtension.equals("java");
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

        private void countProgramFileAttributes(File file) {
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                boolean commentBlock = false;
                while ((line = br.readLine()) != null) {
                    lineCount++;
                    wordCount += line.split("\\s+").length;
                    characterCount += line.length();

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


        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            FileAttributes that = (FileAttributes) o;
            return imageWidth == that.imageWidth &&
                    imageHeight == that.imageHeight &&
                    lineCount == that.lineCount &&
                    wordCount == that.wordCount &&
                    characterCount == that.characterCount &&
                    classCount == that.classCount &&
                    methodCount == that.methodCount &&
                    fileName.equals(that.fileName) &&
                    Objects.equals(fileExtension, that.fileExtension) &&
                    creationTime.equals(that.creationTime) &&
                    lastModifiedTime.equals(that.lastModifiedTime);
        }


        @Override
        public int hashCode() {
            return Objects.hash(fileName, fileExtension, creationTime, lastModifiedTime, imageWidth, imageHeight, lineCount, wordCount, characterCount, classCount, methodCount);
        }
    }
}


