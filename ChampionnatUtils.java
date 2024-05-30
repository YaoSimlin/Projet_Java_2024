import java.util.InputMismatchException;
import java.util.Scanner;

public class ChampionnatUtils {

    // Méthode pour demander le nombre d'équipes à l'utilisateur
    public static int demanderNombreEquipes() {
        Scanner scanner = new Scanner(System.in);
        int nombreEquipes = 0;
        boolean valide = false;

        // Boucle jusqu'à obtenir un nombre d'équipes valide
        while (!valide) {
            try {
                System.out.println("Entrez le nombre d'équipes (pair, entre 4 et 10) : ");
                nombreEquipes = scanner.nextInt();

                // Vérification des contraintes
                if (nombreEquipes < 4 || nombreEquipes > 10) {
                    throw new IllegalArgumentException("Le nombre d'équipes doit être compris entre 4 et 10.");
                }
                if (nombreEquipes % 2 != 0) {
                    throw new IllegalArgumentException("Le nombre d'équipes doit être pair.");
                }
                valide = true; // Si tout est correct, on sort de la boucle

            } catch (InputMismatchException e) {
                // Gestion des entrées incorrectes (non numériques)
                System.out.println("Veuillez entrer un nombre valide.");
                scanner.next(); // Pour consommer l'entrée incorrecte
            } catch (IllegalArgumentException e) {
                // Gestion des entrées qui ne respectent pas les contraintes
                System.out.println(e.getMessage());
            }
        }
        return nombreEquipes;
    }
}
