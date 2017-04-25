//Created by Mitchell Harbour
//Function:

import java.util.*;

class Mastermind {
    int [] secret;
    int [] guess;
    int guesses;
    boolean won;
    Random rand = new Random();
    
    public Mastermind() {
        secret = new int[4];
        for (int count = 0; count < secret.length; count++) {
            secret[count] = rand.nextInt(6);
        }
        guess = new int[4];
        guesses = 0;
        won = false;
    }
    
    public boolean gameOver() {
        if (won || guesses >= 12) {
            return true;
        }
        return false;
    }
    
    public boolean won() {
        return won;
    }
    
    public String secret() {
        String secretString = "";
        for (int count = 0; count < 4; count++) {
            secretString += secret[count];
        }
        return secretString;
    }
    
    public int blackPegs() {
        int blackPegNum = 0;
        for (int count = 0; count < 4; count++) {
            if (secret[count] == guess[count]) {
                blackPegNum++;
            }
        }
        return blackPegNum;
    }
    
    public int whitePegs() {
        int whitePegNum = 0;
        
        //Create copies of secret and guess
        int [] secretTemp = new int[4];
        for (int count = 0; count < 4; count++) {
            secretTemp[count] = secret[count];
        }
        
        int [] guessTemp = new int[4];
        for (int count = 0; count < 4; count++) {
            guessTemp[count] = guess[count];
        }
        
        //This loop eliminates blackPegs
        for (int count = 0; count < 4; count++) {
            if (guessTemp[count] == secretTemp[count]) {
                guessTemp[count] = 6;
                secretTemp[count] = 7;
            }
        }   
        
        //Runs through and counts all whitePegs (replaces the values so they are not counted again)
        for (int count = 0; count < 4; count++) {
            for (int count2 = 0; count2 < 4; count2++) {
                if (guessTemp[count] == secretTemp[count2]) {
                    guessTemp[count] = 6;
                    secretTemp[count2] = 7;
                    whitePegNum++;
                }
            }
        }
        
        return whitePegNum;
    }
    
    public void makeGuess(String guess) {
        for (int count = 0; count < 4; count++) {
            this.guess[count] = guess.charAt(count) - '0';
        }
        
        guesses++;
        
        if (blackPegs() == 4) won = true;
    }
    
    public static void main(String [] args) {
        Scanner sc=new Scanner(System.in);  
        boolean play = true;
        while (play) {
            Mastermind game = new Mastermind();
            
            while ( ! game.gameOver()) {
                System.out.print("Mastermind: ");
                String guess = sc.next();
                if (guess.length() != 4) {
                    System.out.print("Invalid guess. Try again: ");
                    guess = sc.next();
                }
                
                game.makeGuess( guess );
                System.out.println("Black pegs: " + game.blackPegs() + "   White pegs: " + game.whitePegs() + "\n");
            }
            
            if( game.won() ) {
                System.out.println("You won!");
            } else {
                System.out.println("Too many guesses. My secret was: " + game.secret());
            }
            System.out.println("Do you want to play a new game?.\n");
            System.out.println("Type Y for yes and N for no");
            String user_request = sc.next();
            if (user_request != "N" || user_request != "Y") {
                System.out.println("Please type Y for yes or N for no.");
            } else if (user_request == "N") {
                play = false;
            } else {
                System.out.println("Let's start!");
            }
            
        }
    }
}