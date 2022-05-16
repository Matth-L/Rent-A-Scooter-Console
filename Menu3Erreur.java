
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

// possède tt les fonction ou le mot invalide est dedans 
public class Menu3Erreur extends Menu2Option {

    static Scanner scan = new Scanner(System.in);

    void invalideIdLocation(ArrayList<Scooter> tabScooter) throws IOException {
        System.out.println(
                "Id invalide\n vous pouvez : 1) rentrer un autre id \n 2) rentourner au menu \n ");
        switch (scan.nextInt()) {
            case 1:
                flushS();
                louerScooter(tabScooter);
                break;
            case 2:
                // retourner au menu
                flushS();
                break;
            default:
                flushS();
                System.out.println("valeurs rentrée incorecte, retour au menu.");
                break;
        }
    }

    void invalideDateLocation(Scooter S, ArrayList<Scooter> tabScooter) throws IOException {
        System.out.println(
                "non disponible à la date rentrée ou date invalide.\n vous pouvez : 1) rentrer une autre date \n 2) rentrer un autre id \n 3) retourner au menu");
        switch (scan.nextInt()) {
            case 1:
                // rentrer une nvelle date
                flushS();
                louerDate(S, tabScooter);
                break;
            case 2:
                // rentrer un nouvel id
                flushS();
                louerScooter(tabScooter);
                break;
            case 3:
                // retourner au menu
                flushS();
                break;
            default:
                flushS();
                System.out.println("valeurs rentrées incorecte, retour au menu.");
                break;
        }
    }

    void invalideNumRetour(Scooter S, ArrayList<Scooter> tabScooter) throws IOException {
        System.out.println(
                "numéro de location invalide pour le scouteur rentré \n vous pouvez : 1) rentrer un autre numéro \n 2) rentrer un autre id \n 3) retourner au menu");
        switch (scan.nextInt()) {
            case 1:
                // rentrer une nouvelle date
                flushS();
                retourDate(S, tabScooter);
                break;
            case 2:
                // rentrer un nouvel id
                flushS();
                louerScooter(tabScooter);
                break;
            case 3:
                // retourner au menu
                flushS();
                break;
            default:
                flushS();
                System.out.println("valeurs rentrées incorecte, retour au menu.");
                break;
        }
    }

    // message d'erreur
    void invalideScootRetour(ArrayList<Scooter> tabScooter) throws IOException {
        System.out.println("Ce scooter n'est pas dans la base de donnée");
        System.err.println(
                "Que voulez-vous faire: \n 1) rentrer une autre id \n 2) retourner au menu \n");
        switch (scan.nextInt()) {
            case 1:
                flushS();
                retourScooter(tabScooter);
                break;
            case 2:
                // retourner au menu
                flushS();
                break;
            default:
                flushS();
                System.out.println("valeurs rentrée incorecte, retour au menu.");
                break;
        }

    }

    void louerDate(Scooter S, ArrayList<Scooter> tabScooter) throws IOException {

        System.out.println("entrer 2 dates dans le format mm/jj/ann");
        System.out.printf("date de location : ");

        Date debutDate = Location.stringToDate(scan.next());

        System.out.println();
        System.out.printf("date de retour : ");

        Date finDate = Location.stringToDate(scan.next());

        if (S.isDispo(debutDate, finDate) && (debutDate != null || finDate != null)) {
            S.tabLocation.add(new Location(debutDate, finDate, S.getId()));

        } else {
            System.out.println(S.isDispo(debutDate, finDate));
            invalideDateLocation(S, tabScooter);
        }

    }

    void retourDate(Scooter S, ArrayList<Scooter> tabScooter) throws IOException {
        System.out.println("entrez le numéro de location");
        Location l;
        Date finDate = null;
        l = S.verifNumR(scan.nextInt());
        if (l != null) {
            System.out.printf("date de retour : ");
            while (finDate == null) {

                finDate = Location.stringToDate(scan.next());
                if (finDate == null) {
                    System.out.println("veillez rentrer une date valide :");
                }
            }
            l.setDateFin(finDate);
        } else {
            invalideNumRetour(S, tabScooter);
        }
    }

    // louer un scouteur avec une id rentré par l'utilisateur
    public void louerScooter(ArrayList<Scooter> tabScooter) throws IOException {
        Scooter S = getScooter(tabScooter, demandeId());
        if (S != null) {
            louerDate(S, tabScooter);
            clearBoard();

        } else {
            // On traite le cas d'une mauvaise touche comme un retour au menu
            invalideIdLocation(tabScooter);
        }
    }

    /*
     * fct qui permet de rendre un scouteur ap la location en donnant l'id du
     * scooter puis en rentrant les km parcouru
     */
    void retourScooter(ArrayList<Scooter> tabScooter) throws IOException {
        Scooter S;
        S = getScooter(tabScooter, demandeId());
        if (S != null) {
            // retour
            retourDate(S, tabScooter);
            clearBoard();
        } else {
            invalideScootRetour(tabScooter);
        }
    }

}
