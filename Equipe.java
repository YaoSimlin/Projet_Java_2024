import java.io.Serializable;

public class Equipe implements Serializable {
    private static final long serialVersionUID = 1L; // ID de version pour la compatibilité de la sérialisation

    private String nom; // Nom de l'équipe
    private int victoires; // Nombre de victoires
    private int defaites; // Nombre de défaites
    private int matchsNuls; // Nombre de matchs nuls
    private int butsMarques; // Nombre de buts marqués
    private int butsEncaisses; // Nombre de buts encaissés
    private int points; // Nombre de points

    // Constructeur
    public Equipe(String nom) {
        this.nom = nom;
        this.victoires = 0;
        this.defaites = 0;
        this.matchsNuls = 0;
        this.butsMarques = 0;
        this.butsEncaisses = 0;
        this.points = 0;
    }

    // Getters et setters

    public String getNom() {
        return nom;
    }

    public int getVictoires() {
        return victoires;
    }

    public void addVictoire() {
        this.victoires++;
        this.points += 3; // 3 points pour une victoire
    }

    public int getDefaites() {
        return defaites;
    }

    public void addDefaite() {
        this.defaites++;
    }

    public int getMatchsNuls() {
        return matchsNuls;
    }

    public void addMatchNul() {
        this.matchsNuls++;
        this.points += 1; // 1 point pour un match nul
    }

    public int getButsMarques() {
        return butsMarques;
    }

    public void addButsMarques(int buts) {
        this.butsMarques += buts;
    }

    public int getButsEncaisses() {
        return butsEncaisses;
    }

    public void addButsEncaisses(int buts) {
        this.butsEncaisses += buts;
    }

    public int getPoints() {
        return points;
    }

    public int getGoalAverage() {
        return butsMarques - butsEncaisses; // Différence de buts (goal average)
    }
}
