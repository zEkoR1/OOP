package Lab_1.behaviour;

import Lab_1.models.Faculty;
import Lab_1.models.University;
import java.util.Scanner;

public class ApplicationLoop{
    private Scanner scanner;
    private University university;
    private String command;


    public ApplicationLoop(){
        this.scanner = new Scanner(System.in);
        this.university = new University();
        this. command = "asdas";
    }
    public void run(){
        while (!this.command.equals("q")){
            System.out.println(this.command);
            this.command = takeUserInput();
            String[] commandsList = this.command.split("/");
            switch (commandsList[0]) {
                case "f":
                    handleFacultyCreate(commandsList);
                    break;
                case "pf":
                    printFaculties();
                    break;
                case "af":

                default:
                    System.out.println("Invalid Command");

            }
            scanner.close();

        }
    }

    private String takeUserInput(){
        System.out.println("Write Command> ");
        return scanner.nextLine();
    }
    private void handleFacultyCreate(String[] commands) {
        if(commands.length == 4) {
            addFaculty(commands);
        } else {
            addFaculty();
        }
    }

    private void addFaculty() {
        // add faculty
        System.out.println("faculty name:");
        String facultyName = scanner.nextLine();
        System.out.println("faculty abbrev:");
        String facultyAbbrev = scanner.nextLine();
        System.out.println("faculty field:");
        String facultyField = scanner.nextLine();

        Faculty faculty = new Faculty(facultyName, facultyAbbrev, facultyField);
        this.university.addFaculty(faculty);
    }

    private void addFaculty(String[] arguments) {

        Faculty faculty = new Faculty(arguments[1], arguments[2], arguments[3]);
        this.university.addFaculty(faculty);
    }

    private void printFaculties() {
        System.out.println(university);
    }

}


