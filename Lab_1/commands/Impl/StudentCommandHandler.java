package Lab_1.commands.Impl;

import Lab_1.commands.CommandHandler;

public class StudentCommandHandler implements CommandHandler {

    @Override
    public boolean handleCommand(String[] commands, int requiredSize) {
        System.out.println(commands.length);
        if (commands.length == requiredSize) {
            return true;
        }
        return false;
    }

}
