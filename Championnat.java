import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class Championnat implements Serializable {
    private static final long serialVersionUID = 1L; // ID de version pour la compatibilité de la sérialisation

    private Equipe[] equipes; // Tableau des équipes
    private Journee[] journees; // Tableau des journées

    // Constructeur
    public Championnat(String fichierEquipes, int nombreEquipes) throws IOException {
        this.equipes = lireEquipesDepuisFichier(fichierEquipes, nombreEquipes);
        this.journees = genererProgrammeChampionnat();
    }

    // Lecture des équipes depuis un fichier
    private Equipe[] lireEquipesDepuisFichier(String fichierEquipes, int nombreEquipes) throws IOException {
        ArrayList<Equipe> listeEquipes = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(fichierEquipes));
        String ligne;
        int count = 0;

        // Lecture des lignes du fichier et création des objets Equipe
        while ((ligne = reader.readLine()) != null && count < nombreEquipes) {
            listeEquipes.add(new Equipe(ligne));
            count++;
        }
        reader.close();
        if (count < nombreEquipes) {
            throw new IllegalArgumentException("Le fichier ne contient pas assez d'équipes.");
        }
        return listeEquipes.toArray(new Equipe[0]);
    }

    // Génération du programme du championnat
    private Journee[] genererProgrammeChampionnat() {
        int nombreEquipes = equipes.length;
        int nombreJournees = nombreEquipes - 1;
        int nombreMatchsParJournee = nombreEquipes / 2;
        ArrayList<Journee> listeJournees = new ArrayList<>();

        for (int i = 0; i < nombreJournees; i++) {
            Match[] matchsDuJour = new Match[nombreMatchsParJournee];
            for (int j = 0; j < nombreMatchsParJournee; j++) {
                int indexEquipe1 = (i + j) % nombreEquipes;
                int indexEquipe2 = (i + nombreEquipes - j) % nombreEquipes;
                matchsDuJour[j] = new Match(equipes[indexEquipe1], equipes[indexEquipe2], "Date à définir");
            }
            listeJournees.add(new Journee(matchsDuJour));
        }
        return listeJournees.toArray(new Journee[0]);
    }

    // Simulation du championnat
    public void simulerChampionnat() {
        for (Journee journee : journees) {
            journee.simulerJournee(); // Simulation de chaque journée
        }
    }

    // Affichage du classement
    public void afficherClassement() {
        ArrayList<Equipe> classement = new ArrayList<>();
        Collections.addAll(classement, equipes);
        classement.sort((e1, e2) -> {
            int points1 = e1.getPoints();
            int points2 = e2.getPoints();
            if (points1 != points2) return points2 - points1;
            return e2.getGoalAverage() - e1.getGoalAverage();
        });

        for (int i = 0; i < classement.size(); i++) {
            Equipe equipe = classement.get(i);
            System.out.println((i + 1) + ". " + equipe.getNom() + " - Points: " + equipe.getPoints() + ", Goal Average: " + equipe.getGoalAverage());
        }
    }

    // Sauvegarde du championnat dans un fichier
    public void sauvegarderChampionnat(String fichier) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fichier));
        oos.writeObject(this);
        oos.close();
    }

    // Chargement du championnat depuis un fichier
    public static Championnat chargerChampionnat(String fichier) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fichier));
        Championnat championnat = (Championnat) ois.readObject();
        ois.close();
        return championnat;
    }

    // Programme principal
    public static void main(String[] args) {
        Championnat championnat = null;
        try {
            File fichier = new File("championnat.ser");
            if (fichier.exists()) {
                // Charger un championnat existant
                championnat = Championnat.chargerChampionnat("championnat.ser");
                System.out.println("Championnat chargé depuis le fichier.");
            } else {
                // Créer un nouveau championnat
                int nombreEquipes = ChampionnatUtils.demanderNombreEquipes();
                championnat = new Championnat("equipes.txt", nombreEquipes);
                System.out.println("Nouveau championnat créé.");
            }

            // Simuler le championnat
            championnat.simulerChampionnat();
            championnat.afficherClassement();
            championnat.sauvegarderChampionnat("championnat.ser");
            System.out.println("Championnat sauvegardé.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
