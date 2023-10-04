package Lab_1.models;

import Lab_1.enums.StudyField;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Faculty {
    private String name;
    private String abbreviation;
    private StudyField field;
    private List<Student> enrolledStudents = new ArrayList<>();
    private List<Student> graduatedStudents = new ArrayList<>();

//    public Faculty(String name, String abbreviation, String field) {
//        this.name = name;
//        this.abbreviation = abbreviation;
//        this.field = field;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public List<Student> getEnrolledStudents() {
        return enrolledStudents;
    }

    public void setEnrolledStudents(List<Student> enrolledStudents) {
        this.enrolledStudents = enrolledStudents;
    }

    public void setField(StudyField field) {
        this.field = field;
    }

    public StudyField getField() {
        return field;
    }

    public List<Student> getGraduatedStudents() {
        return graduatedStudents;
    }

    public void setGraduatedStudents(List<Student> graduatedStudents) {
        this.graduatedStudents = graduatedStudents;
    }

    public String getAbbreviation(){
        return  abbreviation;
    }

    @Override
    public String toString() {
        return String.format("Faculty(name: %s, abbreviation: %s, field: %s)",name, abbreviation, field);
    }

    public void addStudent(Student student){
        this.enrolledStudents.add(student);
    }

    public void addGraduatedStudent(Student student){
        this.graduatedStudents.add(student);
    }

    public void graduateStudent(String email){
        for (Student student : enrolledStudents){
        this.enrolledStudents.remove(searchStudentByEmail(email));
        this.graduatedStudents.add(searchStudentByEmail(email));
        }
    }
    public Student searchStudentByEmail (String email){
        for (Student student : enrolledStudents){
            if (email == student.getEmail() ){
                return student;
            }
        }
        return null;
    }

}
