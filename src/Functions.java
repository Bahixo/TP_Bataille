import java.util.Random;

public class Functions {

    //-------------Remplissage des Decks
    public static void initCartes (int [] pTab) {                                           //

          initRec(pTab, 0);
    }


    private static void initRec(int[]tabRec, int xCartes) {                                       // Distribution des 13 cartes
        if (xCartes <13) {
            tabRec[xCartes] = xCartes + 1;
            initRec(tabRec, xCartes + 1);
        } 
    }

    //-------------Tirage des cartes
    public static int tirerUneCarte (int [] pTab) {

        Random lIndiceRan = new Random();
        int myRandomIndice = 0;
        int theCard = 0;
        while ( theCard == 0 ) {                                                                  // Random d'une carte
            myRandomIndice = lIndiceRan.nextInt(pTab.length);
            theCard = pTab [myRandomIndice];
        }
        ArrayUtils.remove(pTab, myRandomIndice);
        return theCard;
    }


    //-------------Désignation du ou de la gagnant.e
    public static int quiGagneLeTour (int carteJ1, int carteJ2) {

        return  carteJ1 > carteJ2  ? 1 : carteJ1 < carteJ2 ? 2 : 3;
    }

    //--------------Récup des cartes par la.e gagnant.e
    public static void transfererCarte (int[] pDeck, int[]pPotCommun) {            // En Paramétres Deck + cartes jouées
        for (int i = 0; i<pPotCommun.length && pPotCommun[i] != 0; i++) {
            ArrayUtils.add(pDeck, pPotCommun[i]);                                                      // Appel de la méthode "add" qui ajoute les cartes ramassées au deck
            ArrayUtils.remove(pPotCommun, i);
        }
    }

    //--------------Noms des Cartes----------------

    public static String nameCards (int numCarte) {
        switch (numCarte) {
            /*case (numCarte>0 && numCarte<11):
                return String.valueOf(numCarte);*/

            case 1:; case 2:; case 3:; case 4:; case 5:; case 6:; case 7:; case 8:; case 9:; case 10:
                return String.valueOf(numCarte);


            case 11:
                return "Valet";


            case 12:
                return "Dame";


            case 13:
                return "Roi";


            default:
                return "Joker";
        }

    }



    //--------------Affichage du gagnant.e
    public static void afficherTour (int pGagnant, int pCarteJ1, int pCarteJ2, String pNomJ1, String pNomJ2) {
        System.out.println();                                                                           // No Comment
        switch (pGagnant) {
            case 1:
                System.out.println(pNomJ1 + " a battu le " + pCarteJ2 + " de " + pNomJ2 + " grâce à sa carte " + pCarteJ1 +" !" );
                break;
            case 2:
                System.out.println(pNomJ2 + " a battu le " + pCarteJ1 + " de " + pNomJ1 + " grâce à sa carte " + pCarteJ2 +" !");
                break;
            case 3:
                System.out.println("--BATAILLE !--\n" +
                                    "Les deux joueurs ont pioché la carte "+pCarteJ1+" !\n\n" +
                                    "Les deux joueurs piochent à nouveau..");
                Main.nbBataille++;
                break;
            default:
                System.out.println("Il y a eu une erreur, le tour va être rejoué.");
        }
        System.out.println();
    }

    //--------------BATAILLE !!!
    public static int casBataille(int[]pDeck, int[]pPot){
        int carte=0;
        for(int i=0 ; i<2 ; i++) {
            carte = tirerUneCarte(pDeck);
            ArrayUtils.add(pPot, carte);
        }
        return carte;

    }

}


