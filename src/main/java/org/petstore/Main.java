package org.petstore;

import org.petstore.command.CommandHandler;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        CommandHandler commandHandler = new CommandHandler();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Pet Store CLI!");
        System.out.println("Enter a command to begin. Type 'exit' to quit.");

        while (true) {
            System.out.print("> ");
            String command = scanner.nextLine();
            commandHandler.handle(command);
        }
    }
}