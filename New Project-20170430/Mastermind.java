import java.util.*;

class Mastermind {
    int [] secret;
    int [] guess;
    int [] secretTemp;
    int [] guessTemp;
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
    
    public int[] copyIntArray(int[] array1) {
        int[] array2 = new int[array1.length];
        for (int count = 0; count < array1.length; count++) {
            array2[count] = array1[count];
        }
        return array2;
    }
    
    public int compareIntArray(int[] array1, int[] array2, int countSpeed, int counter) {
        for (int count = 0; count < array1.length; count++) {
            for (int count2 = 0; count2 < array1.length; count2++) {
                if (array1[count] == array2[count2]) {
                    array2[count2] = 6;
                    array1[count] = 7;
                    counter++;
                }
                count += countSpeed;
            }
        }
        return counter;
    }
    
    public int blackPegs() {
        int blackPegNum = 0;
        secretTemp = copyIntArray(secret);
        guessTemp = copyIntArray(guess);
        
        blackPegNum = compareIntArray(secretTemp, guessTemp, 1, blackPegNum);
        
        if (blackPegNum == 4) won = true;
        return blackPegNum;
    }
    
    public int whitePegs() {
        int whitePegNum = 0;

        whitePegNum = compareIntArray(secretTemp, guessTemp, 0, whitePegNum);
        
        return whitePegNum;
    }
    
    public void makeGuess(Mastermind game, Scanner sc) {
        while ( ! game.gameOver() ) {
            System.out.print("Mastermind: ");
            String guess = sc.next();
                
            while ( ! game.validGuess(guess) ) {
                System.out.print("Invalid guess. Try again: ");
                guess = sc.next();
            }
                
            game.convertGuessInt( guess );
            System.out.println("Black pegs: " + game.blackPegs() + "   White pegs: " + game.whitePegs() + "\n");
        }
    }
    
    public void convertGuessInt(String guess) {
        for (int count = 0; count < 4; count++) {
            this.guess[count] = guess.charAt(count) - '0';
        }
        
        guesses++;
    }
    
    public boolean validGuess(String guess) {
        boolean validity = true;
        if (guess.length() == 4) {
            for (int count = 0; count < 4; count++) {
                if (guess.charAt(count) - '0' > 5 || guess.charAt(count) - '0' < 0) {
                    validity = false;
                    break;
                }
            }
        } else {
            validity = false;
        }
        return validity;
    }
    
    public static void main(String [] args) {
        Scanner sc=new Scanner(System.in);  
        boolean play = true;
        while (play) {
            Mastermind game = new Mastermind();
            game.makeGuess(game, sc);
            
            if( game.won() ) {
                System.out.println("You won!\n");
            } else {
                System.out.println("Too many guesses. My secret was: " + game.secret() + "\n");
            }
            System.out.println("Do you want to play a new game?");
            System.out.print("Type Y for yes or anything else for no: ");
            String user_request = sc.next().toUpperCase();
            if (user_request.equals("Y")) {
                  System.out.println("Let's start!\n");
            } else {
                  play = false;
            }
        }
    }
}
