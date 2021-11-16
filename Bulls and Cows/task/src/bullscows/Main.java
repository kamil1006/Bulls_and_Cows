package bullscows;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    //*******************************************************************************

    public static String[] codes ={"0","1","2","3","4","5","6","7","8","9"
                    ,"a","b","c","d","e","f","g","h","i","j"
                    ,"k","l","m","n","o","p","q","r","s","t"
                    ,"u","v","w","x","y","z"};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);



        int i = 0;// = scanner.nextInt();
        int j = 0;
        String secretCodeStr ="";

        boolean ifNotOk = true;
        boolean isOk=true;
        // ------------------------------------
        //while (ifNotOk)
        {
            //System.out.println("Please, enter the secret code's length:");
            System.out.println("Input the length of the secret code:");
            String s="";
           // boolean isOk=true;
            try {

                 s = scanner.nextLine();
                i = Integer.parseInt(s);
            }
            catch (Exception e){

                System.out.println("Error: \""+s+"\" isn't a valid number.yyy");
                isOk=false;

            }
            if(i==0) {
                isOk=false;
                System.out.println("Error: \""+s+"\" isn't a valid number!!!!!.");
            }
            if(isOk) {
                System.out.println("Input the number of possible symbols in the code:");
                try {
                    s = scanner.nextLine();
                    j = Integer.parseInt(s);
                } catch (Exception e) {
                    System.out.println("Error: \"" + s + "\" isn't a valid number.xxx");
                    isOk=false;
                }

                if(j==0)  {
                    isOk=false;
                    System.out.println("Error: \""+s+"\" isn't a valid number.+++");
                }

            }

            if(isOk) {
                if (i > 36 || j > 36) {
                    //System.out.println("Error: can't generate a secret number with a length of "+ i+" because there aren't enough unique digits.");
                    if (j > 36) {
                        System.out.println("Error: maximum number of possible symbols in the code is 36 (0-9, a-z).");
                        isOk=false;
                    } else {
                        System.out.println("Error: can't generate a secret code.");
                        isOk=false;
                    }

                } else if (i > j) {
                    System.out.println("Error: it's not possible to generate a code with a length of " + i + " with " + j + " unique symbols.");
                    isOk=false;
                }// else if(i == 0 || j == 0 ){
                   // System.out.println("Error: can't generate a secret code.");
                //}
                else
                {
                    secretCodeStr = generateRandomWithLetters(i, j);
                    //System.out.println("The random secret number is " + secretCodeStr);
                    System.out.println(printInfo(i, j));
                    System.out.println("Okay, let's start a game!");
                    ifNotOk = false;
                }
            }
        }
        // ------------------------------------

        if(isOk) {
            //System.out.println(secretCodeStr);
            int bulls = 0;
            int cows = 0;
            int iterations = 1;
            while (bulls < i) {
                //while (iterations<2){
                System.out.println();
                System.out.println("Turn " + iterations + ":");


                String s = scanner.nextLine();
                int[] answer = compateTables(secretCodeStr, s);
                bulls = answer[0];
                cows = answer[1];
                printResults(answer);


                iterations++;

            }
            //}
            System.out.println("");
            //System.out.println("Congrats! The secret code is "+ secretCodeStr);
            // System.out.println(" The secret code is "+ secretCodeStr);
            System.out.println("Congratulations! You guessed the secret code.");
        }
        // ------------------------------------

    }

    private static String printInfo(int i, int j) {

        String message = "The secret is prepared: ";

        for(int x = 0 ; x<i; x++)
            message = message +"*";

        message = message +" (";
        String a = "";
        String b = "";
        if(j>10){
            a="0"+"-9,";
            if(j == 11){
                b = Main.codes[j-1];
            }else {
                b = "a-"+Main.codes[j-1];
            }

        }else if(j==1){
            a="0";
        }
        else a="0"+"-"+Main.codes[j-1];

        message =message + a + b ;
        message = message +").";
        return message;
    }

    //*******************************************************************************
    private static void printResults(int[] answer) {
        String message;
        String a = "";
        String b = "";
        if (answer[0] == 1 ) a = answer[0] + " bull";
        else
        if (answer[0] > 1 )
            a = answer[0] + " bulls";

        if (answer[1] == 1 ) b = answer[1] + " cow";
        else
            if (answer[1] > 1 )
                b = answer[1] + " cows";

            if( a.length() > 0 && b.length() > 0)
                message ="Grade: "+ a + " and " + b +"";
            else if(a.length() == 0 && b.length() == 0){
                message ="Grade: None";
            }else {
                message = "Grade: " + a + b + "";
            }

        //System.out.println(message);
        System.out.print(message);
    }
    //*******************************************************************************
    private static int[] compateTables(String secretCodeStr, String s) {
        int[] answer = new int[2];
        answer[0]=0;
        answer[1]=0;

        int l = s.length();

        for(int i = 0 ;i < l ; i++){
            if(secretCodeStr.charAt(i)==s.charAt(i)){
                //busly
                    answer[0]++;
            }else if(secretCodeStr.contains(String.valueOf(s.charAt(i)))){
                answer[1]++;
            }
        }


        return answer;
    }
    //*******************************************************************************
    public static String generateRandomWithLetters(int n, int m){

        String secretCodeStr = "";
        for( int i = 0 ; i < n ; i++) {
            double i1 = Math.random() * m;
            int i11 = (int) i1;
            String value = Main.codes[i11];
            if(secretCodeStr.contains(value) ){
                boolean repeat =true;
                while (repeat){
                    i1 = Math.random() * m;
                    i11 = (int) i1;
                    value = Main.codes[i11];
                    if(secretCodeStr.contains(value) )
                        repeat = true;
                    else repeat = false;
                }

            }
            secretCodeStr = secretCodeStr +Main.codes[i11];
        }
        return secretCodeStr;
    }

    //*******************************************************************************
    public static String generateRandom(int n){

        String secretCodeStr = "";
        for( int i = 0 ; i < n ; i++) {
            double i1 = Math.random() * 10;
            int i11 = (int) i1;
            if(secretCodeStr.contains(String.valueOf(i11)) || (i == 0 && i11 == 0)){
                boolean repeat =true;
                while (repeat){
                    i1 = Math.random() * 10;
                    i11 = (int) i1;
                    if(secretCodeStr.contains(String.valueOf(i11)) || (i == 0 && i11 == 0))
                       repeat = true;
                    else repeat = false;
                }

            }
            secretCodeStr = secretCodeStr +i11;
        }
        return secretCodeStr;
    }
    //*******************************************************************************
    public static String generateRandomOld(int n){

        int size = n;

        String secretCodeStr = "";
        for( int i = 0 ; i < n ; i++){
            double i1 = Math.random()*10;
            int i11 = (int) i1;

            if(secretCodeStr.contains(String.valueOf(i11)) || (i == 0 && i11 == 0)){
                boolean repeat =true;
                double i1a = 0;
                int i11a = 0;
                while (!repeat){
                    i1a = Math.random()*10;
                    i11a = (int) i1;
                    if(!(secretCodeStr.contains(String.valueOf(i11)) || (i == 0 && i11 == 0)) ){
                        repeat = true;
                    }
                }

                secretCodeStr = secretCodeStr +i11a;

            }else{

                secretCodeStr = secretCodeStr +i11;
            }


        }

        return secretCodeStr;
    }

    //*******************************************************************************
    public void stage2(){

        Scanner scanner = new Scanner(System.in);

        int size = 4;
        int[] secretCode = new int[size];
        String secretCodeStr = "";
        for( int i = 0 ; i < secretCode.length ; i++){
            double i1 = Math.random()*10;
            int i11 = (int) i1;

            if(secretCodeStr.contains(String.valueOf(i11))){
                boolean repeat =true;
                double i1a = 0;
                int i11a = 0;
                while (!repeat){
                    i1a = Math.random()*10;
                    i11a = (int) i1;
                    if(!secretCodeStr.contains(String.valueOf(i11))){
                        repeat = true;
                    }
                }
                secretCode[i] = i11a;
                secretCodeStr = secretCodeStr +i11a;

            }else{
                secretCode[i] = i11;
                secretCodeStr = secretCodeStr +i11;
            }


        }


        System.out.println("The secret code is prepared: ****.");
      /*
        System.out.println("***********");
        Arrays.stream(secretCode).forEach(x->System.out.print(x));
        System.out.println("");
        System.out.println("***********");


       */
        // secretCodeStr = "9876";
       // secretCodeStr = "9305";

        int bulls = 0;
        int cows = 0;
        int iterations = 1;
        //while (bulls<4){
        while (iterations<2){
            System.out.println();
            System.out.println("Turn "+ iterations +". Answer:");
            /*
            if(iterations == 1){

                String s = "1234";
                System.out.println(s);
                int[] answer = compateTables(secretCodeStr, s);
                bulls = answer[0];
                cows = answer[1];
                printResults(answer);

            }else
                if(iterations == 2){

                String s = secretCodeStr;
                System.out.println(s);
                int[] answer = compateTables(secretCodeStr, s);
                bulls = answer[0];
                cows = answer[1];
                printResults(answer);

            }

            else

             */
            // {
            String s = scanner.nextLine();
            int[] answer = compateTables(secretCodeStr, s);
            bulls = answer[0];
            cows = answer[1];
            printResults(answer);
            //}

            iterations++;

        }
        //}
        //System.out.println("Congrats! The secret code is "+ secretCodeStr);
        System.out.println(" The secret code is "+ secretCodeStr);

    }
    //*******************************************************************************

}
