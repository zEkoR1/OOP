package Lab_1.services;

import Lab_1.behaviour.UniversityLayer;
import Lab_1.models.University;

public class StudentService extends UniversityLayer {

    private final University university;

    public StudentService() {
        this.university = getUniversity();
    }

}
