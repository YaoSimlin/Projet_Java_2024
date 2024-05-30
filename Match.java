import java.io.Serializable;
import java.util.Random;

public class Match implements Serializable {
    private static final long serialVersionUID = 1L; // ID de version pour la compatibilité de la sérialisation

    private Equipe equipe1; // Première équipe
    private Equipe equipe2; // Deuxième équipe
    private int scoreEquipe1; // Score de la première équipe
    private int scoreEquipe2; // Score de la deuxième équipe
    private String date; // Date du match

    // Constructeur
    public Match(Equipe equipe1, Equipe equipe2, String date) {
        this.equipe1 = equipe1;
        this.equipe2 = equipe2;
        this.date = date;
    }

    // Méthode pour simuler un match en générant des scores aléatoires
    public void simulerScore() {
        Random random = new Random();
        scoreEquipe1 = random.nextInt(5) + 1; // Score entre 1 et 5 pour equipe1
        scoreEquipe2 = random.nextInt(5) + 1; // Score entre 1 et 5 pour equipe2

        // Mise à jour des buts marqués et encaissés pour les deux équipes
        equipe1.addButsMarques(scoreEquipe1);
        equipe1.addButsEncaisses(scoreEquipe2);
        equipe2.addButsMarques(scoreEquipe2);
        equipe2.addButsEncaisses(scoreEquipe1);

        // Mise à jour des résultats des matchs
        if (scoreEquipe1 > scoreEquipe2) {
            equipe1.addVictoire();
            equipe2.addDefaite();
        } else if (scoreEquipe1 < scoreEquipe2) {
            equipe2.addVictoire();
            equipe1.addDefaite();
        } else {
            equipe1.addMatchNul();
            equipe2.addMatchNul();
        }
    }

    // Getters
    public Equipe getEquipe1() {
        return equipe1;
    }

    public Equipe getEquipe2() {
        return equipe2;
    }

    public int getScoreEquipe1() {
        return scoreEquipe1;
    }

    public int getScoreEquipe2() {
        return scoreEquipe2;
    }

    public String getDate() {
        return date;
    }
}
