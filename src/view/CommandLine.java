package view;

import controller.Controller;
import model.Instrument;
import model.InstrumentRental;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Author: DENNIS HADZIALIC
 * Date: 2022-01-06
 * Description:
 **/
public class CommandLine {
    private final Scanner scan = new Scanner(System.in);
    private Controller controller;
    private Commands commands;
    private boolean running = true;

    public CommandLine(Controller c){
        this.controller = c;
        commands = new Commands();
        runTime();
    }

    public void runTime(){
        while(running){
            try{
                commands.listCommands();
                switch(commands.input(scan.nextLine())){
                    case "li":
                        String type = commands.getType();
                        ArrayList<? extends Instrument> instruments = controller.listInstrumentRental(type);
                        if(instruments.size() > 0){
                            for(Instrument instrument : instruments){
                                System.out.println(instrument);
                            }
                        }
                        break;
                    default:
                        System.out.println("User inputted unknown command...");
                }
            } catch(Exception e){
                System.out.println(e.getMessage());
            }
        }
    }
}
