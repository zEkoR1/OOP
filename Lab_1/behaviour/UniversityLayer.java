package Lab_1.behaviour;

import Lab_1.models.Faculty;
import Lab_1.models.University;

import java.util.List;

public class UniversityLayer {
    private final University university = new University();

    public University getUniversity() {
        return this.university;
    }

    public Faculty getFacultyByAbbr(String abbreviation) {
        List<Faculty> faculties = university.getAllFaculties();

        for (Faculty faculty : faculties) {
            if (faculty.getAbbreviation().equals(abbreviation)) {
                return faculty;
            }
        }
        return null;
    }
}
