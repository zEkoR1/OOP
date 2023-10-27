package Lab_2;

import Lab_2.services.files.abstracts.FileAttributes;
import Lab_2.services.files.GeneralFileAttributes;
import Lab_2.services.files.ImageFileAttributes;
import Lab_2.services.files.ProgramFileAttributes;
import Lab_2.services.files.TextFileAttributes;

import java.io.*;
import java.nio.file.*;
import java.text.SimpleDateFormat;
import java.util.*;


public class SimpleGit {

//    private static final String FOLDER_PATH = "C:\\Projects\\Java\\OOP\\Lab_2\\fileFolder";
    //pc path
    private static final String FOLDER_PATH = "C:\\Codes\\Java\\Lab_2\\fileFolder";
//laptop path
    private static final String COMMIT_FOLDER_PATH = FOLDER_PATH + File.separator + ".simplegit";
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MMMM dd, yyyy 'at' hh:mm:ss a");

    private static Date lastSnapshotTime = new Date();
    private static final Map<String, FileAttributes> fileAttributesMap = new HashMap<>();



    public static void loadFileAttributes(File directory) {
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



    public static void commit(File directory) {
        System.out.println("Committing changes...");
        System.out.println("Do you want to commit all files? (yes/no): ");
        Scanner scanner = new Scanner(System.in);
        String answer = scanner.nextLine();
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

            FileAttributes attributes;
            if (ImageFileAttributes.isImage(file)) {
                attributes = new ImageFileAttributes(file);
            } else if (TextFileAttributes.isTextFile(file)) {
                attributes = new TextFileAttributes(file);
            } else if (ProgramFileAttributes.isProgramFile(file)) {
                attributes = new ProgramFileAttributes(file);
            } else {
                attributes = new GeneralFileAttributes(file);
            }

            fileAttributesMap.put(file.getName(), attributes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void info(File directory, String filename) {
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
        System.out.println("File extension: " + attributes.getFileExtension());
        System.out.println("Created date: " + DATE_FORMAT.format(attributes.getCreationTime().toMillis()));
        System.out.println("Last modified date: " + DATE_FORMAT.format(attributes.getLastModifiedTime().toMillis()));

        if (attributes instanceof ImageFileAttributes) {
            ImageFileAttributes imageAttributes = (ImageFileAttributes) attributes;
            System.out.println("Image width: " + imageAttributes.getImageWidth());
            System.out.println("Image height: " + imageAttributes.getImageHeight());
        } else if (attributes instanceof TextFileAttributes) {
            TextFileAttributes textAttributes = (TextFileAttributes) attributes;
            System.out.println("Line count: " + textAttributes.getLineCount());
            System.out.println("Word count: " + textAttributes.getWordCount());
            System.out.println("Character count: " + textAttributes.getCharacterCount());
        } else if (attributes instanceof ProgramFileAttributes) {
            ProgramFileAttributes programAttributes = (ProgramFileAttributes) attributes;
            System.out.println("Class count: " + programAttributes.getClassCount());
            System.out.println("Method count: " + programAttributes.getMethodCount());
        }
    }




    public static void status(File directory) {
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



    public static void deleteCommits() {
        File commitDirectory = new File(COMMIT_FOLDER_PATH);
        if (commitDirectory.exists() && commitDirectory.isDirectory()) {
            for (File file : Objects.requireNonNull(commitDirectory.listFiles())) {
                if (file.isFile()) {

                    file.delete();
                }
            }
        }
    }



}


