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
        System.out.println("2. ...");
        System.out.println("3. ...");
        System.out.println("4. Exit");

    }

    public String input(String in){
        this.command = in.split("\\s+");
        return this.command[0];
    }

    public String getType(){
        return this.command[1];
    }
}
