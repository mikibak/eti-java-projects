package org.example;

import java.util.ArrayList;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        MageController mageController = new MageController(new MageRepository(new ArrayList<>()));
        System.out.println(mageController.save("Mamasz Dziubale", "80"));
        System.out.println(mageController.save("Mamasz Dziubale", "80"));
    }
}