import java.io.Serializable;

public class Journee implements Serializable {
    private Match[] matchs;

    public Journee(Match[] matchs) {
        this.matchs = matchs;
    }

    public void simulerJournee() {
        for (Match match : matchs) {
            match.simulerScore();
        }
    }

    public Match[] getMatchs() {
        return matchs;
    }
}
