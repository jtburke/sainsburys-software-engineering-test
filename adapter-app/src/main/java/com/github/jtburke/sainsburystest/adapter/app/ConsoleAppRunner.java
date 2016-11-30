package com.github.jtburke.sainsburystest.adapter.app;

import com.github.jtburke.sainsburystest.adapter.sainsburys.SainsburysModule;
import com.github.jtburke.sainsburystest.domain.DomainModule;

import java.util.Scanner;

class ConsoleAppRunner {
    private static final String HORIZONTAL_RULE = "============================================================";

    public static void main(String[] args) throws Exception {
        displayWelcomeMessage();

        AppModule appModule = new AppModule(
            System.out::println,
            new DomainModule(new SainsburysModule().productAdapter()).productService()
        );

        appModule.app().listProducts("http://hiring-tests.s3-website-eu-west-1.amazonaws.com/2015_Developer_Scrape/5_products.html");

        displayCloseMessageAndWaitForUserInput();
    }

    private static void displayWelcomeMessage() {
        System.out.println("John-Terrance Burke");
        System.out.println(HORIZONTAL_RULE);
        System.out.println("Application started... (this may take a little while)");
        System.out.println(HORIZONTAL_RULE + "\n");
    }

    private static void displayCloseMessageAndWaitForUserInput() {
        System.out.println("\n" + HORIZONTAL_RULE);
        System.out.println("Press [Enter] to close.");
        new Scanner(System.in).nextLine();
    }
}
