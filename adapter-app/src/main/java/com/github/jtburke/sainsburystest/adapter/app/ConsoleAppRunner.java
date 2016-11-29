package com.github.jtburke.sainsburystest.adapter.app;

import java.util.Scanner;

public class ConsoleAppRunner {
    private static final String HORIZONTAL_RULE = "============================================================";

    public static void main(String[] args) throws Exception {
        displayWelcomeMessage();
        displayCloseMessageAndWaitForUserInput();
    }

    private static void displayWelcomeMessage() {
        System.out.println("John-Terrance Burke");
        System.out.println(HORIZONTAL_RULE);
        System.out.println("Application started...");
        System.out.println(HORIZONTAL_RULE + "\n");
    }

    private static void displayCloseMessageAndWaitForUserInput() {
        System.out.println("\n" + HORIZONTAL_RULE);
        System.out.println("Press [Enter] to close.");
        new Scanner(System.in).nextLine();
    }
}
