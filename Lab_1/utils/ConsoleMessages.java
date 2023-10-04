package Lab_1.utils;

public class ConsoleMessages {


    public static void printGeneralMessages() {
        System.out.println("Students operation");
        System.out.println("ad - add student");
        System.out.println("gs - graduate student");
        System.out.println("des - display enrolled students");
        System.out.println("dgs - display graduated students");
        System.out.println("b - back");
        System.out.println("q - quit program");
    }
    public static void printFacultyMessages() {
        System.out.println("Faculty operations");
        System.out.println("ss - search student and show faculty");
        System.out.println("bf - check if student belongs to faculty");
        System.out.println("dfef - display faculties by field");
        System.out.println("b - back");
        System.out.println("q - quit program");
    }
    public static void printMainMenu () {
        System.out.println("f - faculty operations");
        System.out.println("g - general operations");
        System.out.println("s - student operations ");
    }


}
