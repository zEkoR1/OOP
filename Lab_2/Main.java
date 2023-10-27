package Lab_2;

import Lab_2.behaviour.ApplicationContext;

public class Main {
    private static final String FOLDER_PATH = "C:\\Projects\\Java\\OOP\\Lab_2\\fileFolder";

    public static void main(String[] args) {
        new ApplicationContext(FOLDER_PATH).run();
    }
}