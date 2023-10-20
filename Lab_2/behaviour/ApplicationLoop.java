package Lab_2.behaviour;

import java.util.Scanner;

public class ApplicationLoop {
    private Scanner scanner;
    private String command;

    public ApplicationLoop() {
        this.scanner = new Scanner(System.in);
        this.command = "";
    }

    public void run() {
        System.out.println("Welcome!");
        System.out.println("Enter command:");
        while (!this.command.equals("q")) {


        }
    }
}
