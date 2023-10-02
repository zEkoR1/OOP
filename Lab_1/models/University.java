package Lab_1.models;

import java.util.ArrayList;
import java.util.List;

public class University {
    private List<Faculty> faculties = new ArrayList<>();

    public void addFaculty(Faculty faculty) {
        faculties.add(faculty);
    }

    @Override
    public String toString() {
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < this.faculties.size(); i++)
            text.append(this.faculties.get(i) + "/n");
        return text.toString();

    }
}
