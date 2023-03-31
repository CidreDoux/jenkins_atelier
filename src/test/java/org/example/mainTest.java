package org.example;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class mainTest {

    private Map<String, Integer> cartesEtPins;

    @Before
    public void setUp() {
        cartesEtPins = new HashMap<>();
        cartesEtPins.put("123456789012", 1234);
        cartesEtPins.put("234567890123", 2345);
        cartesEtPins.put("345678901234", 3456);
    }

    @Test
    public void testRetraitValide() {
        String input = "123456789012\n1234\n10\nsortir\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        String output = getConsoleOutput(() -> new Main());
        assertTrue(output.contains("1 billet(s) de 10 €"));
    }

    @Test
    public void testRetraitInvalide() {
        String input = "123456789012\n1234\n3\nsortir\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        // Vérifie que le message d'erreur est affiché
        String output = getConsoleOutput(() -> new Main());
        assertTrue(output.contains("Montant invalide. Veuillez entrer un montant multiple de 5."));
    }

    @Test
    public void testCarteInvalide() {
        String input = "999999999999\n1234\n10\nsortir\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        String output = getConsoleOutput(() -> new Main());
        assertTrue(output.contains("Numéro de carte ou code PIN invalide. Veuillez réessayer."));
    }

    @Test
    public void testCodePinInvalide() {
        String input = "123456789012\n9999\n10\nsortir\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        String output = getConsoleOutput(() -> new Main());
        assertTrue(output.contains("Numéro de carte ou code PIN invalide. Veuillez réessayer."));
    }

    private String getConsoleOutput(Runnable code) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        PrintStream oldPrintStream = System.out;

        System.setOut(printStream);

        code.run();

        System.out.flush();
        System.setOut(oldPrintStream);

        return outputStream.toString().trim();
    }
}