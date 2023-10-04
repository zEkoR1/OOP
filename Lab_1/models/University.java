package Lab_1.models;

import java.util.ArrayList;
import java.util.List;

public class University {
    private List<Faculty> faculties = new ArrayList<>();

    public void addFaculty(Faculty faculty) {
        faculties.add(faculty);
    }

    public List<Faculty> getAllFaculties() {
        return this.faculties;
    }

    public void setFaculties(List<Faculty> faculties){
        this.faculties = faculties;
    }

    @Override
    public String toString() {
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < this.faculties.size(); i++)
            text.append(this.faculties.get(i) + "\n");
        return text.toString();

    }

}

