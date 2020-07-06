public class ArrayUtils {

    //-------------Affiche le tableau en 1 ligne
    public static void printTab(int[] tab){
        for(int i=0 ; i<tab.length ; i++) {
            if (tab [i] != 0) {
                System.out.print(tab[i]+" ");
            }
        }
        System.out.print("\n");
    }


    //-------------True Length
    public static int trueLength(int[] pTab) {                         //
        int tLength = 0;
        for (int i=0; i < pTab.length; i++) {
            if (pTab[i] != 0) {
                tLength++;
            }
        }
        return tLength;
    }

    //-------------New Value                                           // Cherche un emplacement vide dans le Deck/joueur
    public static void add(int[]pTab, int pNewVal) {
        boolean empty = false;
        int indice =-1;                                                // Accéder à l'emplacement 0 du Deck
            while (!empty) {                                           // Tant que emplacement Deck Non vide,
                indice++;                                              // on avance à l'emplacement suivant dans le deck
                if (pTab [indice] == 0) {                              // Si emplacement du deck contient 0
                    empty = true;
                }
            }
            pTab[indice] = pNewVal;                                    // Place la valeur de la carte ramassée à l'emplacement dispo
    }

    //-------------Delete Value
    public static void remove(int[]pTab, int pIndiceDelValue){
        pTab [pIndiceDelValue] = 0;
    }



}
