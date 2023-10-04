package Lab_1.behaviour;

import Lab_1.commands.Impl.FacultyCommandHandler;
import Lab_1.models.Faculty;
import Lab_1.models.Student;
import Lab_1.models.University;
import Lab_1.services.FacultyService;
import Lab_1.utils.ConsoleMessages;

import java.util.Scanner;

public class ApplicationLoop {

    private final Scanner scanner;
    private final FacultyCommandHandler facultyCommandHandler = new FacultyCommandHandler();
    private String command;

    public enum MenuState {
        MAIN, FACULTY, STUDENT, GENERAL
    }


    public ApplicationLoop() {
        this.scanner = new Scanner(System.in);
        this.command = "";
    }

    public void run() {
        System.out.println("Welcome!");
        MenuState currentState = MenuState.MAIN;
        while (!this.command.equals("q")) {
            if (currentState == MenuState.MAIN) {
                ConsoleMessages.printMainMenu();
            } else if (currentState == MenuState.FACULTY) {
                ConsoleMessages.printFacultyMessages();
            } else if (currentState == MenuState.GENERAL) {
                ConsoleMessages.printGeneralMessages();
                System.out.println("General");
            } else{
                ConsoleMessages.printGeneralMessages();
                System.out.println("STUDENT");
            }
            this.command = takeUserInput();
            String[] commandsList = this.command.split("/");
            switch (commandsList[0]) {
                case "f":
                    System.out.println(commandsList);
                    currentState = MenuState.FACULTY;
                    break;
                case "df":
                    facultyCommandHandler.displayAllFaculties();
                    break;
                case "cf":
                    facultyCommandHandler.addFaculty(commandsList);
                    break;
                case "b":
                    currentState = MenuState.MAIN;
                    break;
                case "q":
                    System.out.println("Shutting down...");
                    break;
                case "as":
                    facultyCommandHandler.addStudent(commandsList);
                    break;
                case "s":
                    currentState = MenuState.STUDENT;
                    break;
                case "dgs":
                    facultyCommandHandler.displayGraduatedStudents(commandsList);
                    //function to display graduated students
                    break;
                case "des":
                    facultyCommandHandler.displayEnrolledStudents(commandsList);
                    //function to display enrolled students
                    break;
                case "gs":
                    facultyCommandHandler.graduateStudent(commandsList);
                    //function to graduate a student
                    break;
                case "dfef":
                    facultyCommandHandler.displayFacultiesField(commandsList);
                    //function to get faculties by field
                    break;
                case "bf":
                    facultyCommandHandler.checkStudentBelongsToFaculty(commandsList);
                    //check if student belongs to faculty
                    break;
                case "ss":
                    facultyCommandHandler.searchStudentAndShowFaculty(commandsList);
                    //search student and show faculty
                    break;
                case "g":
                    currentState = MenuState.GENERAL;
                    break;

                default:
                    System.out.println("Invalid Command");
            }
        }
        scanner.close();

    }

    private String takeUserInput() {
        System.out.println("Write Command> ");
        return scanner.nextLine();
    }




}


