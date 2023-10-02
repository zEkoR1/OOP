package Lab_1.behaviour;

import Lab_1.models.Faculty;
import Lab_1.models.Student;
import Lab_1.models.University;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.Date;
import java.util.Scanner;

public class ApplicationLoop{
    private Scanner scanner;
    private University university;
    private String command;


    public ApplicationLoop(){
        this.scanner = new Scanner(System.in);
        this.university = new University();
        this. command = "";
    }
    public void run(){
        System.out.println("Welcome!");
        printMainMenu();
        while (!this.command.equals("q")) {
            this.command = takeUserInput();
            String[] commandsList = this.command.split("/");
            switch (commandsList[0]) {
                case "f":
                    System.out.println(commandsList);
                    printFacultyMenu();
                    break;
                case "df":
                    printFaculties();
                    break;
                case "cf":
                    handleFacultyCreate(commandsList);
                    break;
                case "b":
                    printMainMenu();
                    break;
                default:
                    System.out.println("Invalid Command");
            }
        }
        scanner.close();

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
        String name = scanner.nextLine();
        System.out.println("faculty abbrev:");
        String abbreviation = scanner.nextLine();
        System.out.println("faculty field:");
        String field = scanner.nextLine();

        Faculty faculty = new Faculty(name, abbreviation, field);
        this.university.addFaculty(faculty);
    }

    private void addFaculty(String[] arguments) {
        Faculty faculty = new Faculty(arguments[1], arguments[2], arguments[3]);
        this.university.addFaculty(faculty);
    }

    private void printFaculties() {
        System.out.println(university);

    }
    private void handleStudentsCreate(String[] commands, LocalDate[] dates) {
        if(commands.length == 6) {
            addStudent(commands, dates);
        } else {
            addStudent();
        }
    }
    public void addStudent(String[] arguments, LocalDate[] dates){
        Student student = new Student(arguments[1], arguments[2], arguments[3], dates[4], dates[5]);
    }
    public void addStudent(){
        System.out.println("student name:");
        String name = scanner.nextLine();
        System.out.println("student lastName:");
        String lastName = scanner.nextLine();
        System.out.println("student email:");
        String email = scanner.nextLine();
        System.out.println("student enrollmentDate (dd-MM-yyyy):");
    }
    private void printMainMenu(){
        System.out.println("f - faculty operations");
        System.out.println("g - general operations");
        System.out.println("s - student operations ");
    }
    private void printFacultyMenu(){
        System.out.println("Faculty operations");
        System.out.println("cf - add faculty");
        System.out.println("df - display faculty");
        System.out.println("b - back");
        System.out.println("q - quit program");
    }
}


