package view;

import controller.Controller;
import model.Instrument;

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

    public CommandLine(Controller c) {
        this.controller = c;
        commands = new Commands();
        runTime();
    }

    public void runTime() {
        while (running) {
            try {
                commands.listCommands();
                switch (commands.input(scan.nextLine())) {
                    case "li":
                        String type = commands.getArgument(1);
                        ArrayList<? extends Instrument> instruments = controller.listInstrumentRental(type);
                        if (instruments.size() > 0) {
                            for (Instrument instrument : instruments) {
                                System.out.println(instrument);
                            }
                        } else {
                            System.out.println("Could not find any of those instruments");
                        }
                        break;
                    case "rent":
                        String studentId = commands.getArgument(1);
                        String instrumentId = commands.getArgument(2);
                        int amountRented = controller.checkRentPossibility(studentId);
                        if (amountRented < 2) {
                            System.out.println("\nThe student has " + amountRented + " rented and is eligable to rent the desired item. Proceed? (y/n)");
                            boolean subRun = true;
                            while (subRun) {
                                switch (scan.nextLine()) {
                                    case "y":
                                        controller.rentInstrument(studentId, instrumentId);
                                        System.out.println("student: " + studentId + "\nis now renting instrument: " + instrumentId + "\n");
                                        subRun = false;
                                        break;
                                    case "n":
                                        subRun = false;
                                        break;
                                    default:
                                        System.out.println("Unknown command...");
                                        break;
                                }
                            }
                        } else
                            System.out.println("This student already has " + amountRented + " instruments rented...");
                        break;
                    case "exit":
                        running = false;
                        System.out.println("Exiting program...");
                    default:
                        System.out.println("User inputted unknown command...");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
