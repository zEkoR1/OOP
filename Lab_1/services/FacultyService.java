package Lab_1.services;

import Lab_1.behaviour.UniversityLayer;
import Lab_1.enums.StudyField;
import Lab_1.models.Faculty;
import Lab_1.models.Student;
import Lab_1.models.University;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class FacultyService extends UniversityLayer {
    private final University university;

    public FacultyService() {
        this.university = getUniversity();
    }


    public void getStudentAndFacultyByEmail(String email) {
        List<Faculty> faculties = university.getAllFaculties();
        for (Faculty faculty : faculties) {
            List<Student> enrolledStudents = faculty.getEnrolledStudents();
            Optional<Student> student1 = enrolledStudents.stream()
                    .filter(student -> student.getEmail().equals(email))
                    .findFirst();

            if (student1.isPresent()) {
                System.out.println("Student with email: " + email + " and faculty: " + faculty.getName() + " exists");
            } else {
                System.out.println("Student with email " + email + " not found");
            }
        }

    }

    public boolean checkStudentsBelongingToFaculty(String facultyAbbr, String email) {
        List<Student> enrolledStudents = getEnrolledStudents(facultyAbbr);
        if (enrolledStudents != null) {
            for(Student student : enrolledStudents) {
                if (student.getEmail().equals(email)) {
                    return true;
                }
            }
        }

        return false;
    }

    public void getFacultiesByField(String field) {
        try {
            StudyField studyField = StudyField.valueOf(field);
            List<Faculty> faculties = university.getAllFaculties();
            List<Faculty> filteredFaculties = faculties.stream()
                    .filter(f -> f.getField().equals(studyField))
                    .toList();
            if (filteredFaculties.isEmpty()) {
                System.out.println("No faculties with a StudyField " + field);
            } else {
                System.out.println("Faculties with StudyField " + field);
                filteredFaculties.forEach(System.out::println);
            }

        } catch (Exception e) {
            System.out.println("Invalid studyField: " + field);
        }
    }

    public List<Student> getEnrolledStudents(String facultyAbbr) {
        Faculty faculty = getFacultyByAbbr(facultyAbbr);
        if (faculty != null) {
            List<Student> students = faculty.getEnrolledStudents();
            if (students.isEmpty()) {
                return null;
            }

            return students;
        }

        System.out.println("Not found a faculty with abbreviation " + facultyAbbr);
        return null;
    }

    public void graduateStudentFromFaculty(String email) {
        List<Faculty> faculties = university.getAllFaculties();
        for (Faculty faculty : faculties) {
            List<Student> enrolledStudents = faculty.getEnrolledStudents();
            Optional<Student> student1 = enrolledStudents.stream()
                    .filter(student -> student.getEmail().equals(email))
                    .findFirst();

            if (student1.isPresent()) {
                Student studentToRemove = student1.get();
                enrolledStudents.remove(studentToRemove);
                faculty.setEnrolledStudents(enrolledStudents);
                faculty.addGraduatedStudent(studentToRemove);
                System.out.println("Student was graduated from " + faculty.getName());
            } else {
                System.out.println("Student with email " + email + " not found or it's already graduated");
            }
        }

        university.setFaculties(faculties);
    }

    public List<Student> getGraduatedStudents(String facultyAbbr) {
        Faculty faculty = getFacultyByAbbr(facultyAbbr);
        if (faculty != null) {
            List<Student> students = faculty.getGraduatedStudents();
            if (students.isEmpty()) {
                return null;
            }

            return students;
        }

        System.out.println("Not found a faculty with abbreviation " + facultyAbbr);
        return null;
    }

    public void createNewStudent(String facultyAbbr, String firstName,
                                 String lastName, String email, int day, int month, int year) {

        Faculty faculty = getFacultyByAbbr(facultyAbbr);
        if (faculty != null) {
            LocalDate dateOfBirth = LocalDate.of(year, month, day);

            Student student = new Student();
            student.setFirstName(firstName);
            student.setLastName(lastName);
            student.setEmail(email);
            student.setEnrollmentDate(LocalDate.now());
            student.setDateOfBirth(dateOfBirth);

            faculty.addStudent(student);

            System.out.println("Student is created successfully");


        } else {
            System.out.println("Not found a faculty with abbreviation " + facultyAbbr);
        }
    }


    public void createFaculty(String abbrev, String name, String field) {

        try {
            StudyField studyField = StudyField.valueOf(field);

            Faculty faculty = new Faculty();
            faculty.setName(name);
            faculty.setAbbreviation(abbrev);
            faculty.setField(studyField);

            university.addFaculty(faculty);

            System.out.println("Faculty added successfully");

        } catch (Exception e) {
            System.out.println("Invalid studyField: " + field);
        }

    }

    public void getAllFaculties() {
        List<Faculty> faculties = university.getAllFaculties();
        faculties.forEach(System.out::println);
    }
}
