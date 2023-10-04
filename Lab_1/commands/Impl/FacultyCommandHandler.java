package Lab_1.commands.Impl;

import Lab_1.commands.CommandHandler;
import Lab_1.enums.StudyField;
import Lab_1.models.Faculty;
import Lab_1.models.Student;
import Lab_1.services.FacultyService;

import java.util.List;

public class FacultyCommandHandler implements CommandHandler {

    private final FacultyService facultyService = new FacultyService();

    @Override
    public boolean handleCommand(String[] commands, int requiredSize) {
        System.out.println(commands.length);
        if (commands.length == requiredSize) {
            return true;
        }
        return false;
    }


    public void searchStudentAndShowFaculty(String[] commandList) {
        var parametersSize = 2;
        if (handleCommand(commandList, parametersSize)) {
            facultyService.getStudentAndFacultyByEmail(commandList[1]);
        }  else {
            System.out.println("Error: Incorrect amount of parameters." + "Required amount of parameters " + (parametersSize-1));
        }
    }

    public void checkStudentBelongsToFaculty(String[] commandList) {
        var parametersSize = 3;
        if (handleCommand(commandList, parametersSize)) {
            boolean status = facultyService.checkStudentsBelongingToFaculty(commandList[1], commandList[2]);
            if (status) {
                System.out.println("Student belongs to this faculty");
            }else {
                System.out.println("Student doesn't belong to this faculty");
            }
        }  else {
            System.out.println("Error: Incorrect amount of parameters." + "Required amount of parameters " + (parametersSize-1));
        }
    }

    public void displayFacultiesField(String[] commandList) {
        var parametersSize = 2;
        if (handleCommand(commandList, parametersSize)) {
            facultyService.getFacultiesByField(commandList[1]);
        }  else {
            System.out.println("Error: Incorrect amount of parameters." + "Required amount of parameters " + (parametersSize-1));
        }
    }

    public void displayEnrolledStudents(String[] commandList) {
        var parametersSize = 2;
        if (handleCommand(commandList, parametersSize)) {
            List<Student> students =  facultyService.getEnrolledStudents(commandList[1]);
            if (students == null) {
                System.out.println("No enrolled students to this faculty ");
            } else {
                students.forEach(System.out::println);
            }
        } else {
            System.out.println("Error: Incorrect amount of parameters." + "Required amount of parameters " + (parametersSize-1));
        }
    }

    public void displayGraduatedStudents(String[] commandList) {
        var parametersSize = 2;
        if (handleCommand(commandList, parametersSize)) {
            List<Student> students =  facultyService.getGraduatedStudents(commandList[1]);
            if (students == null) {
                System.out.println("No graduated students to this faculty ");
            } else {
                students.forEach(System.out::println);
            }
        } else {
            System.out.println("Error: Incorrect amount of parameters." + "Required amount of parameters " + (parametersSize-1));
        }
    }

    public void graduateStudent(String[] commandList) {
        var parametersSize = 2;

        if (handleCommand(commandList, parametersSize)) {
            facultyService.graduateStudentFromFaculty(commandList[1]);
        } else {
            System.out.println("Error: Incorrect amount of parameters." + "Required amount of parameters " + (parametersSize-1));
        }
    }

    public void addFaculty(String[] commandList) {
        var parametersSize = 4;
        if (handleCommand(commandList, parametersSize)) {
            facultyService.createFaculty(commandList[1], commandList[2], commandList[3]);
        } else {
            System.out.println("Error: Incorrect amount of parameters." + "Required amount of parameters " + (parametersSize-1));
        }
    }

    public void addStudent(String[] commandList) {
        var parametersSize = 8;
        if (handleCommand(commandList, parametersSize)) {
            facultyService.createNewStudent(commandList[1], commandList[2], commandList[3], commandList[4],
                    Integer.parseInt(commandList[5]), Integer.parseInt(commandList[6]), Integer.parseInt(commandList[7]));
        } else {
            System.out.println("Error: Incorrect amount of parameters." + "Required amount of parameters " + (parametersSize-1));
        }
    }

    public void displayAllFaculties() {
        facultyService.getAllFaculties();
    }
}
