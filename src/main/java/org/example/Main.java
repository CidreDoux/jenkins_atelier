package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class main {
    public static void main(String[] args) { // push 3
        // Créer un Map pour stocker les informations de carte bancaire et les codes PIN
        Map<String, Integer> cartesEtPins = new HashMap<>();
        cartesEtPins.put("123456789012", 1234);
        cartesEtPins.put("234567890123", 2345);
        cartesEtPins.put("345678901234", 3456);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Entrez votre numéro de carte bancaire:");
            String carte = scanner.nextLine();

            System.out.println("Entrez votre code PIN:");
            int pin = scanner.nextInt();
            scanner.nextLine(); // Consomme le reste de la ligne

            if (cartesEtPins.containsKey(carte) && cartesEtPins.get(carte) == pin) {
                while (true) {
                    System.out.println("Entrez le montant à retirer ou tapez 'sortir' pour quitter:");
                    String input = scanner.nextLine();

                    if (input.equalsIgnoreCase("sortir")) {
                        break;
                    }

                    try {
                        int montant = Integer.parseInt(input);

                        if (montant >= 5 && montant % 5 == 0) {
                            int[] coupures = {500, 200, 100, 50, 20, 10, 5};
                            int[] quantites = new int[coupures.length];

                            for (int i = 0; i < coupures.length; i++) {
                                quantites[i] = montant / coupures[i];
                                montant %= coupures[i];
                            }

                            System.out.println("Veuillez prendre vos billets :");
                            for (int i = 0; i < coupures.length; i++) {
                                if (quantites[i] > 0) {
                                    System.out.println("- " + quantites[i] + " billet(s) de " + coupures[i] + " €");
                                }
                            }
                        } else {
                            System.out.println("Montant invalide. Veuillez entrer un montant multiple de 5.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Entrée invalide. Veuillez entrer un montant valide ou tapez 'sortir' pour quitter.");
                    }
                }
            } else {
                System.out.println("Numéro de carte ou code PIN invalide. Veuillez réessayer.");
            }
        }
    }
}
