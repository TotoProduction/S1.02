import extensions.File;
import extensions.CSVFile;

class PatatoQuiz extends Program{
    /*
    ______________________________________________________

    Initialisation des variable globale et du type matière
    ______________________________________________________
    
    */

    String couleur=ANSI_GREEN;
    String Blanc=ANSI_WHITE;
    String quest=ANSI_YELLOW;
    String hauteur="\n\n\n\n\n\n\n\n\n\n\n\n";
    String Largeur="                                                                                     ";
    String Largeur2="                                                ";
    String hauteur_sous_rep="\n\n\n\n\n\n\n\n\n\n";
    int tailleScoreBoard=rowCount(loadCSV("Classement/classement.csv"));
    
    String titre= Blanc+
        Largeur2+" 888888ba             dP              dP             .88888.            oo                   "+'\n'+            
        Largeur2+" 88    `8b            88              88            d8'   `8b                                "+'\n'+                         
        Largeur2+"a88aaaa8P' .d8888b. d8888P .d8888b. d8888P .d8888b. 88     88  dP    dP dP d888888b d888888b "+'\n'+
        Largeur2+" 88        88'  `88   88   88'  `88   88   88'  `88 88  db 88  88    88 88    .d8P'    .d8P' "+'\n'+
        Largeur2+" 88        88.  .88   88   88.  .88   88   88.  .88 Y8.  Y88P  88.  .88 88  .Y8P     .Y8P    "+'\n'+
        Largeur2+" dP        `88888P8   dP   `88888P8   dP   `88888P'  `8888PY8b `88888P' dP d888888P d888888P "+'\n';
    
    //Initialisation des menus de couleur differentes pour chaque interaction avec l'utilisateur

    String Solo=   '\n'+ 
                           titre+'\n'+ 
                   Largeur+"                    "+'\n'+
                   Largeur+"                    "+'\n'+ Largeur+
       couleur +           "  ◄    Solo     ►   "+'\n'+Blanc + Largeur+
                           "  ◄ Multijoueur ►   "+'\n'+ Largeur+
                           "  ◄  Catégorie  ►   "+'\n'+ Largeur+
                   Largeur+"                    "+'\n'+ Largeur+ 
                           "  ◄   Quitter   ►   "+'\n'+ Largeur+
                           
                   hauteur;

    String Multijoueur=""+'\n'+ 
                   titre+'\n'+
           Largeur+"                    "+'\n'+
           Largeur+"                    "+'\n'+Largeur+
                   "  ◄    Solo     ►   "+'\n'+Largeur+
       couleur +   "  ◄ Multijoueur ►   "+'\n'+Blanc +Largeur+
                   "  ◄  Catégorie  ►   "+'\n'+Largeur+
                   "                    "+'\n'+Largeur+
                   "  ◄   Quitter   ►   "+'\n'+
                   hauteur;
    String Categorie='\n'+ 
                   titre+'\n'+ Largeur+
                   "                    "+'\n'+ Largeur+
                   "                    "+'\n'+ Largeur+
                   "  ◄    Solo     ►   "+'\n'+ Largeur+
                   "  ◄ Multijoueur ►   "+'\n'+ Largeur+
       couleur +   "  ◄  Catégorie  ►   "+'\n'+Blanc + Largeur+
                   "                    "+'\n'+ Largeur+
                   "  ◄   Quitter   ►   "+'\n'+
                   hauteur;
    String Quit=""+'\n'+ 
                   titre+'\n'+Largeur+
                   "                    "+'\n'+Largeur+
                   "                    "+'\n'+Largeur+
                   "  ◄    Solo     ►   "+'\n'+Largeur+
                   "  ◄ Multijoueur ►   "+'\n'+Largeur+
                   "  ◄  Catégorie  ►   "+'\n'+Largeur+
       couleur +   "                    "+'\n'+Largeur+
       couleur +   "  ◄   Quitter   ►   "+'\n'+Blanc+
                   hauteur;

    String[] Home=new String[] {Solo,Multijoueur,Categorie,Quit};

    
    //La fonction newMatiere permet d'initialiser une variable du type Matiere
     

    Matiere newMatiere(String nom, int nb_question){
        Matiere mat=new Matiere();
        mat.nom=nom;
        mat.question_reponse=new String[nb_question][6];
        return mat;
    }
    Scoreboard newScoreboard(){
        Scoreboard score=new Scoreboard();
        score.scoreboard=new String[tailleScoreBoard+1][2];
        score.taille=0;
        return score;
    }

    Scoreboard chargerScoreboard(CSVFile fichier,Scoreboard scores){
        int taille=rowCount(fichier);
        for (int lig=0;lig<taille;lig++){
            scores.scoreboard[lig][0]=getCell(fichier,lig,0);
            scores.scoreboard[lig][1]=getCell(fichier,lig,1);
            scores.taille+=1;
        }
        return scores;
    }
    /*
    ________________________________________________________________________________

    Programme permettant la création d'un fichier CSV prêt pour l'utilisation en jeu
    ________________________________________________________________________________
    
    */

    
    //La fonction addMatiere permet de remplir le double tableau question_reponse de la variable matiere passée en paramètre
     

    Matiere AddMatiere(Matiere mat, String[][] q){
        for (int i=0;i<length(q,1);i++){
            for(int j=1;j<5;j++){
                mat.question_reponse[i][j]=q[i][j];
            }
            mat.question_reponse[i][0]=q[i][0];
            mat.question_reponse[i][5]=q[i][5];
        }
        return mat;
    }

    
    //La fonction CreerMatiere permet de creer un fichier CSV contenant toute les question ainsi que les reponse associé à la matière que l'on souhaite créer
    
    void CreerMatiere(){
        println("Entrez le nom de la matière");
        String matiere=readString();
        println("Entrez le nombre de question que vous souhaitez poser");
        int nbr_q=readInt();
        Matiere a= newMatiere(matiere, nbr_q);
        String[][] q=new String[nbr_q][6];
        for(int nbq=0;nbq<nbr_q;nbq++){
            println("Entrez une question");
            q[nbq][0]=readString();
            for (int i=1;i<5;i++){
                println("Entrez la reponse n°"+i);
                q[nbq][i]=readString();
            }
            println("Entrez le numéro correspondant à la bonne reponse");
            int rep=readInt();
            q[nbq][5]=q[nbq][rep];
            AddMatiere(a,q);
            }
        saveCSV(q,"Matiere/"+matiere+".csv");
        println("La matiere à bien été créé");

    }
    

    /*
    _____________________________________________________________________________________

    Programmes permettant le lancement et le fonctionnement du mode "Solo" de PatatoQuizz
    _____________________________________________________________________________________
    
    */
    
    // La fonction Choix_Matiere affiche tout les fichier CSV du repertoire Matiere et renvoie celui avec lequel l'utilisateur veut jouer

    CSVFile Choix_Matiere(){
        int rep=0;
        String[] Matiere=getAllFilesFromDirectory("Matiere");
        do{
        println("Avec quelle matiere voulez-vous jouer ?");
        println("Entrez le chiffre correspondant à la matière");
        for(int mat=0;mat<length(Matiere);mat++){
            println(mat+") "+ Matiere[mat]);
        }
        rep=readInt();
        }while(rep<0 || rep>length(Matiere));
        CSVFile fichier=loadCSV("Matiere/"+Matiere[rep]);
        return fichier;
    }

    // Ce programme verifie que la valeur entré par l'utilisateur est comprise dans les reponses possible 

    int input(){
        int rep;
        do{
            println('\n'+"                                                             "+"Quelle reponse choisissez vous ? (entrez le chiffre noté à gauche)"+hauteur_sous_rep);
            rep=readInt();
        }while(rep<=0  || rep>4);
        return rep;
    }

    //Ce programme permet l'affichage de la question, des reponses et verifie que l'utilisateur répond dans le temps imparti 

    int repondre(CSVFile fichier,int score){
        int nblig=rowCount(fichier);
        int nbcol=columnCount(fichier);
        for(int lig=0;lig<nblig;lig++){
            clearScreen();
            println(titre+"\n\n\n\n\n\n");
            println(quest+Largeur+getCell(fichier,lig,0)+Blanc);
            println();
            for (int col=1;col<nbcol-1;col++){
                println("   "+Largeur+ col+") "+getCell(fichier,lig,col));
            }
            
            double time1=getTime();
            int reponse=input();
            
            double time2=getTime();
            double temps=(time2-time1)/1000;
            String txtrep=getCell(fichier,lig,reponse);
            String correction=getCell(fichier,lig,5);
            if(equals(txtrep,correction)&& temps <= 5){
                 
                println("bravo C'etait la bonne reponse");
                score+=10;
            }else if(equals(txtrep,correction)&& temps > 3){
                println("Perdu, vous aviez la bonne reponse mais vous avez été trop long, vous avez mit : "+temps+" seconde");
            }else{      
                println("Perdu, la bonne reponse était : " + getCell(fichier,lig,5)+'\n'+"Mais vous avez choisi : "+ getCell(fichier,lig,reponse));
            }
        }
        return score;   
    }


    //La fonction username demande à l'utilisateur de rentrer un pseudo et le retourne

    String username(){
        String username="";
       
        do{
             println("Entrez un pseudo ! (16 caractères)");
            username=readString();
        }while(length(username)>16);
        return username;
    }

    String usernamej1(){
        String pseudo = "";
        do{
            println("Quel est le pseudo du joueur 1 ? (16 caractères MAX)");
            pseudo = readString();
        }while(length(pseudo) >= 16);
        return pseudo;
    }

    String usernamej2(){
        String pseudo = "";
        do{
            println("Quel est le pseudo du joueur 2 ? (16 caractères MAX)");
            pseudo = readString();
        }while(length(pseudo) >= 16);
        return pseudo ;
    }

    void Classement(Scoreboard scoreboard,String Username,String score){
        
        String matiere="classement";
        if(scoreboard.taille < 10){
            scoreboard.scoreboard[scoreboard.taille][0]=Username;
            scoreboard.scoreboard[scoreboard.taille][1]=score;
            scoreboard.taille+=1;
        }
            
        saveCSV(scoreboard.scoreboard,"Classement/"+matiere+".csv");
        println("Le classement à bien été créé");

    }
    

    void Solo(Scoreboard scoreboard){
        String Username="";
        String scoreU="";
        String choix="";
        int score=0;
        CSVFile fichier= Choix_Matiere();
        CSVFile fichier2=loadCSV("Classement/classement.csv");
        scoreboard=chargerScoreboard(fichier2,scoreboard);
        Username=username();
        println("C'est parti !!!");
        
        score=repondre(fichier,score);
        println("Le Score de " +Username+" est de "+score+" points !");
        scoreU=scoreU+score;
        Classement(scoreboard,Username,scoreU);
        println(scoreboard.scoreboard[0][0]+" : " + scoreboard.scoreboard[0][1]);

    }

    
    
    
    void Multi(){
        String choixj1="";
        String choixj2="";
        int scorej1 = 0;
        int scorej2 = 0 ;
        String rep = "";
        CSVFile fichier = Choix_Matiere();
        String pseudoj1 = usernamej1() ;
        String pseudoj2 = usernamej2() ;
        println(pseudoj1 + ", vous allez commencer !! Vous êtes prêts ? (oui pour continuer)");
        do{
            rep = readString();
        }while(!equals(rep, "oui"));
            
        println("C'est parti !!!");
        scorej1 = repondre(fichier, scorej1);

        println(pseudoj2 + ", vous allez continuer !! Vous êtes prêts ? (oui pour continuer)");
        do{
            rep = readString();
        }while(!equals(rep, "oui"));
            
        println("C'est parti !!!");
        scorej2 = repondre(fichier, scorej2);

        println("Le score de " + pseudoj1 + " est de " + scorej1 + " points !");
        println("Le score de " + pseudoj2 + " est de " + scorej2 + " points !");
        if(scorej1 > scorej2){
            println(pseudoj1 + " à gagné(e) la partie !! Félicitation !!");
        }else if(scorej1 == scorej2){
            println("Vous êtes à égalité parfaite !! Félicitations !!");
        }else if(scorej1 < scorej2){
            println(pseudoj2 + " à gagné(e) la partie !! Félicitation !!");
        }

    }



    /*
    _________________________________________________________

    Programmes permettant le fonctionnement du jeu en général
    _________________________________________________________
    
    */
    
    int affichMenu(){
        int n=0;
        boolean menu=true;
        char fleche='a';
        while(fleche!=' '){
            clearScreen();
            println(Home[n]);
            fleche=readChar();
            if(fleche=='n' && n < length(Home)){
                n+=1;
                println(n);
            }else if(fleche=='b' && n > 0){
                n=n-1;
            }
        }
        return n;
    }  




    /*
    _____________________________________________________________________________________

    Fonction qui demande à l'utilisateur de rentrer un caractère pour dire "Oui" ou "Non"
    et qui contrôle la saisie.
    _____________________________________________________________________________________

    */

    char verif_YN(){
        char car=' ';
        do{
            println("Voulez vous retourner au menu ? (y/n)");
            car=readChar();
        }while(car!='n' && car != 'y' && car!='N' && car != 'Y');
        return car;
    } 




    void algorithm(){
        boolean menu=true;
        

        while(menu){
            tailleScoreBoard=rowCount(loadCSV("Classement/classement.csv"));
            Scoreboard scoreboard=newScoreboard();
            int n=affichMenu();   
            if (n==0){
                Solo(scoreboard);
                char choix=verif_YN();
                if(choix == 'n' || choix =='N'){
                    menu=false;
                }

            }else if(n==1){
                Multi();
                char rep=verif_YN();
                if (rep=='n' || rep =='N'){
                    menu=false;
                }
                
            }else if(n==2){
                CreerMatiere();
                println("La matière à bien été créé !");
            }else if(n==3){
                menu=false;
            }
        }   
    }
    
    

    
        
}
