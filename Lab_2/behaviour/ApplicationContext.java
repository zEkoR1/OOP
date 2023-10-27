package Lab_2.behaviour;

import Lab_2.SimpleGit;
import Lab_2.services.FileChangeScheduler;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;


public class ApplicationContext {
    private final String folderPath;
    private final FileChangeScheduler fileChangeScheduler;

    public ApplicationContext(String folderPath) {
        this.folderPath = folderPath;
        this.fileChangeScheduler = new FileChangeScheduler();
    }

    public void run() {
        File directory = new File(folderPath);
        if (!directory.exists() || !directory.isDirectory()) {
            System.out.println("The specified directory does not exist or is not a directory.");
            return;
        }

        fileChangeScheduler.start(folderPath);
        SimpleGit.loadFileAttributes(directory);

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            while (true) {
                System.out.println("Enter command (commit, info <filename>, status, exit):");
                String command = br.readLine();
                if (command.equals("exit")) {
                    System.out.println("Shutting down...");
                    SimpleGit.deleteCommits();
                    fileChangeScheduler.stop();
                    break;
                } else if (command.startsWith("commit")) {
                    SimpleGit.commit(directory);
                } else if (command.startsWith("info ")) {
                    String filename = command.substring("info ".length());
                    SimpleGit.info(directory, filename);
                } else if (command.equals("status")) {
                    SimpleGit.status(directory);
                } else {
                    System.out.println("Invalid command. Please try again.");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            fileChangeScheduler.stop();
        }
    }
}