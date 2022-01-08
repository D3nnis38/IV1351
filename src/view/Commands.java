package view;

/**
 * Author: DENNIS HADZIALIC
 * Date: 2022-01-06
 * Description:
 **/
public class Commands {
    private String[] command;
    public Commands() {

    }

    public void listCommands() {
        System.out.println("1. li <type> (List instruments with specific type)");
        System.out.println("2. rent <studentId> <instrumentId> (rent instrument with id to student with id)");
        System.out.println("3. ...");
        System.out.println("4. exit");

    }

    public String input(String in){
        this.command = in.split("\\s+");
        return this.command[0];
    }

    public String getArgument(int arg){
        if(this.command.length < arg) return null;
        return this.command[arg];
    }
}
