import java.lang.reflect.Field;
import java.util.Scanner;

public class Main {
    public static int nbBataille=0;

    public static void main(String[] args) {



        //------------------------------------------------------------Initialisation Scans & Variables----------------

        Scanner entréeClavier = new Scanner(System.in);

        boolean gameOver = false;
        boolean newPlayer = true;
        boolean newGame = true;
        int nbTour = 0;
        long temps= System.currentTimeMillis();                                 // Methode qui retourne l'heure en milliseconde



        //------------------------------------------------------------Démarrage du Jeu---------------------------------

        System.out.println("\n\n\n\n\n\n\n");
        System.out.println("-------------------------------------------------------------------------------------------------------------\n" +
                           "---------------------------------------------JEU DE LA BATAILLE----------------------------------------------\n" +
                           "-------------------------------------------------------------------------------------------------------------\n");


                //--------------------------Entrer Nom des joueurs--------------------

        while (newPlayer) {                                                     // Tant que newPlayer répond "Y" --> true --> on relance la boucle
            newGame = true;                                                     //

            System.out.println("\n\n\n\n");

            System.out.println("Entrez le nom du joueur 1 :");
            Joueur joueur1 = new Joueur();                                      //  Création d'une instance de Joueur (Instanciation d'objet ==  allocation et initialisation)
            joueur1.nomJoueur = entréeClavier.nextLine();                       //  Enregistre la valeur de la saisie clavier dans l'objet nomJoueur
            joueur1.win = 0;                                                    //  Initialisation de l'instance Win --> nombre de victoire

            System.out.println("Entrez le nom du joueur 2 :");
            Joueur joueur2 = new Joueur();
            joueur2.nomJoueur = entréeClavier.nextLine();
            joueur2.win = 0;

            System.out.println( "\n------- " + joueur1.nomJoueur + " et " + joueur2.nomJoueur + " êtes-vous prêt.es à commencer ?! -------" +
                                "\n             Que la.e meilleur.e gagne !");


                //-------------------------Init des cartes--------------------------

            while (newGame) {
                int roundEnCours = joueur1.win + joueur2.win + 1;               // Compte le nombre de Tours du round (sans oublier le tour 0)
                gameOver = false;
                nbTour = 0;

                joueur1.deck = new int[26];                                     // 26 emplacements de prévu pour récup cartes
                Functions.initCartes(joueur1.deck);                             // Distribution des cartes

                joueur2.deck = new int[26];
                Functions.initCartes(joueur2.deck);

                System.out.println("\n" + "--------------------------------------------------ROUND " + roundEnCours + "--------------------------------------------------" +
                                    "\n   Appuyer sur entrer pour commencer la partie....");
                entréeClavier.nextLine();

                temps = System.currentTimeMillis();

        //--------------------------------------------------------Début des Rounds----------------------------------

                while (!gameOver) {                                             // Tant que joueur


                    boolean looseJ2 = false;
                    boolean looseJ1 = false;

                    int carteJ1 = Functions.tirerUneCarte(joueur1.deck);
                    int carteJ2 = Functions.tirerUneCarte(joueur2.deck);

                    int[] potCommun= new int [26];
                    potCommun[0]=carteJ1;
                    potCommun[1]=carteJ2;



                //-----------------------------------Comparaison des cartes-----------
                    while(Functions.quiGagneLeTour(carteJ1, carteJ2)==3){
                        Functions.afficherTour(Functions.quiGagneLeTour(carteJ1, carteJ2), carteJ1, carteJ2, joueur1.nomJoueur, joueur2.nomJoueur);
                        carteJ1=Functions.casBataille(joueur1.deck, potCommun);
                        carteJ2=Functions.casBataille(joueur2.deck, potCommun);
                    }


                    switch (Functions.quiGagneLeTour(carteJ1, carteJ2 )) {
                        case 1:
                            Functions.transfererCarte(joueur1.deck, potCommun);
                            nbTour++;
                            break;

                        case 2:
                            Functions.transfererCarte(joueur2.deck, potCommun);
                            nbTour++;
                            break;

                        case 3:
                            nbTour++;
                            break;

                        default:
                    }

                        Functions.afficherTour(Functions.quiGagneLeTour(carteJ1, carteJ2), carteJ1, carteJ2, joueur1.nomJoueur, joueur2.nomJoueur);

                //-----------------------------------Résumé du Tour------------------

                    looseJ1 = ArrayUtils.trueLength(joueur1.deck) == 0;
                    looseJ2 = ArrayUtils.trueLength(joueur2.deck) == 0;


                    System.out.println("Il reste " + ArrayUtils.trueLength(joueur1.deck) + " carte(s) à " + joueur1.nomJoueur + "." +
                                        "\nIl reste " + ArrayUtils.trueLength(joueur2.deck) + " carte(s) à " + joueur2.nomJoueur + ".");


                    if (looseJ1 && !looseJ2) {
                        joueur2.win++;
                        System.out.println("\n"+joueur2.nomJoueur + " à gagné en " + nbTour + " tour(s); le round a duré " + ((double)(System.currentTimeMillis()-temps)/1000) + " secondes");
                    } else if  (looseJ2 && !looseJ1) {
                        joueur1.win++;
                        System.out.println("\n"+joueur1.nomJoueur + " à gagné en " + nbTour + " tour(s); le round a duré " + ((double)(System.currentTimeMillis()-temps)/1000) + " secondes");
                    }


                    gameOver = looseJ1 || looseJ2;
                }
                System.out.println("Il y a eu "+nbBataille+" bataille(s) lors de ce round !");
                nbBataille=0;
                System.out.println("\n" + joueur1.nomJoueur + " à " + joueur1.win + " victoire(s)\n" +
                                   joueur2.nomJoueur + " à " + joueur2.win + " victoire(s)");


                System.out.println("\nVoulez-vous prendre votre revanche ? (y/n)");
                newGame = entréeClavier.nextLine().equals("y");

            }

            if (joueur1.win > joueur2.win) {
                System.out.println(joueur1.nomJoueur + " a gagné.e la partie avec " + joueur1.win + " victoire(s)");
            } else if (joueur2.win > joueur1.win) {
                System.out.println(joueur2.nomJoueur + " a gagné.e la partie avec " + joueur2.win + " victoire(s)");
            } else {
                System.out.println("La partie était serrée. Après " + (joueur1.win * 2) + " round(s), c'est une égalité.");
            }

            System.out.println("\nVoulez-vous commencer une nouvelle partie avec des nouveaux joueurs ? (y/n)");
            newPlayer = entréeClavier.nextLine().equals("y");

        }
        System.out.println("\n\n\n\n\n\n\n");
        System.out.println("-------------------------------------------------------------------------------------------------------------\n" +
                           "------------------------------------------Merci d'avoir joué !-----------------------------------------------\n" +
                           "-------------------------------------------------------------------------------------------------------------\n" +
                           "--------------------------------------------------By---------------------------------------------------------\n" +
                           "------------------------Amandine-------------------&-----------------------------Joel------------------------\n" +
                           "-------------------------------------------------------------------------------------------------------------\n");

    }
}