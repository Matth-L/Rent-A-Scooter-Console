
import java.io.IOException;
import java.util.ArrayList;


public class Menu4 extends Menu3Erreur {
    void choixMenu(ArrayList<Scooter> tabScooter) throws IOException {
        // menu
        mainMenu();
        System.out.print("Entrez un nombre : ");
        switch (scan.nextInt()) {
            case 1:
                // louer le scooter
                louerScooter(tabScooter);
                choixMenu(tabScooter);
                break;
            case 2:
                // retour scooter
                retourScooter(tabScooter);
                choixMenu(tabScooter);
                break;
            case 3:
                /// etat
                afficheScooter(tabScooter);
                choixMenu(tabScooter);
                break;
            case 4:
                // afficher état du parc des scooters
                afficheParc(tabScooter);
                choixMenu(tabScooter);
                break;
            case 5:
                // saisie du parc des scooters
                afficheStatParc(tabScooter);
                choixMenu(tabScooter);
                break;
            case 6:
                // quand on quitte ça sauvegarde dans la bd avant
                BaseDonne.saveDB(tabScooter);
                break;
            default:
                System.out.println("Veuillez entrez un nombre correcte ... ");
                choixMenu(tabScooter);
                break;
        }
    }

}
