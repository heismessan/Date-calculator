import java.util.Scanner;

public class MainApp{
    public static void main(String [] args){
        Scanner scanner = new Scanner(System.in);
        
        try{
            message();
            int choix = obtenirChoix(scanner);
            System.out.println(choix);
            if(choix == 1){
                int annee = verifieAnnee(scanner);
                boolean resultat = calculBissextile(annee);
                if(resultat){
                    System.out.println("L'année " + annee + " est bissextile.");
                }else{
                    System.out.println("L'année " + annee + " n'est pas bissextile.");
                }
                
            }else{
                int annee = verifieAnnee(scanner);
                int mois = valideMois(scanner);
                int jour = verifieJour(scanner, mois, annee);
                System.out.println("Votre date est: " + jour + "-" + mois + "-" + annee);
                int h = rechercheJour(jour, mois, annee);
                String dateTrouvée = jourDate(h);
                System.out.println("Et c'est un " + dateTrouvée);
            }
        }finally{
            scanner.close();
        }    
    }


// nouvelles méthodes ici
    public static int obtenirChoix(Scanner scanner){
        // message();
        while(!scanner.hasNextInt()){
            message();
            scanner.next();
        }
        int a = scanner.nextInt();
        while(a != 1 && a != 2){
            if(!scanner.hasNextInt()){
                message();
                scanner.next();
            }else{
                message();
                a = scanner.nextInt();
            }
        }
        return a;        
    }

    public static void message(){
        System.out.println("Choisissez 1 pour année bissextile ou 2 pour le jour d'une date: ");
    }

    public static void message2(String texte){
        System.out.println("Saisissez votre " + texte + ": ");
    }

    public static void errorMessage(String texte){
        System.out.println("Erreur de saisie " + texte + ". Réessayer!");
    }

    public static int verifieAnnee(Scanner scanner){
        message2("année");
        while(!scanner.hasNextInt()){
            errorMessage("de l'année");
            scanner.next();
        }
        return scanner.nextInt();
    }


    public static boolean calculBissextile(int annee){
        if(annee % 400 == 0){
            return true;
        }else if(annee % 100 == 0){
            return false;
        }else if(annee % 4 == 0){
            return true;
        }else{
            return false;
        }
    }


    public static int valideMois(Scanner scanner){
        message2("mois");
        int mois;
        while(!scanner.hasNextInt()){
            message2("mois");
            scanner.next();
        }
        mois = scanner.nextInt();
        while(mois < 1 || mois > 12){
            errorMessage("du mois");
            while(!scanner.hasNextInt()){
                message2("année");
                scanner.next();
            }
            mois = scanner.nextInt();
        }
        return mois;
    }

    public static int verifieJour(Scanner scanner, int mois, int annee){
        boolean anneeChoisie = calculBissextile(annee);
        // int moisChoisis = valideMois(mois);
        int [] tab = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        if(!anneeChoisie){
            tab[1] = 28;
        }
        // Vérification de la saisie du jour avant validation
        message2("jour");
        while(!scanner.hasNextInt()){
            errorMessage("du jour");
            scanner.next();
        }
        // Plage de validation du jour
        int jourAccepte = scanner.nextInt();
        while(jourAccepte < 1 || jourAccepte > tab[mois - 1]){
            errorMessage("jour");
            while(!scanner.hasNextInt()){
                errorMessage(" du jour");
                scanner.next();
            }
            jourAccepte = scanner.nextInt();
        }
        return jourAccepte;   
    }

    public static int rechercheJour(int jour, int mois, int annee){
        if(mois == 1){
            mois = 13;
            annee--;
        }else if(mois == 2){
            mois = 14;
            annee--;
        }   
        // variables pour la formule
        int q = jour;
        int m = mois;
        int a = annee;
        int k = a % 100;
        int j = a / 100;

        // Calcul du jour de la date

        int h = (q + (13 * (m + 1)/5) + k + (k/4) + (j/4) - 2 * j) % 7;
        if(h < 0){
            h += 7;
        }
        return h;
    }
    
    public static String jourDate(int h){
        String[] jour = {"Samedi", "Dimanche", "Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi"};
        return jour[h];
    }
}

